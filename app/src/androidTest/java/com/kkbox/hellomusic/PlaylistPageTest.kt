package com.kkbox.hellomusic

import android.support.test.rule.ActivityTestRule
import com.kkbox.hellomusic.page.Home
import com.kkbox.hellomusic.page.Playlist
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PlaylistPageTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

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