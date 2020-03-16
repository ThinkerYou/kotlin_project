package com.kotlin.mvp.Impl

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import com.kotlin.mvp.IMVpView
import com.kotlin.mvp.IPresenter
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

abstract class BaseActivity<Presenter: BasePresenter<BaseActivity<Presenter>>> : IMVpView<Presenter>, Activity(){
    override val presenter: Presenter

    init{
        presenter = createPresenterJava()
        presenter.view = this
    }

    private fun createPresentKt():Presenter{
        sequence{
            var thisClass:KClass<*> = this@BaseActivity::class
            while (true){
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure?:break
            }
        }.flatMap{
            it.flatMap{it.arguments}.asSequence()
        }.first{
            it.type?.jvmErasure?.isSuperclassOf(IPresenter::class)?:false
        }.let{
          return it.type?.jvmErasure?.primaryConstructor?.call() as Presenter
        }
    }

    private fun createPresenterJava():Presenter {
        sequence{
            var thisClass:Class<*> = this@BaseActivity::class.java
            while (true){
                yield(thisClass.genericSuperclass)
                thisClass = thisClass.superclass?:break
            }
        }.flatMap{
            (it as ParameterizedType).actualTypeArguments.asSequence()
        }.first{
           it is Class<*>&&IPresenter::class.java.isAssignableFrom(it)
        }.let{
            return (it as Class<Presenter>).newInstance()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        presenter.onConfigurationChanged(newConfig)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        presenter.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}