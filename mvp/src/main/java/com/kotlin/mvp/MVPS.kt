package com.kotlin.mvp


interface IPresenter<out View:IMVpView<IPresenter<View>>>:ILifeCycle{
    val view:View

}

interface IMVpView<out Presenter:IPresenter<IMVpView<Presenter>>>:ILifeCycle{
    val presenter:Presenter
}