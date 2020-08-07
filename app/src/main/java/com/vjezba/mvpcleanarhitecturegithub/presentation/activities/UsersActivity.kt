package com.vjezba.mvpcleanarhitecturegithub.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.vjezba.mvpcleanarhitecturegithub.R
import kotlinx.android.synthetic.main.activity_user_details.*

class UsersActivity : AppCompatActivity()  {

    var login = ""
    var avatarUrl = ""
    var reposUrl = ""
    var followersUrl = ""
    var siteAdmin = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        login = intent.getStringExtra("login")
        avatarUrl = intent.getStringExtra("avatar_url")
        reposUrl = intent.getStringExtra("repos_url")
        followersUrl = intent.getStringExtra("followers_url")
        siteAdmin = intent.getBooleanExtra("site_admin", false)

        Glide.with(this)
            .load(avatarUrl)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.error2)
            .fallback(R.drawable.error2)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ivUserImage)

        textUserName.text = "Username: " + login
        textReposUrl.text = "Repos url: "  + reposUrl
        textFollowersUrl.text = "Follower url: "  +followersUrl
        textSiteAdmin.text = "Is this user admin in github: "  +siteAdmin
    }

}
