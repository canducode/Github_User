package com.submission.githubuser.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.githubuser.databinding.FragmentFollowingBinding
import com.submission.githubuser.service.adapter.FollowAdapter
import com.submission.githubuser.service.model.ModelFollowing

class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    private lateinit var modelFollowing: ModelFollowing
    private lateinit var adapter: FollowAdapter

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int) = FollowingFragment().apply {
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
        _binding = FragmentFollowingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FollowAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvFolloweing.layoutManager = LinearLayoutManager(activity)
            rvFolloweing.adapter = adapter

            modelFollowing = ViewModelProvider(this@FollowingFragment, ViewModelProvider.NewInstanceFactory())[ModelFollowing::class.java]
        }

        modelFollowing.setFollowing(activity?.intent?.getStringExtra(DetailActivity.EXTRA_USERNAME).toString())

        modelFollowing.getFollowing().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setData(it)
            }
        })
    }
}