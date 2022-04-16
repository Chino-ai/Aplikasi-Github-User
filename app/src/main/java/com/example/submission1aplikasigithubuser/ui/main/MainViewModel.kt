package com.example.submission1aplikasigithubuser.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.example.submission1aplikasigithubuser.api.ApiConfig
import com.example.submission1aplikasigithubuser.data.Event
import com.example.submission1aplikasigithubuser.data.source.remote.response.ItemsItem
import com.example.submission1aplikasigithubuser.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _userGithub = MutableLiveData<List<ItemsItem>>()
    val userGithub: LiveData<List<ItemsItem>> = _userGithub
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _toast = MutableLiveData<Event<Boolean>>()
    val toast: LiveData<Event<Boolean>> = _toast
    private val _noData = MutableLiveData<String>()
    val noData: LiveData<String> = _noData


    companion object {
        private const val TAG = "MainViewModel"
    }

    init {

        getUserGithub("h")
    }


    fun getUserGithub(user: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUser(user)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        _userGithub.value = responseBody.items
                        _noData.value = ""
                        if (responseBody.totalCount == 0) {
                            _noData.value = "No User"
                        }
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }

            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                _toast.value = Event(true)
                Log.e(TAG, "onFailure: ${t.message}")


            }

        })
    }

}