package com.nakhmadov.vkfriends.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.nakhmadov.vkfriends.data.remote.ApiInterface
import com.nakhmadov.vkfriends.data.remote.response_models.asDomainModel
import com.nakhmadov.vkfriends.data.remote.response_models.asListOfDomainModels
import com.nakhmadov.vkfriends.domain.models.ProfileInfoDomain
import com.nakhmadov.vkfriends.domain.models.UserDomain
import com.nakhmadov.vkfriends.ui.authorization.AuthFragment.Companion.ACCESS_TOKEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FriendsRepository private constructor(private val network: ApiInterface, private val sharedPrefs: SharedPreferences) {

    private val accessToken = sharedPrefs.getString(ACCESS_TOKEN, "")

    private val _userFriends: MutableLiveData<List<UserDomain>> = MutableLiveData()
    val userFriends: LiveData<List<UserDomain>> = _userFriends

    private val _profileInfo = MutableLiveData<ProfileInfoDomain>()
    val profileInfo: LiveData<ProfileInfoDomain> = _profileInfo

    suspend fun getProfileInfo() {
        withContext(Dispatchers.IO) {
            accessToken?.let {
                val networkResult = network.fetchProfileInfoAsync(accessToken).await()
                val profileInfo = networkResult.response.asDomainModel()
                _profileInfo.postValue(profileInfo)
            }
        }
    }

    suspend fun getUserFriends(count: Int) {
        withContext(Dispatchers.IO) {
            accessToken?.let {
                val networkResult = network.fetchUserFriendsAsync(accessToken = accessToken, count = count).await()
                val items = networkResult.response.asListOfDomainModels()
                _userFriends.postValue(items)
            }
        }
    }

    companion object {
        private var instance: FriendsRepository? = null
        fun getRepository(network: ApiInterface, sharedPrefs: SharedPreferences): FriendsRepository {
            synchronized(this) {
                if (instance == null) {
                    instance = FriendsRepository(network, sharedPrefs)
                }
                return instance as FriendsRepository
            }
        }
    }
}