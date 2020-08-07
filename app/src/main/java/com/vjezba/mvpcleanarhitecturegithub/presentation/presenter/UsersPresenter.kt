package com.vjezba.mvpcleanarhitecturegithub.presentation.presenter

import com.vjezba.domain.GithubInteractor
import com.vjezba.domain.usecase.GithubContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import com.vjezba.domain.Result

class UsersPresenter(private val githubInteractor: GithubInteractor) : GithubContract.UserPresenter,
    CoroutineScope {

    private var view: GithubContract.UserView? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var job: Job? = null

    override fun attachView(view: GithubContract.UserView) {
        this.view = view
    }

    override fun getUsers(users: String) {
        job = launch {
            getUsersAsync(users)
        }
    }

    suspend fun getUsersAsync(users: String) {
        view?.showProgress()
        when (val result = githubInteractor.getUsers(users)) {
            is Result.Success -> view?.setUsers(result.data/* .map { it.mapToMainReponseItem() }*/)
            is Result.Error ->
                result.throwable.message?.let {
                    view?.showMessage(it)
                }
        }
        view?.hideProgress()
    }
}