package com.practice.githubuser.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.githubuser.R
import com.practice.githubuser.model.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var usersListData = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersListData[position]
        holder.tvUser.text = "${user.login}\n ${user.type} "

        Glide.with(holder.imgUser)
            .load(user.avatar_url)
            .circleCrop()
            .into(holder.imgUser)
    }

    override fun getItemCount(): Int {
        return usersListData.size
    }

    fun setUsersDataList(arrayList: ArrayList<User>) {
        usersListData = arrayList
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUser = view.findViewById<TextView>(R.id.textView)
        val imgUser = view.findViewById<ImageView>(R.id.imageView)

    }
}