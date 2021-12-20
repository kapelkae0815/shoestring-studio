package com.example.shoestringstudio

import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnHolderItem
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.StringEndsWith.endsWith
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class GUITest {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Navigates through app by creating a project and then entering the editing fragment, then exiting back to the mainMenuFragment
     */
    @Test
    fun fragmentNavigation() {

        onView(withId(R.id.title_text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.button_add_project)).check(matches(isDisplayed()))
        onView(withId(R.id.button_add_project)).check(matches(withText("New Project")))


        onView(withId(R.id.button_add_project)).perform(click())
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<MainMenuAdapter.ProjectHolder>(1, click()))
        onView(withId(R.id.title_text)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.title_text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }
    // Need to have a project with tracks in it, doesn't play sound because of null exception
    @Test
    fun playingSong() {
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<MainMenuAdapter.ProjectHolder>(0, click()))
        onView(withId(R.id.button_play_pause))
    }
    // Doesn't work
    @Test
    fun deletingProject() {
        onView(allOf(withId(R.id.recyclerView), withParentIndex(R.id.buttonDeleteProject)))
            .perform(actionOnItemAtPosition<MainMenuAdapter.ProjectHolder>(0, click()))
    }

}