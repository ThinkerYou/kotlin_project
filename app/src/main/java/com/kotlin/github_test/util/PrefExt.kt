package com.kotlin.github_test.util

import com.kotlin.common.sharedpreferences.Preference
import com.kotlin.github_test.AppContext
import kotlin.reflect.jvm.jvmName

inline fun <reified R, T> R.prop(default:T)= Preference(AppContext,"",default,R::class.jvmName)