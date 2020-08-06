package com.vjezba.mvpcleanarhitecturegithub.presentation.presenter

import com.vjezba.mvpcleanarhitecturegithub.core.GithubContract
import com.vjezba.mvpcleanarhitecturegithub.core.GithubInteractor
import com.vjezba.mvpcleanarhitecturegithub.core.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RepositoryPresenter(private val githubInteractor: GithubInteractor) : GithubContract.RepositoryPresenter,
    CoroutineScope {

    var page: Int = 1
    var pageNumber: Int = 15

    private var view: GithubContract.RepositoryView? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var job: Job? = null

    override fun attachView(view: GithubContract.RepositoryView) {
        this.view = view
    }

    override fun getRepositories(repository: String, sort: String, order: String) {
        job = launch {
            getRepositoriesAsync(repository, sort, order)
        }
    }

    suspend fun getRepositoriesAsync(repository: String, sort: String, order: String) {
        view?.showProgress()

        when (val result = githubInteractor.getRepositories(repository, sort, order, page, pageNumber)) {
            is Result.Success -> {
                view?.setRepository(result.data)
                page++
            }
            is Result.Error ->
                result.throwable.message?.let {
                    view?.showMessage(it)
                }
        }
        view?.hideProgress()
    }
}