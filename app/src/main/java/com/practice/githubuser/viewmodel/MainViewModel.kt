package com.practice.githubuser.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practice.githubuser.view.MeFragment
import com.practice.githubuser.view.UserListFragment

class MainViewModel : ViewModel() {

    var meFragment: MeFragment = MeFragment()
    var userListFragment: UserListFragment = UserListFragment()

    val liveDataFragmentList = MutableLiveData<List<Fragment>>()

    fun getFragmentList() {
        val fragmentList = arrayListOf<Fragment>()
        fragmentList.clear()
        fragmentList.add(userListFragment)
        fragmentList.add(meFragment)
        liveDataFragmentList.postValue(fragmentList)
    }


}