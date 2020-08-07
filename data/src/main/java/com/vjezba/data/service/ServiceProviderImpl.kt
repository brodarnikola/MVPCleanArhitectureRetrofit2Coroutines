package com.vjezba.data.service

import com.vjezba.data.network.ServiceFactory


class ServiceProviderImpl(private val serviceFactory: ServiceFactory) : ServiceProvider {
    override fun getGithubService(): GithubService {
        return serviceFactory.create(GithubService::class.java)
    }
}