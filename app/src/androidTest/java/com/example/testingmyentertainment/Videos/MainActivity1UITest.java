package com.example.testingmyentertainment.Videos;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.testingmyentertainment.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainActivity1UITest {

    @Rule
    public ActivityScenarioRule<MainActivity1> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity1.class);

    @Test
    public void testPlayerViewDisplayed() {
        // Check if the player view is displayed
        Espresso.onView(withId(R.id.playerView)).check(matches(isDisplayed()));
    }

    @Test
    public void testGridViewDisplayed() {
        // Check if the video grid view is displayed
        Espresso.onView(withId(R.id.videoGridView)).check(matches(isDisplayed()));
    }



}
