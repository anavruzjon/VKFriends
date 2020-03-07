package com.nakhmadov.vkfriends.data.remote.response_models

import com.nakhmadov.vkfriends.domain.models.UserDomain

data class UserNet(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val nickname: String = "",
    val domain: String = "",
    val bdate: String = "",
    val city: CityNet = CityNet(),
    val photo_100: String = "",
    val online: Int = 0
)

fun UserNet.asDomainModel() = UserDomain(
    id = this.id,
    firstName = this.first_name,
    lastName = this.last_name,
    photo = this.photo_100,
    city = if (this.city.id != 0) this.city.title else null
)
