package com.kkbox.hellomusic.rule

import android.app.Activity
import android.support.test.rule.ActivityTestRule
import java.lang.Thread.sleep

class HelloMusicActivityRule<T: Activity>(activityClass: Class<T>): ActivityTestRule<T>(activityClass) {

    override fun afterActivityLaunched() {
        sleep(5000)
    }
}