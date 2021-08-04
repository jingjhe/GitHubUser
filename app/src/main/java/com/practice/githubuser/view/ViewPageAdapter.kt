package com.practice.githubuser.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPageAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {


    private var fragmentList = mutableListOf<Fragment>()


    fun setFragmentList(arrayList: List<Fragment>) {
        arrayList.forEach {
            fragmentList.add(it)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}