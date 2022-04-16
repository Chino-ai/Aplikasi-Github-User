package com.example.submission1aplikasigithubuser.ui.listFollowing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission1aplikasigithubuser.api.ApiConfig
import com.example.submission1aplikasigithubuser.data.Event
import com.example.submission1aplikasigithubuser.data.source.remote.response.FollowingResponseItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFollowingViewModel : ViewModel() {

    private val _userFollowing = MutableLiveData<List<FollowingResponseItem>>()
    val userFollowing: LiveData<List<FollowingResponseItem>> = _userFollowing
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val _toast = MutableLiveData<Event<Boolean>>()
    val toast: LiveData<Event<Boolean>> = _toast
    private val _noData = MutableLiveData<String>()
    val noData: LiveData<String> = _noData

    companion object {
        private const val TAG = "ListFollowingViewModel"
    }

    fun getUserFollowingGithub(user: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(user)
        client.enqueue(object : Callback<List<FollowingResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowingResponseItem>>,
                response: Response<List<FollowingResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    responseBody?.let {user->
                        _userFollowing.value = user
                        _noData.value = ""
                        if (user.isEmpty()) {
                            _noData.value = "No Following"
                        }
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowingResponseItem>>, t: Throwable) {
                _isLoading.value = false
                _toast.value = Event(true)
                Log.e(TAG, "onFailure: ${t.message}")

            }

        })
    }
}