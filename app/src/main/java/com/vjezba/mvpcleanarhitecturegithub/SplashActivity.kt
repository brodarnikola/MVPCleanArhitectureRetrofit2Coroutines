package com.vjezba.mvpcleanarhitecturegithub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vjezba.mvpcleanarhitecturegithub.presentation.RepositoriesActivity
import com.vjezba.mvpcleanarhitecturegithub.presentation.UsersActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        btnNext.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}