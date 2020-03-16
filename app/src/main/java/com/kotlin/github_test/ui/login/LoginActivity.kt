package com.kotlin.github_test.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signInButton.setOnClickListener(){
            presenter.checkUserName(username.text.toString()).yes {
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

    private fun showTips(view: EditText, tips: String) {
        view.requestFocus()
        view.error = tips
    }

    fun loginStart(){}
    fun loginError(e:Throwable){
        e.printStackTrace()
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show()
    }
    fun loginSuccess(){
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
    }
    fun dataInit(pwd:String,name:String){
        username.setText(name)
        password.setText(pwd)
    }
}
