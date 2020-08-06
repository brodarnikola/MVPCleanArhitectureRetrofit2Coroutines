package com.vjezba.mvpcleanarhitecturegithub.presentation.activities

import android.os.Bundle
import android.os.RecoverySystem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aldieemaulana.take.listener.RecyclerViewScrollListener
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
    val repositoryLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    private var currentPage : Int = 1
    private var nextPage : Boolean = false

    var keyword: String = ""
    var sort: String = ""
    var order: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)
        githubPresenter.attachView( this )
        repositoryAdapter = RepositoryAdapter( mutableListOf<RepositoryDetails>(), mutableListOf<RepositoryDetails>())
        repository_list.apply {
            layoutManager = repositoryLayoutManager
            adapter = repositoryAdapter
        }

        btnFind.setOnClickListener {
            val searchRepositoryDialog =
                SearchRepositoryDialog(this)
            searchRepositoryDialog.show(supportFragmentManager,
                ""
            )
        }

        repository_list.addOnScrollListener( object : RecyclerViewScrollListener(repositoryLayoutManager) {
            override fun onLoadMore(current_page: Int) {
                if(nextPage) {
                    currentPage++
                    startSearch(keyword, sort, order, true)
                }
            }
        })

    }

    override fun setRepository(repository: Repository) {
        repositoryAdapter.setItems(repository.items)
        nextPage = true
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

    override fun startSearch(mKeyword: String, mSort: String, mOrder: String, showOtherData: Boolean) {
        print("Keyword is: ${mKeyword}")

        keyword = mKeyword
        sort = mSort
        order = mOrder

        if( repositoryAdapter.getItems().isNotEmpty() && !showOtherData ) {
            repositoryAdapter.getItems().clear()
        }
        githubPresenter.getRepositories(keyword, sort, order)
    }


}