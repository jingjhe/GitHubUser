package com.practice.githubuser.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.githubuser.view.MeFragment
import com.practice.githubuser.view.UsersFragment

class MainViewModel : ViewModel() {

    var meFragment: MeFragment = MeFragment()
    var usersFragment: UsersFragment = UsersFragment()

    val liveDataFragmentList = MutableLiveData<List<Fragment>>()

    fun getFragmentList() {
        val fragmentList = arrayListOf<Fragment>()
        fragmentList.clear()
        fragmentList.add(usersFragment)
        fragmentList.add(meFragment)
        liveDataFragmentList.postValue(fragmentList)
    }


}