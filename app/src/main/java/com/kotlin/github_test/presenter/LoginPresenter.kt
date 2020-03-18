package com.kotlin.github_test.presenter

import android.annotation.SuppressLint
import com.kotlin.github_test.BuildConfig
import com.kotlin.github_test.model.account.AccountManager
import com.kotlin.github_test.ui.login.LoginActivity
import com.kotlin.mvp.Impl.BasePresenter

class LoginPresenter:BasePresenter<LoginActivity>(){

    @SuppressLint("CheckResult")
    fun doLogin(userName:String, password:String){
        AccountManager.username  = userName
        AccountManager.password = password
        view.loginStart()
        AccountManager.login()
            .subscribe({
            view.loginSuccess()
        },{
            view.loginError(it)
        })
    }

    fun checkUserName(userName: String): Boolean {
        return true
    }

    fun checkPassword(password: String): Boolean {
        return true
    }

    override fun onResume() {
        super.onResume()
        if(BuildConfig.DEBUG){
            view.dataInit(BuildConfig.testPassword,BuildConfig.testUserName)
        }else{
        view.dataInit(AccountManager.password,AccountManager.username)}
    }
}