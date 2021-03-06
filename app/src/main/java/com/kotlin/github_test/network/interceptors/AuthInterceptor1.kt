package com.kotlin.github_test.network.interceptors

import android.util.Base64
import com.kotlin.github_test.model.account.AccountManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor1:Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var origin = chain.request()
        return  chain.proceed(origin.newBuilder()
            .apply {
                 when{

                     origin.url.pathSegments.contains("authorizations")->{
                        val userCredentials= AccountManager.username + ":" + AccountManager.password
                        val auth = "Basic"+ String(Base64.encode(userCredentials.toByteArray(),Base64.DEFAULT)).trim()
                        header("Authorization",auth)
                    }

                    AccountManager.isLogin()->{
                        val auth = "Token" + AccountManager.token
                        header("Authorization",auth)
                    }
                    else ->removeHeader("Authorization")
                }
        }.build())
    }

}