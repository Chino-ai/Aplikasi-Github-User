package com.example.submission1aplikasigithubuser.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserGithubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userGithub: UserGithub)

    @Update
    fun update(userGithub: UserGithub)
    @Delete
    fun delete(userGithub: UserGithub)



    @Query("SELECT * from userGithub")
    fun getAllUsers(): LiveData<List<UserGithub>>
}