package com.kkbox.hellomusic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kkbox.openapideveloper.auth.Auth

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CLIENT_ID: String = "cca0265a5f2b92c776691cb9cfdd6a37"
        private const val CLIENT_SECRET: String = "e6336cc9e943dc29ba0039f858de9a5b"
    }

    private val auth = Auth(CLIENT_ID, CLIENT_SECRET, this)
    val accessToken = auth.clientCredentialsFlow.fetchAccessToken().get().get("access_token").asString

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
