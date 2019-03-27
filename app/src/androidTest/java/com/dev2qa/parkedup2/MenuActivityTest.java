package com.dev2qa.parkedup2;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class MenuActivityTest {
    @Rule
    public ActivityTestRule<MenuActivity> MenuActivityRule =
            new ActivityTestRule<>(MenuActivity.class);

    @Test
    public void viewsMustBeVisibleTest()
    {
        onView(withId(R.id.notify)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.MIKItoggle)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.home)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.exit)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.aboutButton)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void notifyToggleClickTest(){
        onView(withId(R.id.notify)).perform(click());
    }
    @Test
    public void MIKItoggleToggleClickTest(){
        onView(withId(R.id.MIKItoggle)).perform(click());
    }
    @Test
    public void homeButtonClickTest(){
        onView(withId(R.id.home)).perform(click());
    }
    @Test
    public void exitButtonClickTest(){
        onView(withId(R.id.exit)).perform(click());
    }
    @Test
    public void aboutButtonClickTest(){
        onView(withId(R.id.aboutButton)).perform(click());
    }

}
