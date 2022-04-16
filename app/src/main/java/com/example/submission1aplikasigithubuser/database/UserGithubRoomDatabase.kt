package com.example.submission1aplikasigithubuser.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserGithub::class], version = 3)
abstract class UserGithubRoomDatabase : RoomDatabase() {
    abstract fun userGithubDao(): UserGithubDao

    companion object {
        @Volatile
        private var INSTANCE: UserGithubRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): UserGithubRoomDatabase {
            if (INSTANCE == null) {
                synchronized(UserGithubRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        UserGithubRoomDatabase::class.java, "userGithub_database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE as UserGithubRoomDatabase

        }


    }
}