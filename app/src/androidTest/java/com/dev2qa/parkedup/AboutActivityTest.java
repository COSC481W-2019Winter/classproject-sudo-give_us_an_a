package com.dev2qa.parkedup;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class AboutActivityTest {
    @Rule
    public ActivityTestRule<AboutActivity> AboutActivityRule =
            new ActivityTestRule<>(AboutActivity.class);

    @Test
    public void viewsMustBeVisibleTest()
    {
        onView(withId(R.id.backButton)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void backButtonClickTest(){
        onView(withId(R.id.backButton)).perform(click());
    }
}
