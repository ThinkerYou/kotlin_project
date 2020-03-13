package com.kotlin.github_test

import com.kotlin.common.sharedpreferences.Preference

object Settings{
    val email:String by Preference(AppContext, "email", "")
    val password:String by Preference(
        AppContext,
        "password",
        ""
    )

}