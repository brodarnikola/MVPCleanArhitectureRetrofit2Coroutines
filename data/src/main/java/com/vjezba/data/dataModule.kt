package com.vjezba.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vjezba.data.network.NetworkServiceFactory
import com.vjezba.data.network.ServiceFactory
import com.vjezba.data.service.ServiceProvider
import com.vjezba.data.service.ServiceProviderImpl
import com.vjezba.domain.GithubRepository
import com.vjezba.mvpcleanarhitecturegithub.data.environments.Environment
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single {
        val converterFactory: Converter.Factory = GsonConverterFactory.create()
        converterFactory
    }

    single {
        val callAdapterFactory: CallAdapter.Factory = CoroutineCallAdapterFactory()
        callAdapterFactory
    }

    factory { HttpLoggingInterceptor.Level.BODY }

    factory { Environment("https://api.github.com/") }

    single {
        val serviceFactory: ServiceFactory = NetworkServiceFactory(
            converterFactory = get(),
            callAdapterFactory = get(),
            logLevel = get(),
            environment = get()
        )
        serviceFactory
    }

    factory {
        val serviceProvider: ServiceProvider = ServiceProviderImpl(get())
        serviceProvider
    }

    factory {
        val githubRepository: GithubRepository =
            GithubRepositoryImpl(get())
        githubRepository
    }
}