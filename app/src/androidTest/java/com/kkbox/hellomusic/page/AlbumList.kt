package com.kkbox.hellomusic.page

import com.agoda.kakao.navigation.KNavigationView
import com.agoda.kakao.screen.Screen
import com.kkbox.hellomusic.R

class AlbumList: Screen<AlbumList>() {

    val toolbar = KNavigationView {
        withText(R.string.albumRecyclerViewTitle)
        withParent { withId(R.id.toolbar) }
    }

    fun checkTitleDisplayed() {
        toolbar { isDisplayed() }
    }
}