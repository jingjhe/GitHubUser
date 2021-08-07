package com.practice.githubuser.view

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
import com.practice.githubuser.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    lateinit var progressBar: ProgressBar
    private val adapter = ViewPageAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initView()
        loadData()
    }


    private fun initView() {
        val viewPager = findViewById<ViewPager2>(R.id.viewPagerMain)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        viewPager.adapter = adapter
        val tabLayoutMain = findViewById<TabLayout>(R.id.tabLayoutMain)
        val title: ArrayList<String> =
            arrayListOf(getString(R.string.main_tab1), getString(R.string.main_tab2))
        val icon: ArrayList<Int> =
            arrayListOf(R.drawable.ic_users, R.drawable.ic_me)

        TabLayoutMediator(tabLayoutMain, viewPager) { tab, position ->
            tab.text = title[position]
            tab.icon = getDrawable(icon[position])
        }.attach()
    }

    private fun loadData() {
        progressBar.visibility = VISIBLE
        mainViewModel.getFragmentList()
        mainViewModel.liveDataFragmentList.observe(this, Observer {
            progressBar.visibility = GONE
            adapter.setFragmentList(it)
        })

    }



}