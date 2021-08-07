package com.practice.githubuser.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practice.githubuser.R
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.practice.githubuser.viewmodel.UserViewModel


class MeFragment : Fragment() {


    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val followingText = view.findViewById<TextView>(R.id.tvFlowering)
        val loginText = view.findViewById<TextView>(R.id.tvLogin)
        userViewModel.fetchMeData()
        userViewModel.getLiveDataMeUser().observe(viewLifecycleOwner, Observer {
            val str = getString(R.string.me_follower)
            val showStr = String.format(str, it.followers, it.following)
            followingText.text = showStr
            loginText.text = it.login
        })

        val myImage = view.findViewById<ImageView>(R.id.imageView)
        val options = RequestOptions()
        val myActivity = activity as MainActivity
        Glide.with(myActivity)
            .load(R.drawable.nate)
            .apply(options.circleCrop())
            .into(myImage)
    }


}