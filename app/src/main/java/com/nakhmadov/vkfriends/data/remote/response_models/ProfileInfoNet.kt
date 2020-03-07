package com.nakhmadov.vkfriends.data.remote.response_models

import com.nakhmadov.vkfriends.domain.models.ProfileInfoDomain

data class ProfileInfoNet(
    val first_name: String,
    val last_name: String,
    val sex: Int,
    val relation: Int,
    val bdate: String,
    val cityNet: CityNet = CityNet()
)

fun ProfileInfoNet.asDomainModel() = ProfileInfoDomain(this.first_name, this.last_name)

