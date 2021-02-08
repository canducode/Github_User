package com.submission.githubuser.ui.detail

import android.annotation.SuppressLint
import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.githubuser.R
import com.submission.githubuser.databinding.FragmentUserBinding
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.CONTENT_URI
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.URL_AVATAR
import com.submission.githubuser.db.DatabaseContract.UserColumns.Companion.USERNAME
import com.submission.githubuser.service.model.ModelDetail
import com.submission.githubuser.service.response.ResponseDetail

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var uriWithId: Uri

    private lateinit var dataDetail: ResponseDetail
    private var favorite = false

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int) = UserFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_SECTION_NUMBER, index)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = activity?.intent?.getStringExtra(DetailActivity.EXTRA_USERNAME).toString()

        val modelDetail = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ModelDetail::class.java]
        modelDetail.setDetail(username)
        binding.shimmerViewContainer.startShimmer()

        modelDetail.getDetail().observe(viewLifecycleOwner, {
            if (it?.login != null) {
                dataDetail = it
                setUI()
            }
        })

        binding.btnFavorite.setOnClickListener {
            setStateFavorite()
        }
    }

    @SuppressLint("SetTextI18n", "Recycle")
    private fun setUI() {
        binding.layoutDetail.apply {

            Glide.with(this@UserFragment)
                .load(dataDetail.avatar_url)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_black).error(R.drawable.ic_broken_black))
                .into(imgAvatar)

            tvName.text = dataDetail.name
            tvUsername.text = dataDetail.login
            btnFollower.text = dataDetail.followers.toString()
            btnFollowing.text = dataDetail.following.toString()
            tvCompany.text = dataDetail.company?:"-"
            tvLocation.text = dataDetail.location?:"-"
            tvRepo.text = "${dataDetail.public_repos} Repository"
        }
        binding.apply {
            shimmerViewContainer.stopShimmer()
            shimmerViewContainer.hideShimmer()
            btnFavorite.isEnabled = true
        }

        uriWithId = Uri.parse(CONTENT_URI.toString() + "/" + dataDetail.login!!)
        val cursor = context?.contentResolver?.query(uriWithId, null, null, null, null)
        if (cursor?.count != 0) {
            favorite = true
            binding.btnFavorite.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.ic_favorite, null))
        }
    }

    private fun setStateFavorite() {
        favorite = !favorite
        if (favorite) {
            binding.btnFavorite.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.ic_favorite, null))
            val values = ContentValues()
            values.put(USERNAME, dataDetail.login)
            values.put(URL_AVATAR, dataDetail.avatar_url)
            context?.contentResolver?.insert(CONTENT_URI, values)
        } else {
            binding.btnFavorite.setImageDrawable(ResourcesCompat.getDrawable(resources,R.drawable.ic_favorite_border, null))
            context?.contentResolver?.delete(uriWithId, null, null)
        }
    }
}