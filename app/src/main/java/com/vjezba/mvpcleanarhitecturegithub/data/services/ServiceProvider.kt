package com.vjezba.mvpcleanarhitecturegithub.data.services

interface ServiceProvider {
    fun getGithubService(): GithubService
}
