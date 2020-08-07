package com.vjezba.domain.usecase

import com.vjezba.domain.entities.Repository
import com.vjezba.domain.entities.RepositoryDetails


interface GithubContract {

    // repositories screen
    interface RepositoryView {
        fun setRepository(repository: Repository)
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
    }

    interface RepositoryPresenter{
        fun attachView(view: RepositoryView)
        fun getRepositories(repository: String, sort: String, order: String, showOtherData: Boolean)
    }


    // user details screen
    interface UserView {
        fun showUserDetailInExternalBrowser( userHtmlLink: String)
    }

    interface UserPresenter{
        fun attachView(view: UserView)
        fun startToDisplayUserDetailsInBrowser( userHtmlLink: String)
    }


    // repository details screen
    interface RepositoryDetailsView {
        fun displayRepositoryDetails( repositoryDetails: RepositoryDetails)
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
    }

    interface RepositoryDetailsPresenter{
        fun attachView(view: RepositoryDetailsView)
        fun loadRepositoryDetailsById( repositoryId: Long)
    }
}