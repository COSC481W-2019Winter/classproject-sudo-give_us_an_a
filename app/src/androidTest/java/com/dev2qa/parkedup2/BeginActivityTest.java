package com.dev2qa.parkedup2;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BeginActivityTest {
    @Rule
    public ActivityTestRule<BeginActivity> BeginActivityRule =
            new ActivityTestRule<>(BeginActivity.class);

    @Test
    public void viewsMustBeVisibleTest()
    {
        onView(withId(R.id.parkButton)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.exit)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void parkButtonClickTest(){
        onView(withId(R.id.parkButton)).perform(click());
    }

    @Test
    public void exitButtonClickTest() {
        onView(withId(R.id.exit)).perform(click());
    }
}
