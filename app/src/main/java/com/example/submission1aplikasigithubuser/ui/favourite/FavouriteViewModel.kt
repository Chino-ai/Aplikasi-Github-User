package com.example.submission1aplikasigithubuser.ui.favourite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submission1aplikasigithubuser.database.UserGithub
import com.example.submission1aplikasigithubuser.repository.UserGithubRepository

class FavouriteViewModel(application: Application): ViewModel() {
    private val mUserGithubRepository : UserGithubRepository = UserGithubRepository(application)

    fun getAllUser() : LiveData<List<UserGithub>> = mUserGithubRepository.getAllUser()
}