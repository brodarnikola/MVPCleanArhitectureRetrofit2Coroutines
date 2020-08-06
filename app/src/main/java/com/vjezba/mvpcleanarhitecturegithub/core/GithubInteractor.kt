package com.vjezba.mvpcleanarhitecturegithub.core

import com.vjezba.mvpcleanarhitecturegithub.core.entities.MainResponse
import com.vjezba.mvpcleanarhitecturegithub.core.entities.Repository

interface GithubInteractor {

    suspend fun getUsers(userRepoString: String): Result<MainResponse>

    suspend fun getRepositories(userRepoString: String): Result<Repository>
}