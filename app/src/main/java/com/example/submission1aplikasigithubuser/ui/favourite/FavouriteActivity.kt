package com.example.submission1aplikasigithubuser.ui.favourite

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1aplikasigithubuser.databinding.ActivityFavouriteBinding
import com.example.submission1aplikasigithubuser.helper.ViewModelFactory

class FavouriteActivity : AppCompatActivity() {
    private lateinit var favouriteBinding: ActivityFavouriteBinding
    private lateinit var adapter: FavouriteListAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favouriteBinding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(favouriteBinding.root)

        val mainViewModel = obtainViewModel(this@FavouriteActivity)
        mainViewModel.getAllUser().observe(this, {
            if (it != null) {
                adapter.setListNotes(it)
            }
        })

        adapter = FavouriteListAdapter(this)

        favouriteBinding.noFavourite.text = "No Favourite"
        favouriteBinding.rvUsers.layoutManager = LinearLayoutManager(this)
        favouriteBinding.rvUsers.setHasFixedSize(true)
        favouriteBinding.rvUsers.adapter = adapter


    }

    private fun obtainViewModel(activity: AppCompatActivity): FavouriteViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavouriteViewModel::class.java)
    }
}