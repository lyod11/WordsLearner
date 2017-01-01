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
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatImageButton = onView(
allOf(withContentDescription("Open navigation drawer"),
withParent(withId(R.id.toolbar)),
isDisplayed()));
        appCompatImageButton.perform(click());
        
        ViewInteraction appCompatImageButton2 = onView(
allOf(withContentDescription("Open navigation drawer"),
withParent(withId(R.id.toolbar)),
isDisplayed()));
        appCompatImageButton2.perform(click());
        
        ViewInteraction appCompatCheckedTextView = onView(
allOf(withId(R.id.design_menu_item_text), withText("Словники"), isDisplayed()));
        appCompatCheckedTextView.perform(click());
        
        ViewInteraction appCompatEditText = onView(
allOf(withId(R.id.etTypeWrd), isDisplayed()));
        appCompatEditText.perform(click());
        
        ViewInteraction floatingActionButton = onView(
allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());
        
        ViewInteraction appCompatEditText2 = onView(
allOf(withId(R.id.etAddWord), isDisplayed()));
        appCompatEditText2.perform(replaceText("h"), closeSoftKeyboard());
        
        ViewInteraction appCompatEditText3 = onView(
allOf(withId(R.id.etAddDesc), isDisplayed()));
        appCompatEditText3.perform(replaceText("rrr"), closeSoftKeyboard());
        
        ViewInteraction appCompatButton = onView(
allOf(withId(R.id.btn_Add_word), withText("ДОДАТИ"), isDisplayed()));
        appCompatButton.perform(click());
        
        ViewInteraction appCompatImageButton3 = onView(
allOf(withContentDescription("Open navigation drawer"),
withParent(withId(R.id.toolbar)),
isDisplayed()));
        appCompatImageButton3.perform(click());
        
        ViewInteraction appCompatCheckedTextView2 = onView(
allOf(withId(R.id.design_menu_item_text), withText("Словники"), isDisplayed()));
        appCompatCheckedTextView2.perform(click());
        
        ViewInteraction gridLayout = onView(
allOf(childAtPosition(
withId(R.id.lvDict),
0),
isDisplayed()));
        gridLayout.perform(click());
        
        ViewInteraction appCompatButton2 = onView(
allOf(withId(R.id.btn_Delete_word), withText("Видалити"), isDisplayed()));
        appCompatButton2.perform(click());
        
        ViewInteraction appCompatImageButton4 = onView(
allOf(withContentDescription("Open navigation drawer"),
withParent(withId(R.id.toolbar)),
isDisplayed()));
        appCompatImageButton4.perform(click());
        
        ViewInteraction appCompatCheckedTextView3 = onView(
allOf(withId(R.id.design_menu_item_text), withText("Тренування"), isDisplayed()));
        appCompatCheckedTextView3.perform(click());
        
        ViewInteraction appCompatButton3 = onView(
allOf(withId(R.id.btn_w_t), withText("Слово-переклад"), isDisplayed()));
        appCompatButton3.perform(click());
        
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
