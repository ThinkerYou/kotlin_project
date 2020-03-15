package com.kotlin.github_test.ui.login

import android.os.Bundle
import com.kotlin.github_test.R
import com.kotlin.github_test.presenter.LoginPresenter
import com.kotlin.mvp.Impl.BaseActivity

class LoginActivity : BaseActivity<LoginPresenter>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
