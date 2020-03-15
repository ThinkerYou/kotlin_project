package com.kotlin.github_test.network.entities

import com.kotlin.common.anno.PoKo
import com.kotlin.github_test.settings.Configs

@PoKo
data class AuthorizationReq(var scopes:List<String> = Configs.Account.SCOPS,
                           var client_secret:String = Configs.Account.clientSecret,
                           var note:String = Configs.Account.note,
                           var note_url:String = Configs.Account.noteUrl)
@PoKo
data class AuthorizationRsp(var id:Int,
                            var url:String,
                            var token:String,
                            var token_last_eight:String,
                            var hashed_token:String,
                            var note:String,
                            var note_url: String,
                            var updated_at:String,
                            var created_at:String)