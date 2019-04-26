package com.kkbox.hellomusic

import com.kkbox.hellomusic.page.AlbumList
import com.kkbox.hellomusic.page.Home
import com.kkbox.hellomusic.rule.HelloMusicActivityRule
import org.junit.Rule
import org.junit.Test
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import org.junit.After



class AlbumListPageTest {

    @get:Rule
    val activityRule = HelloMusicActivityRule(MainActivity::class.java)

    @Rule @JvmField
    var reportHelper: ReportHelper = Factory.getReportHelper()

    @After
    fun tearDown() {
        reportHelper.label("Stopping App")
    }

    @Test
    fun openAlbumList() {
        Home().openNewAlbumList()
        AlbumList().checkNewAlbumTitleDisplayed()
    }

    @Test
    fun checkAlbumContent() {
        Home().openNewAlbumList()
        AlbumList().checkAlbumContentDisplayed()
    }
}