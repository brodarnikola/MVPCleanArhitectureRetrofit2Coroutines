package com.vjezba.mvpcleanarhitecturegithub.presentation.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vjezba.mvpcleanarhitecturegithub.R
import com.vjezba.mvpcleanarhitecturegithub.core.GithubContract
import com.vjezba.mvpcleanarhitecturegithub.core.entities.MainResponse
import com.vjezba.mvpcleanarhitecturegithub.core.entities.UserRepo
import com.vjezba.mvpcleanarhitecturegithub.presentation.adapters.UsersAdapter
import com.vjezba.mvpcleanarhitecturegithub.presentation.hide
import com.vjezba.mvpcleanarhitecturegithub.presentation.show
import kotlinx.android.synthetic.main.activity_user_details.*
import org.koin.android.ext.android.inject

class UsersActivity : AppCompatActivity(), GithubContract.UserView {

    private val userPresenter: GithubContract.UserPresenter by inject()
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        userPresenter.attachView(this)
        val usersLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        usersAdapter = UsersAdapter( mutableListOf<UserRepo>(), mutableListOf<UserRepo>())
        users_list.apply {
            layoutManager = usersLayoutManager
            adapter = usersAdapter
        }

        btnFind.setOnClickListener {
            if( usersAdapter.getItems().isNotEmpty() ) {
                usersAdapter.getItems().clear()
            }
            userPresenter.getUsers(etInsertText.text.toString())
        }
    }

    override fun setUsers(users: MainResponse) {
        usersAdapter.setItems(users.items)
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
