package com.kotlin.github_test.network.services

import com.kotlin.github_test.network.entities.AuthorizationReq
import com.kotlin.github_test.network.entities.AuthorizationRsp
import com.kotlin.github_test.network.retrofit
import com.kotlin.github_test.settings.Configs
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthApi{
    @PUT("/authorizations/clients/${Configs.Account.clientId}/{fingerPrint}")
    fun createAuthorization(@Body req:AuthorizationReq,@Path ("fingerPrint") fingerPrint:String = Configs.Account.fingerPrint)
        : Observable<AuthorizationRsp>
    @DELETE("/authorization/{id}")
    fun deleteAuthorization(@Path("id")id:Int):Observable<Response<Any>>
}

object Authservice:AuthApi by retrofit.create(AuthApi::class.java)