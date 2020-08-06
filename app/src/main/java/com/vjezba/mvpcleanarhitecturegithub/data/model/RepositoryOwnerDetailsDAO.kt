package com.vjezba.mvpcleanarhitecturegithub.data.model


import com.google.gson.annotations.SerializedName
import com.vjezba.mvpcleanarhitecturegithub.core.entities.RepositoryOwnerDetails

data class RepositoryOwnerDetailsDAO(
    @SerializedName("login")
    val login: String = "",
    @SerializedName("avatar_url")
    val avatar_url: String = ""
)

fun RepositoryOwnerDetailsDAO.mapToRepositoryOwnerDetails(): RepositoryOwnerDetails {
    return RepositoryOwnerDetails(login, avatar_url)
}
