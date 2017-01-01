package com.example.liudmula.myapplication;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.example.liudmula.myapplication.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest2 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        ViewInteraction appCompatImageButton = onView(
allOf(withContentDescription("Open navigation drawer"),
withParent(withId(R.id.toolbar)),
isDisplayed()));
        appCompatImageButton.perform(click());
        
        ViewInteraction floatingActionButton = onView(
allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());
        
        ViewInteraction appCompatEditText = onView(
allOf(withId(R.id.etAddWord), isDisplayed()));
        appCompatEditText.perform(replaceText("v"), closeSoftKeyboard());
        
        ViewInteraction appCompatButton = onView(
allOf(withId(R.id.btn_Add_word), withText("ДОДАТИ"), isDisplayed()));
        appCompatButton.perform(click());
        
        ViewInteraction floatingActionButton2 = onView(
allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton2.perform(click());
        
        ViewInteraction appCompatEditText2 = onView(
allOf(withId(R.id.etAddWord), isDisplayed()));
        appCompatEditText2.perform(replaceText("g"), closeSoftKeyboard());
        
        ViewInteraction appCompatEditText3 = onView(
allOf(withId(R.id.etAddDesc), isDisplayed()));
        appCompatEditText3.perform(replaceText("rgththh"), closeSoftKeyboard());
        
        ViewInteraction appCompatButton2 = onView(
allOf(withId(R.id.btn_Add_word), withText("ДОДАТИ"), isDisplayed()));
        appCompatButton2.perform(click());
        
        ViewInteraction floatingActionButton3 = onView(
allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton3.perform(click());
        
        ViewInteraction editText = onView(
allOf(withId(R.id.etAddWord), withText("Enter word"),
childAtPosition(
childAtPosition(
withId(android.R.id.content),
0),
0),
isDisplayed()));
        editText.check(matches(withText("Enter word")));
        
        }

        private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
