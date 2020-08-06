package com.vjezba.mvpcleanarhitecturegithub.core

import com.vjezba.mvpcleanarhitecturegithub.core.entities.MainResponse
import com.vjezba.mvpcleanarhitecturegithub.core.entities.Repository

class GithubInteractorImpl(private val githubRepository: GithubRepository) : GithubInteractor {

    override suspend fun getUsers(userRepoString: String): Result<MainResponse> {
        return githubRepository.getUserRepo(userRepoString)
    }

    override suspend fun getRepositories(repository: String, sort: String, order: String, page: Int, pageNumber: Int): Result<Repository> {
        return  githubRepository.getRepositories(repository, sort, order, page, pageNumber)
    }
}