package com.kotlin.mvp

import android.content.res.Configuration
import android.os.Bundle

interface ILifeCycle{
    fun onCreate( savedInstanceState: Bundle?)

    fun onViewStateRestored(savedInstanceState:Bundle?)

    fun onSaveInstanceState(outState:Bundle )

    fun onConfigurationChanged(newConfig: Configuration)

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()
}