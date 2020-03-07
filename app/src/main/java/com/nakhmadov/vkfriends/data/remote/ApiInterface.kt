package com.nakhmadov.vkfriends.data.remote

import com.nakhmadov.vkfriends.data.remote.response_models.ResponseFriendsList
import com.nakhmadov.vkfriends.data.remote.response_models.ResponseProfileInfo
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("method/friends.get")
    fun fetchUserFriendsAsync(
        @Query("fields") fields: String = "nickname,domain,bdate,city,photo_100",
        @Query("order") order: String = "random",
        @Query("access_token") accessToken: String,
        @Query("v") version: String = "5.103",
        @Query("count") count: Int = 5
    ): Deferred<ResponseFriendsList>

    @GET("method/account.getProfileInfo")
    fun fetchProfileInfoAsync(
        @Query("access_token") accessToken: String,
        @Query("v") version: String = "5.103"
    ): Deferred<ResponseProfileInfo>
}