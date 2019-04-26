package com.kkbox.hellomusic

import android.support.test.rule.ActivityTestRule
import com.kkbox.hellomusic.page.AlbumList
import com.kkbox.hellomusic.page.Home
import org.junit.Rule
import org.junit.Test
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import org.junit.After



class AlbumListPageTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

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