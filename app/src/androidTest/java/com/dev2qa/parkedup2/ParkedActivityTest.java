package com.dev2qa.parkedup2;

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
public class ParkedActivityTest {
//    @Rule
//    // third parameter is set to false which means the activity is not started automatically
//    public ActivityTestRule<ParkedActivity> rule =
//            new ActivityTestRule(ParkedActivity.class, true, false);

    @Rule
    public ActivityTestRule<ParkedActivity> ParkedActivityRule =
            new ActivityTestRule<>(ParkedActivity.class);

    @Test
    public void viewsMustBeVisibleTest()
    {
        onView(withId(R.id.deleteButton)).check(matches(isCompletelyDisplayed()));
        onView(withId(R.id.exit)).check(matches(isCompletelyDisplayed()));

    }

    @Test
    public void exitButtonTest(){
        onView(withId(R.id.exit)).perform(click());
    }

    @Test
    public void deleteSpotButtonTest(){
        onView(withId(R.id.deleteButton)).perform(click());
    }

//    @Test
//    public void popupViewsMustBeVisibleTest(){
//        onView(withId(android.R.id.text1))
//                .check(matches(withText("Are you sure you want to delete? (This will be permanent)")))
//                .check(matches(isDisplayed()));
//
//        onView(withId(android.R.string.no))
//                .check(matches(isDisplayed()));
//
//        onView(withId(android.R.string.yes))
//                .check(matches(isDisplayed()));
//    }

//    @Test
//    public void deleteSpotPopupYesTest(){
//        int titleId= ParkedActivityRule.getActivity().getResources().getIdentifier( "abc", "id", "com.dev2qa.parkedup2" );
//        onView(withId(titleId))
//                .inRoot(isDialog())
//                .check(matches(withText("abc")))
//                .check(matches(isDisplayed()));
//
////        onView(withContentDescription("Are you sure you want to delete? (This will be permanent)"))
////                .inRoot(RootMatchers.isPlatformPopup())
////                .perform(click());
//
////        onView(withId(R.id.deleteButton)).perform(click());
////        onView(withText("yes")).inRoot(isDialog()) // <---
////                .check(matches(isDisplayed()))
////                .perform(click());
//
////        onView(withText("Are you sure you want to delete? (This will be permanent)")).perform(pressBack());
//
//        onView(withText("Yes")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
//    }

//    @Test
//    public void deleteSpotPopupNoTest(){
//        onView(withContentDescription("Are you sure you want to delete? (This will be permanent)"))
//                .inRoot(RootMatchers.isPlatformPopup())
//                .perform(click());
//    }
//
//    @Test
//    public void exitPopupYesTest () {
//        onView(withContentDescription("Are you sure you want to exit? (This will be delete your parked location)"))
//                .inRoot(RootMatchers.isPlatformPopup())
//                .perform(click());
//
//    }
//
//    @Test
//    public void exitPopupNoTest () {
//        onView(withContentDescription("Are you sure you want to exit? (This will be delete your parked location)"))
//                .inRoot(RootMatchers.isPlatformPopup())
//                .perform(click());
//    }


}
