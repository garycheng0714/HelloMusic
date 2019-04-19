package com.kkbox.hellomusic

import com.kkbox.hellomusic.page.Home
import com.kkbox.hellomusic.page.Playlist
import com.kkbox.hellomusic.rule.HelloMusicActivityRule
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PlaylistPageTest {

    @get:Rule
    val activityRule = HelloMusicActivityRule(MainActivity::class.java)

    @Rule @JvmField
    var reportHelper: ReportHelper = Factory.getReportHelper()

    @After
    fun tearDown() {
        reportHelper.label("Stopping App")
    }

    @Before
    fun openPlaylist() {
        Home().openPlaylist("華語速爆新歌")
    }

    @Test
    fun songHasName() {
        Playlist().checkNotDefaultSongNameAtPosition(0)
    }

    @Test
    fun artistHasName() {
        Playlist().checkNotDefaultArtistNameAtPosition(0)
    }
}