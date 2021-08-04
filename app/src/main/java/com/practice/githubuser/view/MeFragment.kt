package com.practice.githubuser.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.practice.githubuser.R
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.widget.ImageView

import com.bumptech.glide.Glide

import com.bumptech.glide.request.RequestOptions


class MeFragment : Fragment() {


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
        val myImage = view.findViewById<ImageView>(R.id.imageView)
        val options = RequestOptions()
        val myActivity = activity as MainActivity
        Glide.with(myActivity)
            .load(R.drawable.nate)
            .apply(options.circleCrop())
            .into(myImage)
    }


}