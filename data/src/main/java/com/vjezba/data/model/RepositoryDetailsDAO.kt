package com.vjezba.data.model


import com.google.gson.annotations.SerializedName
import com.vjezba.domain.entities.RepositoryDetails
import com.vjezba.domain.entities.RepositoryOwnerDetails

data class RepositoryDetailsDAO(
    val id: Int = 0,
    @SerializedName("owner")
    val owner: RepositoryOwnerDetails = RepositoryOwnerDetails(""),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("stargazers_count")
    val stargazers_count: String = "",
    @SerializedName("watchers_count")
    val watchers_count: String = "",
    val forks: String = "",
    @SerializedName("open_issues")
    val open_issues: Int = 0
)


fun RepositoryDetailsDAO.mapToRepositoryDetails(): RepositoryDetails {
    return RepositoryDetails(id, owner, name, description, stargazers_count, watchers_count, forks, open_issues)
}
