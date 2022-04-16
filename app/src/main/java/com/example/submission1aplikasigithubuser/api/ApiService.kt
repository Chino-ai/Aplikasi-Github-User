package com.example.submission1aplikasigithubuser.api
import com.example.submission1aplikasigithubuser.data.source.remote.response.DetailResponse
import com.example.submission1aplikasigithubuser.data.source.remote.response.UserResponse
import com.example.submission1aplikasigithubuser.data.source.remote.response.FollowerResponseItem
import com.example.submission1aplikasigithubuser.data.source.remote.response.FollowingResponseItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getUser(
        @Query("q") id: String
    ): Call<UserResponse>


    @GET("users/{login}")

    fun getUserDetail(
        @Path("login") id: String
    ): Call<DetailResponse>

    @GET("users/{login}/followers")

    fun getFollowers(
        @Path("login") id: String
    ): Call<List<FollowerResponseItem>>

    @GET("users/{login}/following")
    fun getFollowing(
        @Path("login") id: String
    ): Call<List<FollowingResponseItem>>
}