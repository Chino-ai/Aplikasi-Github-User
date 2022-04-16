package com.example.submission1aplikasigithubuser.ui.detailUserActivity

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.submission1aplikasigithubuser.database.UserGithub
import com.example.submission1aplikasigithubuser.repository.UserGithubRepository

class UserAddViewModel(application: Application):ViewModel() {
    private val mUserGithubRepository: UserGithubRepository = UserGithubRepository(application)

    fun insert(userGithub: UserGithub){
        mUserGithubRepository.insert(userGithub)
    }

    fun delete(userGithub: UserGithub){
        mUserGithubRepository.delete(userGithub)
    }



}