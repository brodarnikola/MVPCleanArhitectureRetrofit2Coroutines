package com.vjezba.mvpcleanarhitecturegithub.data

import com.vjezba.mvpcleanarhitecturegithub.core.GithubRepository
import com.vjezba.mvpcleanarhitecturegithub.core.Result
import com.vjezba.mvpcleanarhitecturegithub.core.entities.MainResponse
import com.vjezba.mvpcleanarhitecturegithub.core.entities.Repository
import com.vjezba.mvpcleanarhitecturegithub.data.services.ServiceProvider
import com.vjezba.mvpcleanarhitecturegithub.data.model.mapToMainReponse
import com.vjezba.mvpcleanarhitecturegithub.data.model.mapToRepository

class GithubRepositoryImpl(private val serviceProvider: ServiceProvider) : GithubRepository {

    override suspend fun getUserRepo(userRepoString: String): Result<MainResponse> {
        return try {
            val githubService = serviceProvider.getGithubService()
            val mainResponseDAO = githubService.getUserRepoAsync(userRepoString, 0, 100).await()
            val users = mainResponseDAO.mapToMainReponse()//.map { it.mapToMainReponse() }
            Result.Success(users)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }

    override suspend fun getRepositories(userRepoString: String): Result<Repository> {
        return try {
            val githubService = serviceProvider.getGithubService()
            val mainResponseDAO = githubService.getRepositoryAsync(userRepoString, 0, 100).await()
            val repository = mainResponseDAO.mapToRepository()//.map { it.mapToMainReponse() }
            Result.Success(repository)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }

}