package com.practice.githubuser.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.practice.githubuser.R
import com.practice.githubuser.model.User
import com.practice.githubuser.viewmodel.MainViewModel
import com.practice.githubuser.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(), UserAdapter.OnItemActionListener {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var userViewModel: UserViewModel

    companion object {
        const val EXTRA_MESSAGE_LOGIN = "LOGIN"
    }

    private val adapter = ViewPageAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        initView()
        loadData()
    }


    private fun initView() {
        val viewPager = findViewById<ViewPager2>(R.id.viewPagerMain)
        viewPager.adapter = adapter
        val tabLayoutMain = findViewById<TabLayout>(R.id.tabLayoutMain)
        val title: ArrayList<String> =
            arrayListOf(getString(R.string.main_tab1), getString(R.string.main_tab2))
        val icon: ArrayList<Int> =
            arrayListOf(R.drawable.ic_users, R.drawable.ic_info)

        TabLayoutMediator(tabLayoutMain, viewPager) { tab, position ->
            tab.text = title[position]
            tab.icon = getDrawable(icon[position])
        }.attach()
    }

    private fun loadData() {
        mainViewModel.getFragmentList()
        mainViewModel.liveDataFragmentList.observe(this, Observer {
            adapter.setFragmentList(it)
        })

    }

    override fun onClick(position: Int) {
        val user = userViewModel.getLiveDataUsersList().value?.get(position) as User
        if (user.login.isNotEmpty()) {
            openDetailUserActivity(user.login)
        }
    }

    private fun openDetailUserActivity(login: String) {

        val intent = Intent(this, UserActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE_LOGIN, login)
        }
        startActivity(intent)
    }


}