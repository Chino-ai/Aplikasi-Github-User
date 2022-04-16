package com.example.submission1aplikasigithubuser.ui.detailUserActivity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submission1aplikasigithubuser.R
import com.example.submission1aplikasigithubuser.data.source.remote.response.ItemsItem
import com.example.submission1aplikasigithubuser.database.UserGithub
import com.example.submission1aplikasigithubuser.databinding.ActivityDetailUserBinding
import com.example.submission1aplikasigithubuser.helper.ViewModelFactory
import com.example.submission1aplikasigithubuser.ui.tabLayout.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {


    private lateinit var detailUserBinding: ActivityDetailUserBinding
    private lateinit var userAddViewModel: UserAddViewModel
    private lateinit var userGithub: UserGithub
    private val detailUserViewModel by viewModels<DetailUserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)

        setContentView(detailUserBinding.root)

        val user = intent.getParcelableExtra<ItemsItem>(EXTRA_USER) as ItemsItem
        userGithub = UserGithub()
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = detailUserBinding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = detailUserBinding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
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

            detailUserBinding.vUsername.text = it

        })

        detailUserViewModel.name.observe(this, {
            detailUserBinding.vName.text = it

        })

        detailUserViewModel.follower.observe(this, {
            detailUserBinding.vFollow.text = it
        })

        detailUserViewModel.following.observe(this, {
            detailUserBinding.vFollowing.text = it
        })

        detailUserViewModel.company.observe(this, {
            detailUserBinding.vCompany.text = it
        })

        detailUserViewModel.location.observe(this, {
            detailUserBinding.vLocation.text = it
        })

        detailUserViewModel.repository.observe(this, {
            detailUserBinding.vRepository.text = it
        })

        detailUserViewModel.avatar.observe(this, {
            Glide.with(detailUserBinding.avatar)
                .load(it)
                .apply(RequestOptions().override(55, 55))
                .into(detailUserBinding.avatar)
        })
        detailUserViewModel.getUserDetail(user.login)
        name = user.login

        userAddViewModel = obtainViewModel(this@DetailUserActivity)

        detailUserBinding.favourite.setOnClickListener {
            userGithub.id = user.id
            userGithub.name = detailUserBinding.vName.text.toString()
            userGithub.login = detailUserBinding.vUsername.text.toString()
            userGithub.avatar = user.avatarUrl

            userAddViewModel.insert(userGithub)
            Toast.makeText(
                this@DetailUserActivity,
                "Sudah di tambahkan ke daftar Favorit",
                Toast.LENGTH_SHORT
            ).show()
        }


    }

    companion object {
        const val EXTRA_USER = "extra_user"
        lateinit var name: String

        @StringRes
        val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
    private fun obtainViewModel(activity: AppCompatActivity): UserAddViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(UserAddViewModel::class.java)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            detailUserBinding.progressBar.visibility = View.VISIBLE
        } else {
            detailUserBinding.progressBar.visibility = View.GONE
        }
    }

    private fun showToast(isToast: Boolean) {
        val caution = "No Internet Connection"
        if (isToast) {

            Toast.makeText(this@DetailUserActivity, caution, Toast.LENGTH_SHORT).show()
        }
    }


}