package com.example.testingmyentertainment;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class MainActivityUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void testGridViewItemsDisplayed() {
        // Check if the GridView items are displayed
        Espresso.onView(withId(R.id.idGVcourses1)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.idGVcourses2)).check(matches(isDisplayed()));

    }
    @Test
    public void testSearchFunctionality() {
        // Type in the search view
        Espresso.onView(ViewMatchers.withId(R.id.search_view))
                .perform(ViewActions.typeText("Beatles"), ViewActions.closeSoftKeyboard());

        // Ensure that the first item in gridView1 matches the search query
        Espresso.onView(ViewMatchers.withId(R.id.idGVcourses1))
                .check(ViewAssertions.matches(
                        ViewMatchers.hasDescendant(ViewMatchers.withText("Beatles"))
                ));
    }



}
