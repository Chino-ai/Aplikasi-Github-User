package com.example.submission1aplikasigithubuser.ui.listFollower

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission1aplikasigithubuser.api.ApiConfig
import com.example.submission1aplikasigithubuser.data.Event
import com.example.submission1aplikasigithubuser.data.source.remote.response.FollowerResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFollowerViewModel : ViewModel() {

    private val _userFollower = MutableLiveData<List<FollowerResponseItem>>()
    val userFollower: LiveData<List<FollowerResponseItem>> = _userFollower
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _toast = MutableLiveData<Event<Boolean>>()
    val toast: LiveData<Event<Boolean>> = _toast
    private val _noData = MutableLiveData<String>()
    val noData: LiveData<String> = _noData

    companion object {
        private const val TAG = "ListFollowerViewModel"
    }

    fun getUserFollowerGithub(user: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(user)
        client.enqueue(object : Callback<List<FollowerResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowerResponseItem>>,
                response: Response<List<FollowerResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    responseBody?.let {user->
                        _userFollower.value = user
                        _noData.value = ""
                        if (user.isEmpty()) {
                            _noData.value = "No Follower"
                        }
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowerResponseItem>>, t: Throwable) {
                _isLoading.value = false
                _toast.value = Event(true)
                Log.e(TAG, "onFailure: ${t.message}")

            }

        })
    }
}