package com.submission.githubuser.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.githubuser.databinding.FragmentFollowerBinding
import com.submission.githubuser.service.adapter.FollowAdapter
import com.submission.githubuser.service.model.ModelFollowers

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding!!

    private lateinit var modelFollowers: ModelFollowers
    private lateinit var adapter: FollowAdapter

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        fun newInstance(index: Int) = FollowerFragment().apply {
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
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FollowAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvFollower.layoutManager = LinearLayoutManager(activity)
            rvFollower.adapter = adapter

            modelFollowers = ViewModelProvider(this@FollowerFragment, ViewModelProvider.NewInstanceFactory())[ModelFollowers::class.java]
        }

        modelFollowers.setFollowers(activity?.intent?.getStringExtra(DetailActivity.EXTRA_USERNAME).toString())

        modelFollowers.getFollowers().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setData(it)
            }
        })
    }
}