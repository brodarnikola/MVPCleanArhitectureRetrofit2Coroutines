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

    private var view: GithubContract.RepositoryView? = null

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var job: Job? = null

    override fun attachView(view: GithubContract.RepositoryView) {
        this.view = view
    }

    override fun getRepositories(repository: String) {
        job = launch {
            getRepositoriesAsync(repository)
        }
    }

    suspend fun getRepositoriesAsync(repositories: String) {
        view?.showProgress()
        when (val result = githubInteractor.getRepositories(repositories)) {
            is Result.Success -> view?.setRepository(result.data/* .map { it.mapToMainReponseItem() }*/)
            is Result.Error ->
                result.throwable.message?.let {
                    view?.showMessage(it)
                }
        }
        view?.hideProgress()
    }
}