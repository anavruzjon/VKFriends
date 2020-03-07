package com.nakhmadov.vkfriends.adapters.friends_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nakhmadov.vkfriends.databinding.ItemFriendBinding
import com.nakhmadov.vkfriends.domain.models.UserDomain

class FriendsListAdapter : ListAdapter<UserDomain, RecyclerView.ViewHolder>(FriendsListItemCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FriendViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FriendViewHolder).bind(getItem(position))
    }

    class FriendViewHolder private constructor(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserDomain) {
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FriendViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemFriendBinding.inflate(inflater, parent, false)
                return FriendViewHolder(binding)
            }
        }

    }

}