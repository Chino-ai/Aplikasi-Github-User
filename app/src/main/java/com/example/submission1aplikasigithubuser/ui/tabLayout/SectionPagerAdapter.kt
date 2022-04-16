package com.example.submission1aplikasigithubuser.ui.tabLayout

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.submission1aplikasigithubuser.ui.listFollower.ListFollowerFragment
import com.example.submission1aplikasigithubuser.ui.listFollowing.ListFollowingFragment


class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ListFollowerFragment()
            1 -> fragment = ListFollowingFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2
}