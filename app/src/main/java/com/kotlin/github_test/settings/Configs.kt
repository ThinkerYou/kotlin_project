package com.kotlin.github_test.settings

import com.kotlin.common.log.logger
import com.kotlin.github_test.AppContext
import com.kotlin.github_test.util.deviceId

object Configs{
    object Account{
        val SCOPS= listOf("user","repo","notification","gist","admin:org")
        const val clientId = "38b54ba3d68340782756"
        const val clientSecret="52a5f25371118ae23e48ebdfb747184cbf399564"
        const val note="kotliner"
        const val noteUrl="https://www.kotliner.cn"

        val fingerPrint by lazy{
            (AppContext.deviceId + clientId).also {
                logger.info("fingerPrint:"+it)
            }
        }
    }

}