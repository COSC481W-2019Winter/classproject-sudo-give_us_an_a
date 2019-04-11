package com.dev2qa.parkedup;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ParkedActivityTest {
    @Rule
    public ActivityTestRule<ParkedActivity> ParkedActivityRule =
            new ActivityTestRule<>(ParkedActivity.class);

    @Test
    public void viewsMustBeVisibleTest()
    {
        onView(withId(R.id.deleteButton)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.exit)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.menubutton)).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void exitButtonTest(){
        onView(withId(R.id.exit)).perform(click());
    }

    @Test
    public void deleteSpotButtonTest(){
        onView(withId(R.id.deleteButton)).perform(click());
    }

    @Test
    public void deleteSpotPopupYesTest(){
       onView(withId(R.id.deleteButton)).perform(click());
       onView(withText("Are you sure you want to delete? (This will be permanent)")).check(matches(isDisplayed()));
       onView(withText("Yes")).perform(click());
  }

    @Test
    public void deleteSpotPopupNoTest(){
        onView(withId(R.id.deleteButton)).perform(click());
        onView(withText("Are you sure you want to delete? (This will be permanent)")).check(matches(isDisplayed()));
        onView(withText("No")).perform(click());
    }

    @Test
    public void exitPopupYesTest() {
        onView(withId(R.id.exit)).perform(click());
        onView(withText("Are you sure you want to exit? (This will be delete your parked location)")).check(matches(isDisplayed()));
        onView(withText("Yes")).perform(click());
    }

    @Test
    public void exitPopupNoTest(){
        onView(withId(R.id.exit)).perform(click());
        onView(withText("Are you sure you want to exit? (This will be delete your parked location)")).check(matches(isDisplayed()));
        onView(withText("No")).perform(click());
    }
    @Test
    public void menuButtonClickTest() {
        onView(withId(R.id.menubutton)).perform(click());
    }
}
