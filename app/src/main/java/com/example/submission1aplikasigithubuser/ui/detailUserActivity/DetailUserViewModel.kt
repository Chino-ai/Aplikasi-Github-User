package com.example.submission1aplikasigithubuser.ui.detailUserActivity


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission1aplikasigithubuser.api.ApiConfig
import com.example.submission1aplikasigithubuser.data.Event
import com.example.submission1aplikasigithubuser.data.source.remote.response.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {



    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _follower = MutableLiveData<String>()
    val follower: LiveData<String> = _follower

    private val _following = MutableLiveData<String>()
    val following: LiveData<String> = _following

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> = _location

    private val _company = MutableLiveData<String>()
    val company: LiveData<String> = _company

    private val _repository = MutableLiveData<String>()
    val repository: LiveData<String> = _repository

    private val _avatar = MutableLiveData<String>()
    val avatar: LiveData<String> = _avatar

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toast = MutableLiveData<Event<Boolean>>()
    val toast: LiveData<Event<Boolean>> = _toast

    companion object {
        private const val TAG = "DetailUserViewModel"
    }


    fun getUserDetail(user: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUserDetail(user)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        _name.value = responseBody.name?: "-"
                        _username.value = responseBody.login?: "-"
                        _follower.value = responseBody.followers.toString()?: "-"
                        _following.value = responseBody.following.toString()?: "-"
                        _company.value = responseBody.company?: "-"
                        _repository.value = responseBody.reposUrl?: "-"
                        _location.value = responseBody.location?: "-"
                        _avatar.value = responseBody.avatarUrl?: "-"


                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
                _toast.value = Event(true)
                Log.e(TAG, "onFailure: ${t.message}")

            }

        })
    }
}