package com.vjezba.mvpcleanarhitecturegithub.core

import com.vjezba.mvpcleanarhitecturegithub.core.entities.MainResponse
import com.vjezba.mvpcleanarhitecturegithub.core.entities.Repository

class GithubInteractorImpl(private val githubRepository: GithubRepository) : GithubInteractor {

    override suspend fun getUsers(userRepoString: String): Result<MainResponse> {
        return githubRepository.getUserRepo(userRepoString)
    }

    override suspend fun getRepositories(repository: String): Result<Repository> {
        return githubRepository.getRepositories(repository)
    }
}