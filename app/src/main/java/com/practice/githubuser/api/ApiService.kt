package com.practice.githubuser.api

import com.practice.githubuser.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/users/{userId}")
    fun getUserById(
        @Path("userId") userId: String
    ): Observable<User>

}