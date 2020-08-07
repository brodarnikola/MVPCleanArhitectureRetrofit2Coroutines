package com.vjezba.data.service

interface ServiceProvider {
    fun getGithubService(): GithubService
}
