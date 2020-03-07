package com.nakhmadov.vkfriends.adapters.friends_list

import androidx.recyclerview.widget.DiffUtil
import com.nakhmadov.vkfriends.domain.models.UserDomain

class FriendsListItemCallback : DiffUtil.ItemCallback<UserDomain>() {
    override fun areItemsTheSame(oldItem: UserDomain, newItem: UserDomain): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserDomain, newItem: UserDomain): Boolean {
        return oldItem == newItem
    }

}