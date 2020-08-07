package com.vjezba.data


import com.vjezba.data.model.mapToMainReponse
import com.vjezba.data.model.mapToRepository
import com.vjezba.data.service.ServiceProvider
import com.vjezba.domain.GithubRepository
import com.vjezba.domain.entities.MainResponse
import com.vjezba.domain.entities.Repository

import com.vjezba.domain.Result

class GithubRepositoryImpl(private val serviceProvider: ServiceProvider) : GithubRepository {

    override suspend fun getUserRepo(users: String): Result<MainResponse> {
        return try {
            val githubService = serviceProvider.getGithubService()
            val mainResponseDAO = githubService.getUserRepoAsync(users, 0, 100).await()
            val users = mainResponseDAO.mapToMainReponse()//.map { it.mapToMainReponse() }
            Result.Success(users)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }

    override suspend fun getRepositories(repository: String, sort: String, order: String, page: Int, pageNumber: Int): Result<Repository> {
        return try {
            val githubService = serviceProvider.getGithubService()
            val mainResponseDAO = githubService.getRepositoryAsync(repository, sort, order, page, pageNumber).await()
            val repository = mainResponseDAO.mapToRepository()//.map { it.mapToMainReponse() }
            Result.Success(repository)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }

}