package com.example.shoestringstudio

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<MainMenuAdapter.ProjectHolder>(0, click()))
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
        onView((withId(R.id.recyclerView)))
            .perform(actionOnItem<MainMenuAdapter.ProjectHolder>(hasDescendant(withText("projectDeleteButton")), click()))
    }


}