package com.vjezba.domain

import com.vjezba.domain.entities.MainResponse
import com.vjezba.domain.entities.Repository


interface GithubInteractor {

    suspend fun getUsers(userRepoString: String): Result<MainResponse>

    suspend fun getRepositories(repository: String, sort: String, order: String, page: Int, numberOfItems: Int): Result<Repository>
}