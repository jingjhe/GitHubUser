package com.practice.githubuser.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.practice.githubuser.R
import com.practice.githubuser.viewmodel.UserViewModel

class UsersFragment : Fragment() {


    private val userViewModel: UserViewModel by activityViewModels()
    lateinit var userAdapter: UserAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            val decoration = DividerItemDecoration(activity, VERTICAL)
            addItemDecoration(decoration)
            userAdapter = UserAdapter()
            adapter = userAdapter
        }
        userViewModel.fetchUsersListData()
        setObserve()
    }

    private fun setObserve() {
        userViewModel.getLiveDataUsersList().observe(viewLifecycleOwner, Observer {
            userAdapter.setUsersDataList(it)
            userAdapter.notifyDataSetChanged()
        })
    }


}