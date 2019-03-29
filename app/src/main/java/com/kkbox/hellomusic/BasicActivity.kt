package com.kkbox.hellomusic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open class BasicActivity: AppCompatActivity() {

    lateinit var accessToken: String

    override fun onCreate(savedInstanceState: Bundle?) {

        // https://developer.android.com/training/appbar/setting-up
        setSupportActionBar(findViewById(R.id.toolbar))

        // https://developer.android.com/training/appbar/up-action
        // Get a support ActionBar corresponding to this toolbar and enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        accessToken = intent.getStringExtra(MainActivity.ACCESS_TOKEN)

        super.onCreate(savedInstanceState)
    }
}