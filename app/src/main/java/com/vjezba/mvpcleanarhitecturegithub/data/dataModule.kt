package com.vjezba.mvpcleanarhitecturegithub.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.vjezba.mvpcleanarhitecturegithub.data.environments.Environment
import com.vjezba.mvpcleanarhitecturegithub.data.network.NetworkServiceFactory
import com.vjezba.mvpcleanarhitecturegithub.data.network.ServiceFactory
import com.vjezba.mvpcleanarhitecturegithub.data.services.ServiceProvider
import com.vjezba.mvpcleanarhitecturegithub.data.services.ServiceProviderImpl
import com.vjezba.mvpcleanarhitecturegithub.core.GithubRepository
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
            get(),
            get(),
            get(),
            get()
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