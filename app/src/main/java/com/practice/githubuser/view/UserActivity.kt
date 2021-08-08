package com.practice.githubuser.view

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.practice.githubuser.R
import com.practice.githubuser.viewmodel.UserViewModel

class UserActivity : AppCompatActivity() {

    private lateinit var imgUser: ImageView
    private lateinit var imgBack: ImageView
    private lateinit var tvUserName1: TextView
    private lateinit var tvUserName2: TextView
    private lateinit var tvUserLocal: TextView
    private lateinit var tvUserLink: TextView

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            window.statusBarColor = Color.BLACK
        }
        supportActionBar!!.hide()
        if (!intent.getStringExtra(MainActivity.EXTRA_MESSAGE_LOGIN).isNullOrEmpty()) {
            userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            val login = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_LOGIN)
            initView()
            loadData(login!!)
        } else {
            finish()
        }
    }

    private fun initView() {

        imgBack = findViewById(R.id.imgBack)
        imgUser = findViewById(R.id.imageViewUser)
        tvUserName1 = findViewById(R.id.tvUserName)
        tvUserName2 = findViewById(R.id.tvName)
        tvUserLocal = findViewById(R.id.tvLocal)
        tvUserLink = findViewById(R.id.tvLink)
        imgBack.setOnClickListener {
            finish()
        }
    }

    private fun loadData(login: String) {
        userViewModel.fetchUserDataByLogin(login)

        userViewModel.getLiveDataUser().observe(this, Observer {
            tvUserName1.text = it.login
            tvUserName2.text = it.login
            tvUserLocal.text = it.location
            tvUserLink.text = it.blog

            val options = RequestOptions()
            Glide.with(this)
                .load(it.avatar_url)
                .apply(options.circleCrop())
                .into(imgUser)
        })


        userViewModel.getLiveDataMessage().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })


    }

}