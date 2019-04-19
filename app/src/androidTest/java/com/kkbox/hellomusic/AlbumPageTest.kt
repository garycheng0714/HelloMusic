package com.kkbox.hellomusic

import android.support.test.rule.ActivityTestRule
import com.kkbox.hellomusic.page.Album
import com.kkbox.hellomusic.page.Home
import com.kkbox.hellomusic.rule.HelloMusicActivityRule
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import org.junit.After
import org.junit.Rule
import org.junit.Test

class AlbumPageTest {

    @get:Rule
    val activityRule = HelloMusicActivityRule(MainActivity::class.java)

    @Rule @JvmField
    var reportHelper: ReportHelper = Factory.getReportHelper()

    @After
    fun tearDown() {
        reportHelper.label("Stopping App")
    }

    @Test
    fun openAlbum() {
        val albumName = Home().getAlbumName(0)

        Home().openAlbum(0)
        Album().checkHasTitle(albumName)
    }
}