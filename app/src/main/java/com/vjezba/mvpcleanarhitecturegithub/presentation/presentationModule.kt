package com.vjezba.mvpcleanarhitecturegithub.presentation

import com.vjezba.mvpcleanarhitecturegithub.core.GithubContract
import com.vjezba.mvpcleanarhitecturegithub.presentation.presenter.RepositoryPresenter
import com.vjezba.mvpcleanarhitecturegithub.presentation.presenter.UsersPresenter
import org.koin.dsl.module

val presentationModule = module {
    factory {
        val userUserPresenter: GithubContract.UserPresenter =
            UsersPresenter(
                get()
            )
        userUserPresenter
    }

    factory {
        val repositoryPresenter: GithubContract.RepositoryPresenter =
            RepositoryPresenter(
                get()
            )
        repositoryPresenter
    }
}