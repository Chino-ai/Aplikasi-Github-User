package com.example.submission1aplikasigithubuser.ui.favourite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1aplikasigithubuser.R
import com.example.submission1aplikasigithubuser.database.UserGithub
import com.example.submission1aplikasigithubuser.databinding.ActivityDetailFavouriteBinding
import com.example.submission1aplikasigithubuser.ui.detailUserActivity.DetailUserActivity.Companion.name
import com.example.submission1aplikasigithubuser.ui.detailUserActivity.DetailUserViewModel
import com.example.submission1aplikasigithubuser.ui.tabLayout.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailFavouriteActivity : AppCompatActivity() {
    private lateinit var detailFavouriteBinding: ActivityDetailFavouriteBinding
    private val detailUserViewModel by viewModels<DetailUserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailFavouriteBinding = ActivityDetailFavouriteBinding.inflate(layoutInflater)
        setContentView(detailFavouriteBinding.root)

        val user = intent.getParcelableExtra<UserGithub>(EXTRA_USER_FAVOURITE) as UserGithub


        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = detailFavouriteBinding.viewsPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = detailFavouriteBinding.viewsTabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES2[position])
        }.attach()

        detailUserViewModel.isLoading.observe(this, {
            showLoading(it)
        })

        detailUserViewModel.toast.observe(this, {
            it.getContentIfNotHandled()?.let {
                showToast(it)
            }

        })

        detailUserViewModel.username.observe(this, {

            detailFavouriteBinding.vUsername.text = it

        })

        detailUserViewModel.name.observe(this, {
            detailFavouriteBinding.vName.text = it

        })

        detailUserViewModel.follower.observe(this, {
            detailFavouriteBinding.vFollow.text = it
        })

        detailUserViewModel.following.observe(this, {
            detailFavouriteBinding.vFollowing.text = it
        })

        detailUserViewModel.company.observe(this, {
            detailFavouriteBinding.vCompany.text = it
        })

        detailUserViewModel.location.observe(this, {
            detailFavouriteBinding.vLocation.text = it
        })

        detailUserViewModel.repository.observe(this, {
            detailFavouriteBinding.vRepository.text = it
        })

        detailUserViewModel.avatar.observe(this, {
            Glide.with(detailFavouriteBinding.avatar)
                .load(it)
                .apply(RequestOptions().override(55, 55))
                .into(detailFavouriteBinding.avatar)
        })
        detailUserViewModel.getUserDetail(user.login.toString())
        name = user.login.toString()


    }

    companion object {
        const val EXTRA_USER_FAVOURITE = "extra_user_favourite"

        @StringRes
        val TAB_TITLES2 = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            detailFavouriteBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailFavouriteBinding.progressBar.visibility = View.GONE
        }
    }

    private fun showToast(isToast: Boolean) {
        val caution = "No Internet Connection"
        if (isToast) {

            Toast.makeText(this@DetailFavouriteActivity, caution, Toast.LENGTH_SHORT).show()
        }
    }
}