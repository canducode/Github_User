package com.submission.githubuser.service.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.submission.githubuser.ui.detail.FollowerFragment
import com.submission.githubuser.R
import com.submission.githubuser.ui.detail.FollowingFragment
import com.submission.githubuser.ui.detail.UserFragment

class SectionPagerAdapter(private val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var tabTitle = intArrayOf(R.string.user, R.string.follower, R.string.following)

    override fun getCount(): Int = tabTitle.size

    override fun getItem(position: Int): Fragment {
        return  when (position) {
            0 -> UserFragment.newInstance(position+1)
            1 -> FollowerFragment.newInstance(position+1)
            else -> FollowingFragment.newInstance(position+1)
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(tabTitle[position])
    }
}