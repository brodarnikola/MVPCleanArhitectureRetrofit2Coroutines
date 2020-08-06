package com.vjezba.mvpcleanarhitecturegithub.core

import org.koin.dsl.module

val coreModule = module {
    factory {
        val githubInteractor: GithubInteractor =
            GithubInteractorImpl(get())
        githubInteractor
    }
}