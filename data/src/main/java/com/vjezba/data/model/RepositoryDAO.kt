package com.vjezba.data.model


import com.google.gson.annotations.SerializedName
import com.vjezba.domain.entities.Repository
import com.vjezba.domain.entities.RepositoryDetails

data class RepositoryDAO(

    @SerializedName("total_count")
    val total_count: Int,
    @SerializedName("incomplete_results")
    val incomplete_results: Boolean,
    val items: List<RepositoryDetails>
)


fun RepositoryDAO.mapToRepository(): Repository {
    return Repository(total_count, incomplete_results, items)
}