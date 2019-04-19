package com.kkbox.hellomusic

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import com.kkbox.hellomusic.page.Home
import com.kkbox.hellomusic.page.Playlist
import com.kkbox.hellomusic.rule.HelloMusicActivityRule
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import org.junit.After

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class HomePageTest {

    @get:Rule
    val activityRule = HelloMusicActivityRule(MainActivity::class.java)

    @Rule @JvmField
    var reportHelper: ReportHelper = Factory.getReportHelper()

    @After
    fun tearDown() {
        reportHelper.label("Stopping App")
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.kkbox.hellomusic", appContext.packageName)
    }

    @Test
    fun openAppShow20HotPlaylists() {
        Home().checkPlaylistNumber(20, activityRule.activity)
    }

    @Test
    fun show10Albums() {
        Home().checkAlbumNumber(10, activityRule.activity)
    }

    @Test
    fun playlistHasPlaylistName() {
        Home().checkPlaylistTitle(1, "華語速爆新歌")
    }

    @Test
    fun playlistHasCuratorName() {
        Home().checkCuratorName(1, "KKBOX 華語小編")
    }

    @Test
    fun openCpopPlaylist() {
        Home().openPlaylist("華語速爆新歌")
        Playlist().checkHasTitle("華語速爆新歌")
    }
}
