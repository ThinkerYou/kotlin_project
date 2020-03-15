package com.kotlin.github_test.network.services

import com.kotlin.github_test.network.entities.User
import com.kotlin.github_test.network.retrofit
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface UserApi{
    @GET("/user")
    fun getAuthenticatedUser():Observable<User>
}

object UserService:UserApi by retrofit.create(UserApi::class.java)