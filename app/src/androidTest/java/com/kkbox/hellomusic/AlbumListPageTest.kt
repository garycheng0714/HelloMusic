package com.kkbox.hellomusic

import android.support.test.rule.ActivityTestRule
import com.kkbox.hellomusic.page.AlbumList
import com.kkbox.hellomusic.page.Home
import org.junit.Rule
import org.junit.Test

class AlbumListPageTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun openAlbumList() {
        Home().openNewAlbumList()
        AlbumList().checkTitleDisplayed()
    }
}