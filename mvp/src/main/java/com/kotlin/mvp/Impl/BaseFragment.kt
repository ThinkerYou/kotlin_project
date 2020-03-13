package com.kotlin.mvp.Impl
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.kotlin.mvp.IMVpView
import com.kotlin.mvp.IPresenter
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass
import kotlin.reflect.full.isSuperclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

abstract class BaseFragment<out P:BasePresenter<BaseFragment<P>>> :IMVpView<P>,Fragment(){

    override val presenter: P

    init {
        presenter = createPresenterKt()
        presenter.view = this
    }

    private fun createPresenterKt(): P {
       sequence{
            var thisClass:KClass<*> = this@BaseFragment::class
            while (true){
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes.firstOrNull()?.jvmErasure?:break
            }
        }.flatMap{
           it.flatMap{it.arguments }.asSequence()
       }.first{
           it.type?.jvmErasure?.isSuperclassOf(IPresenter::class)?:false
       }.let{
          return it!!.type!!.jvmErasure.primaryConstructor!!.call() as P
       }
    }


    private fun createPresenterJava():P{
        sequence{
            var thisClas:Class<*> = this@BaseFragment::class.java
            while (true){
                yield(thisClas.genericSuperclass)
              thisClas =  thisClas.superclass?:break
            }
        }.filter{
            it is ParameterizedType
        }.flatMap{
            (it as ParameterizedType).actualTypeArguments.asSequence()
        }.first{
            it is Class<*>&&IPresenter::class.java.isAssignableFrom(it)
        }.let{
            return (it as Class<P>).newInstance()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreate(savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        presenter.onViewStateRestored(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        presenter.onConfigurationChanged(newConfig)
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

    class MainFragment:BaseFragment<MainPresenter>()
    class MainPresenter:BasePresenter<MainFragment>(){
        override fun onResume() {
            super.onResume()
            Log.i("mvp","onResume")
        }
    }
}