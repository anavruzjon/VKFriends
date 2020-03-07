package com.nakhmadov.vkfriends.data.remote.response_models

import com.nakhmadov.vkfriends.domain.models.UserDomain

data class ItemsList(val count: Int = 0, val items: List<UserNet> = mutableListOf())

fun ItemsList.asListOfDomainModels(): List<UserDomain> {
    val items = mutableListOf<UserDomain>()
    this.items.forEach {
        items.add(it.asDomainModel())
    }
    return items
}