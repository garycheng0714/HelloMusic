package com.kkbox.hellomusic.matcher

import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher

class CustomMatcher {

    companion object {

        fun hasText(): Matcher<View> {
            return object : BoundedMatcher<View, TextView>(TextView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("Check target view has any text")
                }

                override fun matchesSafely(textView: TextView): Boolean {
                    return textView.text.isNotEmpty()
                }
            }
        }

        fun hasToolbarTitle(title: String): Matcher<View> {
            return object : BoundedMatcher<View, Toolbar>(Toolbar::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("Check tool bar title have text: ")
                    description.appendText(title)
                }

                override fun matchesSafely(toolbar: Toolbar): Boolean {
                    return title == toolbar.title
                }
            }
        }

        fun atPosition(index: Int, targetViewId: Int, expectedMatcher: Matcher<View>): Matcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("Check position $index match expected condition: ")
                    description.appendText(expectedMatcher.toString())
                }

                override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(index) ?: return false
                    Log.d("Gary", viewHolder.itemView.findViewById<TextView>(targetViewId).text.toString())
                    return expectedMatcher.matches(viewHolder.itemView.findViewById(targetViewId))
                }
            }
        }
    }
}