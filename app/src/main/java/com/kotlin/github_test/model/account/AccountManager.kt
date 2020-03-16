package com.kotlin.github_test.model.account

import com.google.gson.Gson
import com.kotlin.github_test.network.entities.AuthorizationReq
import com.kotlin.github_test.network.entities.AuthorizationRsp
import com.kotlin.github_test.network.entities.User
import com.kotlin.github_test.network.services.Authservice
import com.kotlin.github_test.network.services.UserService
import com.kotlin.github_test.util.fromJson
import com.kotlin.github_test.util.prop
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

interface OnAccountStateChanged{
    fun login(user: User)
    fun logout()
}

object AccountManager {
    var authId: Int by prop(-1)
    var username: String by prop("")
    var password: String by prop("")
    var token: String by prop("")
    var userjson:String by prop("")

    var loginStateListener:OnAccountStateChanged
        get() {
            TODO()
        }
        set(value) {}

    var onAccountStateChangedListeners = ArrayList<OnAccountStateChanged>()

    private fun notifyLogin(user:User){
        onAccountStateChangedListeners.forEach {
            it.login(user)
        }
    }
    private fun notifyLogout(){
        onAccountStateChangedListeners.forEach {
            it.logout()
        }
    }

    var currentUser:User? = null
        get(){
            if(field==null && userjson.isNotEmpty()){
                field = Gson().fromJson<User>(userjson)
            }

            return field
        }
        set(value) {
            if(value==null){
                userjson=""
            }else{
                Gson().toJson(value)
            }
            field = value
        }


    fun isLogin(): Boolean = token.isEmpty()

    fun login() = Authservice.createAuthorization(AuthorizationReq())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnNext {
            if (it.token.isEmpty())
                throw AccountException(it)
        }.retryWhen {
            it.flatMap {
                if (it is AccountException) {
                    Authservice.deleteAuthorization(it.authorizationReq.id)
                } else
                    Observable.error(it)
            }
        }.flatMap {
            token = it.token
            authId = it.id
            UserService.getAuthenticatedUser()
        }.map {
            currentUser = it
            notifyLogin(it)
        }

    fun logout() = Authservice.deleteAuthorization(authId)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnNext {
            if (it.isSuccessful) {
                token = ""
                authId = -1
                currentUser=null
                notifyLogout()
            }else{
                throw HttpException(it)
            }
        }

    class AccountException(val authorizationReq: AuthorizationRsp) : Exception("Already login in.")
}