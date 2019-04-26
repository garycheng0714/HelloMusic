package com.kkbox.hellomusic.page

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.navigation.KNavigationView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.kkbox.hellomusic.R
import org.hamcrest.Matcher

class AlbumList: Screen<AlbumList>() {

    private class Album(parent: Matcher<View>) : KRecyclerItem<Album>(parent) {
        val cover: KImageView = KImageView(parent) { withId(R.id.album_cover) }
        val albumTitle: KTextView = KTextView(parent) { withId(R.id.album_title) }
    }

    private val toolbarTitle = KNavigationView {
        withText(R.string.albumRecyclerViewTitle)
        withParent { withId(R.id.toolbar) }
    }

    private val newAlbumList = KRecyclerView({
        withId(R.id.activity_album_list)
    }, itemTypeBuilder = {
        itemType(::Album)
    })

    fun checkNewAlbumTitleDisplayed() {
        toolbarTitle { isDisplayed() }
    }

    fun checkAlbumContentDisplayed() {
        newAlbumList { firstChild<Album> {
            albumTitle { isDisplayed() }
            cover { isDisplayed() }
        }}
    }
}