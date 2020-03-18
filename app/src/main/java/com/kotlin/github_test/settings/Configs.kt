package com.kotlin.github_test.settings

import com.kotlin.common.log.logger
import com.kotlin.github_test.AppContext
import com.kotlin.github_test.util.deviceId

object Configs{
    object Account{
        val SCOPES= listOf("user","repo","notifications","gist","admin:org")
        const val clientId = "d3d3dd9bc2e926b07287"
        const val clientSecret="fd3e311a2d17daeade449e17c7f14cbe50c05fb7"
        const val note="kotliner"
        const val noteUrl="https://www.kotliner.cn"

        val fingerPrint by lazy{
            (AppContext.deviceId + clientId).also { logger.info("fingerPrint:"+it) }
        }
    }

}