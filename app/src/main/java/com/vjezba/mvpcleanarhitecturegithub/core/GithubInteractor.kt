package com.vjezba.mvpcleanarhitecturegithub.core

import com.vjezba.mvpcleanarhitecturegithub.core.entities.MainResponse
import com.vjezba.mvpcleanarhitecturegithub.core.entities.Repository

interface GithubInteractor {

    suspend fun getUsers(userRepoString: String): Result<MainResponse>

    suspend fun getRepositories(repository: String, sort: String, order: String, page: Int, numberOfItems: Int): Result<Repository>
}