package com.kkbox.hellomusic.page

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import com.kkbox.hellomusic.R
import com.kkbox.hellomusic.matcher.CustomMatcher.Companion.atPosition
import org.hamcrest.Matcher
import com.kkbox.hellomusic.matcher.CustomMatcher.Companion.hasToolbarTitle
import org.hamcrest.Matchers.not

class Playlist: Basic() {

    private val playlistRecyclerView: Matcher<View> = withId(R.id.playlist_recyclerview)

    fun checkNotDefaultSongNameAtPosition(index: Int) {
        onView(playlistRecyclerView).check(matches(not(atPosition(index, R.id.song_name, withText(R.string.song_name)))))
    }

    fun checkNotDefaultArtistNameAtPosition(index: Int) {
        onView(playlistRecyclerView).check(matches(not(atPosition(index, R.id.artist_name, withText(R.string.artist_name)))))
    }
}