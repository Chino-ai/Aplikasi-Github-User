package com.example.submission1aplikasigithubuser.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.submission1aplikasigithubuser.database.UserGithub
import com.example.submission1aplikasigithubuser.database.UserGithubDao
import com.example.submission1aplikasigithubuser.database.UserGithubRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UserGithubRepository(application: Application) {
    private val mUserGithubDao : UserGithubDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UserGithubRoomDatabase.getDatabase(application)
        mUserGithubDao = db.userGithubDao()
    }

    fun getAllUser(): LiveData<List<UserGithub>> = mUserGithubDao.getAllUsers()

    fun insert(userGithub: UserGithub){
        executorService.execute{mUserGithubDao.insert(userGithub)}
    }

    fun delete(userGithub: UserGithub){
        executorService.execute{mUserGithubDao.delete(userGithub)}
    }



}