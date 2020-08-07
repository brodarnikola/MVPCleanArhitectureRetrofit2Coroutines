package com.vjezba.mvpcleanarhitecturegithub.presentation

import com.vjezba.domain.usecase.GithubContract
import com.vjezba.mvpcleanarhitecturegithub.presentation.presenter.RepositoryDetailsPresenter
import com.vjezba.mvpcleanarhitecturegithub.presentation.presenter.RepositoryPresenter
import com.vjezba.mvpcleanarhitecturegithub.presentation.presenter.UsersPresenter
import org.koin.dsl.module

val presentationModule = module {

    factory {
        val repositoryPresenter: GithubContract.RepositoryPresenter =
            RepositoryPresenter(
                get()
            )
        repositoryPresenter
    }

    factory {
        val userDetailsPresenter: GithubContract.UserPresenter =
            UsersPresenter(
                get()
            )
        userDetailsPresenter
    }

    factory {
        val repositoryDetailsPresenter: GithubContract.RepositoryDetailsPresenter =
            RepositoryDetailsPresenter(
                get()
            )
        repositoryDetailsPresenter
    }
}