package com.example.shoestringstudio


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AutomatedGUITest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun automatedGUITest() {
        val materialButton = onView(
            allOf(
                withId(R.id.button_add_project), withText("New Project"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val materialButton2 = onView(
            allOf(
                withId(R.id.projectEditButton), withText("Edit"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.button_add_track), withText("Add Track"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton3.perform(click())

        val materialTextView = onView(
            allOf(
                withId(android.R.id.title), withText("Choose from storage"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        val materialButton4 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val materialButton5 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())

        val materialButton6 = onView(
            allOf(
                withId(R.id.button_add_track), withText("Add Track"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())

        val materialButton7 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton7.perform(click())

        val materialButton8 = onView(
            allOf(
                withId(R.id.button_add_track), withText("Add Track"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton8.perform(click())

        val materialTextView2 = onView(
            allOf(
                withId(android.R.id.title), withText("Choose from storage"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView2.perform(click())

        val materialButton9 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton9.perform(click())

        val materialButton10 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton10.perform(click())

        val materialButton11 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton11.perform(click())

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val materialButton12 = onView(
            allOf(
                withId(R.id.projectEditButton), withText("Edit"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton12.perform(click())

        val materialButton13 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton13.perform(click())

        val materialButton14 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton14.perform(click())

        val materialButton15 = onView(
            allOf(
                withId(R.id.button_add_track), withText("Add Track"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton15.perform(click())

        val materialTextView3 = onView(
            allOf(
                withId(android.R.id.title), withText("Choose from storage"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialTextView3.perform(click())

        val materialButton16 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton16.perform(click())

        val materialButton17 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton17.perform(click())

        val appCompatImageButton2 = onView(
            allOf(
                withId(R.id.buttonDelete),
                childAtPosition(
                    allOf(
                        withId(R.id.trackHolder),
                        childAtPosition(
                            withId(R.id.trackDisplay),
                            2
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        val appCompatImageButton3 = onView(
            allOf(
                withId(R.id.buttonDelete),
                childAtPosition(
                    allOf(
                        withId(R.id.trackHolder),
                        childAtPosition(
                            withId(R.id.trackDisplay),
                            1
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatImageButton3.perform(click())

        val materialButton18 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton18.perform(click())

        val materialButton19 = onView(
            allOf(
                withId(R.id.button_play_pause), withText("Play/Pause"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.myNavHostFragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton19.perform(click())

        val appCompatImageButton4 = onView(
            allOf(
                withId(R.id.buttonDelete),
                childAtPosition(
                    allOf(
                        withId(R.id.trackHolder),
                        childAtPosition(
                            withId(R.id.trackDisplay),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatImageButton4.perform(click())

        val appCompatImageButton5 = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton5.perform(click())

        val materialButton20 = onView(
            allOf(
                withId(R.id.projectDeleteButton), withText("Delete"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.recyclerView),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton20.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
