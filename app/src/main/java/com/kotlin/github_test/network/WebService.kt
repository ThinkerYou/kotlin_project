package com.kotlin.github_test.network

import com.kotlin.common.ext.ensureDir
import com.kotlin.github_test.AppContext
import com.kotlin.github_test.network.interceptors.AcceptInterceptor
import com.kotlin.github_test.network.interceptors.AuthInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.github.com"

private val cacheFile by lazy{
    File(AppContext.cacheDir,"webServiceApi").apply { ensureDir() }
}
val retrofit by lazy{
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(
            OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60,TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .cache(Cache(cacheFile,1024*1024*1024))
            .addInterceptor(AuthInterceptor())
            .addInterceptor(AcceptInterceptor)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build())
        .baseUrl(BASE_URL)
        .build()
}