package com.vjezba.mvpcleanarhitecturegithub.core

import com.vjezba.mvpcleanarhitecturegithub.core.entities.MainResponse
import com.vjezba.mvpcleanarhitecturegithub.core.entities.Repository

interface GithubContract {

    interface UserView {
        fun setUsers(users: MainResponse)
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
    }

    interface UserPresenter{
        fun attachView(view: UserView)
        fun getUsers(users: String)
    }



    interface RepositoryView {
        fun setRepository(repository: Repository)
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
    }

    interface RepositoryPresenter{
        fun attachView(view: RepositoryView)
        fun getRepositories(repository: String, sort: String, order: String)
    }

    interface Model
}