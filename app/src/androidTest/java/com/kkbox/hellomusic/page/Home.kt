package com.kkbox.hellomusic.page

import android.app.Activity
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import com.kkbox.hellomusic.R
import com.kkbox.hellomusic.matcher.CustomMatcher.Companion.atPosition
import com.kkbox.hellomusic.viewAction.CustomViewAction.Companion.clickChildView
import com.kkbox.hellomusic.viewAction.CustomViewAction.Companion.getText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Assert

class Home {

    private val albumNameId = R.id.album_title
    private val hitPlaylistTitleId = R.id.playlist_title
    private val hitPlaylistCuratorId = R.id.curator_name

    private val albumRecyclerView: Matcher<View> = withId(R.id.new_album_recyclerview)
    private val hitPlaylistRecyclerView: Matcher<View> = withId(R.id.hit_playlist_recyclerview)

    fun checkAlbumNumber(expectedNumber: Int, activity: Activity) {
        val recyclerView: RecyclerView = activity.findViewById(R.id.new_album_recyclerview)

        Assert.assertEquals(recyclerView.adapter!!.itemCount, expectedNumber)
    }

    fun checkPlaylistNumber(expectedNumber: Int, activity: Activity) {
        val recyclerView: RecyclerView = activity.findViewById(R.id.hit_playlist_recyclerview)

        Assert.assertEquals(recyclerView.adapter!!.itemCount - 1, expectedNumber)
    }

    fun checkPlaylistTitle(index: Int, name: String) {
        onView(hitPlaylistRecyclerView).check(matches(allOf(atPosition(index, hitPlaylistTitleId, withText(name)), isDisplayed())))
    }

    fun checkCuratorName(index: Int, name: String) {
        onView(hitPlaylistRecyclerView).check(matches(allOf(atPosition(index, hitPlaylistCuratorId, withText(name)), isDisplayed())))
    }

    fun getAlbumName(position: Int, activity: Activity): String {
        val stringHolder = ArrayList<String>()

        onView(albumRecyclerView)
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(position, getText(activity, albumNameId, stringHolder)))

        return stringHolder[0]
    }

    fun openAlbum(index: Int) {
        onView(albumRecyclerView)
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(index, click()))
    }

    fun openPlaylist(playlistName: String) {
        onView(hitPlaylistRecyclerView)
            .perform(
                RecyclerViewActions.actionOnItem<ViewHolder>(
                    hasDescendant(
                        withText(
                            playlistName
                        )
                    ), clickChildView(hitPlaylistTitleId)
                ))
    }
}