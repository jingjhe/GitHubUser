package com.practice.githubuser.repository

import com.practice.githubuser.api.ApiService
import com.practice.githubuser.api.Config
import com.practice.githubuser.api.RetroInstance
import com.practice.githubuser.model.User
import io.reactivex.Observable


object GitHubRepository {

    val apiService = RetroInstance.client.create(ApiService::class.java)


    fun getRemoteMeUserObserver():Observable<User>{
      return  apiService.getUserById(Config.MY_USER_ID)

    }

}