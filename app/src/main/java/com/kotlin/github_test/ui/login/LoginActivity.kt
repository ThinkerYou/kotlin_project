package com.kotlin.github_test.ui.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.*
import com.kotlin.common.ext.otherWise
import com.kotlin.common.ext.yes
import com.kotlin.github_test.R
import com.kotlin.github_test.presenter.LoginPresenter
import com.kotlin.github_test.util.hideSoftInput
import com.kotlin.mvp.Impl.BaseActivity

class LoginActivity : BaseActivity<LoginPresenter>() {
    val signInButton by lazy{findViewById<Button>(R.id.signInButton)}
    val password by lazy { findViewById<EditText>(R.id.password) }
    val username by lazy { findViewById<EditText>(R.id.username) }
    val progressBar by lazy{findViewById<ProgressBar>(R.id.progressBar)}
    val logForm by lazy{findViewById<LinearLayout>(R.id.logForm)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signInButton.setOnClickListener(){
                presenter.checkUserName(username.text.toString())
                    .yes {
                        presenter.checkPassword(password.text.toString())
                            .yes {
                                hideSoftInput()
                                presenter.doLogin(username.text.toString(),password.text.toString())
                            }.otherWise {
                                showTips(password,"密码不合法")
                            }
                    }.otherWise {
                        showTips(username,"用户名不合法")
                    }
        }
    }

    private fun showProgressbar(isShow:Boolean){
        var shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime)
        logForm.animate().setDuration(shortAnimTime.toLong()).alpha((if(isShow) 0 else 1).toFloat())
            .setListener(object :AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    logForm.visibility = if(isShow) View.GONE else View.VISIBLE
                }
            })
        progressBar.animate().setDuration(shortAnimTime.toLong()).alpha((if(isShow) 0 else 1).toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    progressBar.visibility = if(isShow) View.VISIBLE else View.GONE
                }

            })
    }

    private fun showTips(view: EditText, tips: String) {
        view.requestFocus()
        view.error = tips
    }

    fun loginStart(){
        showProgressbar(true)
    }
    fun loginError(e:Throwable){
        e.printStackTrace()
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show()
        showProgressbar(false)
    }
    fun loginSuccess(){
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
        showProgressbar(false)
    }
    fun dataInit(pwd:String,name:String){
        username.setText(name)
        password.setText(pwd)
    }
}
