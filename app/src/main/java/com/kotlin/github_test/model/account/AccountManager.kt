package com.kotlin.github_test.model

import com.kotlin.github_test.util.prop

object AccountManager{
    var username:String by prop("")
    var password:String by prop("")
    var token:String by prop("")

    fun isLogin():Boolean= TODO()
}