package com.vjezba.mvpcleanarhitecturegithub.data.model


import com.google.gson.annotations.SerializedName
import com.vjezba.mvpcleanarhitecturegithub.core.entities.RepositoryOwnerDetails

data class RepositoryOwnerDetailsDAO(
    @SerializedName("avatar_url")
    val avatar_url: String = ""
)

fun RepositoryOwnerDetailsDAO.mapToRepositoryOwnerDetails(): RepositoryOwnerDetails {
    return RepositoryOwnerDetails(avatar_url)
}
