package com.vjezba.domain

import com.vjezba.domain.entities.MainResponse
import com.vjezba.domain.entities.Repository


interface GithubRepository {

    suspend fun getUserRepo(userRepoString: String): Result<MainResponse>

    suspend fun getRepositories(repository: String, sort: String, order: String, page: Int, pageNumber: Int): Result<Repository>
}