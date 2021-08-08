package com.practice.githubuser.view

import android.content.Context
import android.text.Html
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
    private lateinit var onItemActionListener: OnItemActionListener
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = usersListData[position]
        val str = context.getString(R.string.item_user_text,user.login, user.type)
        holder.tvUser.text = Html.fromHtml(str,0)
        Glide.with(holder.imgUser)
            .load(user.avatar_url)
            .circleCrop()
            .into(holder.imgUser)

        if (onItemActionListener != null) {
            holder.itemView.setOnClickListener {
                onItemActionListener.onClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return usersListData.size
    }

    fun setUsersDataList(arrayList: ArrayList<User>) {
        usersListData = arrayList
    }

    fun setListener(listener: OnItemActionListener) {
        onItemActionListener = listener
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUser = view.findViewById<TextView>(R.id.textView)
        val imgUser = view.findViewById<ImageView>(R.id.imageView)
    }

    interface OnItemActionListener {
        fun onClick(position: Int)
    }
}