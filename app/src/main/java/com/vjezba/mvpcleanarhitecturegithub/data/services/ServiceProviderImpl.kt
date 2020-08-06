package com.vjezba.mvpcleanarhitecturegithub.data.services

import com.vjezba.mvpcleanarhitecturegithub.data.network.ServiceFactory

class ServiceProviderImpl(private val serviceFactory: ServiceFactory) : ServiceProvider {
    override fun getGithubService(): GithubService {
        return serviceFactory.create(GithubService::class.java)
    }
}