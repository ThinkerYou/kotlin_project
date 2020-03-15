package com.kotlin.github_test.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response


object AcceptInterceptor:Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var origin = chain.request()
        return chain.proceed(origin.newBuilder().apply {
            header("accept","application/vnd.github.v3.full+json,${origin.header("accept")?:""}")
        }.build())
    }

}