package com.kotlin.github_test.settings

import com.kotlin.common.sharedpreferences.Preference
import com.kotlin.github_test.AppContext

object Settings{
    val email:String by Preference(AppContext, "email", "")
    val password:String by Preference(AppContext, "password", "")

}