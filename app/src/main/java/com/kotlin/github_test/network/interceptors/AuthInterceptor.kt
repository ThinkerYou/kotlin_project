package com.kotlin.github_test.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor:Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var origin = chain.request()
        chain.proceed(origin.newBuilder().apply {
            when{
                origin.url.pathSegments.contains("authorizations")->{
                    
                }
            }
        }.build())
    }

}