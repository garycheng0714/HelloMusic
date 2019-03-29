package com.kkbox.hellomusic

import android.support.test.rule.ActivityTestRule
import com.kkbox.hellomusic.page.Album
import com.kkbox.hellomusic.page.Home
import org.junit.Rule
import org.junit.Test

class AlbumPageTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun openAlbum() {
        val albumName = Home().getAlbumName(0, activityRule.activity)

        Home().openAlbum(0)
        Album().checkHasTitle(albumName)
    }
}