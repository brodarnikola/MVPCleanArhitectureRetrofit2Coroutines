package com.vjezba.mvpcleanarhitecturegithub.presentation.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vjezba.mvpcleanarhitecturegithub.R
import com.vjezba.mvpcleanarhitecturegithub.core.GithubContract
import com.vjezba.mvpcleanarhitecturegithub.core.entities.Repository
import com.vjezba.mvpcleanarhitecturegithub.core.entities.RepositoryDetails
import com.vjezba.mvpcleanarhitecturegithub.presentation.`interface`.RepositorySearchInterface
import com.vjezba.mvpcleanarhitecturegithub.presentation.adapters.RepositoryAdapter
import com.vjezba.mvpcleanarhitecturegithub.presentation.dialog.SearchRepositoryDialog
import com.vjezba.mvpcleanarhitecturegithub.presentation.hide
import com.vjezba.mvpcleanarhitecturegithub.presentation.show
import kotlinx.android.synthetic.main.activity_repositories.*
import org.koin.android.ext.android.inject

class RepositoriesActivity : AppCompatActivity(), GithubContract.RepositoryView,
    RepositorySearchInterface {

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

            val choseEPaperTypeDialog =
                SearchRepositoryDialog(this)
            choseEPaperTypeDialog.show(supportFragmentManager,
                ""
            )
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

    override fun startSearch(keyword: String, sort: String, order: String) {
        print("Keyword is: ${keyword}")
        if( repositoryAdapter.getItems().isNotEmpty() ) {
            repositoryAdapter.getItems().clear()
        }
        githubPresenter.getRepositories(keyword)
    }


}