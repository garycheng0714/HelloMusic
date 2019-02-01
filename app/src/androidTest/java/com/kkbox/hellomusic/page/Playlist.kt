package com.kkbox.hellomusic.page

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.view.View
import com.kkbox.hellomusic.R
import org.hamcrest.Matcher
import com.kkbox.hellomusic.matcher.CustomMatcher.Companion.hasToolbarTitle

class Playlist {

    private val toolbar: Matcher<View> = withId(R.id.toolbar)

    fun checkHasTitle(expectedTitle: String) {
        onView(toolbar).check(matches(hasToolbarTitle(expectedTitle)))
    }
}