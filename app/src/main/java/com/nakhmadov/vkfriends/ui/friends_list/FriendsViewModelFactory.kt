package com.nakhmadov.vkfriends.ui.friends_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nakhmadov.vkfriends.data.repository.FriendsRepository

@Suppress("UNCHECKED_CAST")
class FriendsViewModelFactory(private val repository: FriendsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FriendsViewModel::class.java))
            return FriendsViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}