package com.kotlin.mvp.Impl

import android.content.res.Configuration
import android.os.Bundle
import com.kotlin.mvp.IMVpView
import com.kotlin.mvp.IPresenter

abstract class BasePresenter<out V:IMVpView<BasePresenter<V>>>:IPresenter<V>{

    override lateinit var view: @UnsafeVariance V

    override fun onSaveInstanceState(outState: Bundle) = Unit

    override fun onConfigurationChanged(newConfig: Configuration) = Unit

    override fun onStart()= Unit

    override fun onResume() = Unit

    override fun onPause() = Unit

    override fun onStop()= Unit
    override fun onDestroy() = Unit

    override fun onCreate(savedInstanceState: Bundle?) = Unit

    override fun onViewStateRestored(savedInstanceState: Bundle?)= Unit
}