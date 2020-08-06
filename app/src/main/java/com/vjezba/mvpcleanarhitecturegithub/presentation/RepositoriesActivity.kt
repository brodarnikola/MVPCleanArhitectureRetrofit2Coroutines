package com.vjezba.mvpcleanarhitecturegithub.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vjezba.mvpcleanarhitecturegithub.R
import com.vjezba.mvpcleanarhitecturegithub.core.GithubContract
import com.vjezba.mvpcleanarhitecturegithub.core.entities.Repository
import com.vjezba.mvpcleanarhitecturegithub.core.entities.RepositoryDetails
import com.vjezba.mvpcleanarhitecturegithub.presentation.list.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_repositories.*
import org.koin.android.ext.android.inject

class RepositoriesActivity : AppCompatActivity(), GithubContract.RepositoryView {

    private val githubPresenter: GithubContract.RepositoryPresenter by inject()
    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)
        githubPresenter.attachView( this )
        val repositoryLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        repositoryAdapter = RepositoryAdapter( mutableListOf<RepositoryDetails>(), mutableListOf<RepositoryDetails>())
        repository_list.apply {
            layoutManager = repositoryLayoutManager
            adapter = repositoryAdapter
        }

        btnFind.setOnClickListener {
            if( repositoryAdapter.getItems().isNotEmpty() ) {
                repositoryAdapter.getItems().clear()
            }
            githubPresenter.getRepositories(etInsertText.text.toString())
        }
    }

    override fun setRepository(repository: Repository) {
        repositoryAdapter.setItems(repository.items)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
    }
}