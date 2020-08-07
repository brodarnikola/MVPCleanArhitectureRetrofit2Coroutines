package com.vjezba.mvpcleanarhitecturegithub.presentation.presenter

import com.vjezba.domain.GithubInteractor
import com.vjezba.domain.usecase.GithubContract

class UsersPresenter(private val githubInteractor: GithubInteractor) : GithubContract.UserPresenter  {

    private var view: GithubContract.UserView? = null

    override fun attachView(view: GithubContract.UserView) {
        this.view = view
    }

    override fun startToDisplayUserDetailsInBrowser( userHtmlLink: String) {
        view?.showUserDetailInExternalBrowser(userHtmlLink)
    }

    /*suspend fun getUsersAsync(users: String) {
        view?.showProgress()
        when (val result = githubInteractor.getUsers(users)) {
            is Result.Success -> view?.showUserDetailInExternalBrowser(result.data*//* .map { it.mapToMainReponseItem() }*//*)
            is Result.Error ->
                result.throwable.message?.let {
                    view?.showMessage(it)
                }
        }
        view?.hideProgress()
    }*/
}