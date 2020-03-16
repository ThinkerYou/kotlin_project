package com.kotlin.github_test.util

import android.app.Activity
import android.content.Context
import android.view.View

fun Context.isActive(){
}
fun Context.toggleSoftInput(){
//    InputMethodManager().toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS)
}
fun View.hideSoftInput(){
//    return Context().getSystemService().hideSoftInputFromWindow(windowToken, 0)
}
fun View.showSoftInput(){}
fun Activity.hideSoftInput(){}
fun Activity.showSoftInput(){}