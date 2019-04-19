package com.kkbox.hellomusic.viewAction

import android.app.Activity
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.GeneralClickAction
import android.support.test.espresso.action.GeneralLocation
import android.support.test.espresso.action.Press
import android.support.test.espresso.action.Tap
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.view.InputDevice
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import org.hamcrest.Matcher

class CustomViewAction {

    companion object {

        fun clickChildView(viewId: Int): ViewAction {

            val clickAction = GeneralClickAction(
                Tap.SINGLE,
                GeneralLocation.VISIBLE_CENTER,
                Press.FINGER,
                InputDevice.SOURCE_UNKNOWN,
                MotionEvent.BUTTON_PRIMARY
            )

            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return isAssignableFrom(View::class.java)
                }

                override fun getDescription(): String {
                    return "Click child view ${withId(viewId)}"
                }

                override fun perform(uiController: UiController, view: View) {
                    val targetView = view.findViewById<View>(viewId)

                    clickAction.perform(uiController, targetView)
                }
            }
        }

        fun getText(viewId: Int, stringHolder: ArrayList<String>): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return isAssignableFrom(TextView::class.java)
                }

                override fun getDescription(): String {
                    return "Get text from TextView"
                }

                override fun perform(uiController: UiController, view: View) {
                    val textView = view.findViewById<TextView>(viewId)

                    stringHolder.add(textView.text.toString())
                }
            }
        }
    }
}