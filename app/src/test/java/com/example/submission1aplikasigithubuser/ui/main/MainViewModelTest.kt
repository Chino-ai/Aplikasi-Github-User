package com.example.submission1aplikasigithubuser.ui.main

import android.util.Log
import com.example.submission1aplikasigithubuser.api.ApiConfig
import com.example.submission1aplikasigithubuser.data.source.remote.response.UserResponse
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModelTest {


    private lateinit var mainViewModel: MainViewModel
    private val TAGS = "MainViewModel"

    @Before
    fun before() {

        mainViewModel = mock(MainViewModel::class.java)

    }

    @Test
    fun getUserGithub() {
        val user = mainViewModel.getUserGithub("sidiqpermana")

        assertEquals(getUser(), user)


    }

    private fun getUser() {
        val client = ApiConfig.getApiService().getUser("sidiqpermana")
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.items
                } else {
                    Log.e(TAGS, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAGS, "onFailure: ${t.message}")

            }

        })
    }
}