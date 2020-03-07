package com.nakhmadov.vkfriends.ui.friends_list

import androidx.lifecycle.ViewModel
import com.nakhmadov.vkfriends.data.repository.FriendsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FriendsViewModel(private val repository: FriendsRepository) : ViewModel() {

    companion object {
        const val FRIENDS_COUNT_TO_GET = 5
    }

    private val job = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + job)

    val userFriends = repository.userFriends
    val profileInfo = repository.profileInfo


    init {
        viewModelScope.launch {
            repository.getUserFriends(count = FRIENDS_COUNT_TO_GET)
            repository.getProfileInfo()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

}
