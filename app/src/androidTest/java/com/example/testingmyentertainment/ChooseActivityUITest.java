package com.example.testingmyentertainment;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ChooseActivityUITest {

    @Rule
    public ActivityScenarioRule<ChooseActivity> activityScenarioRule =
            new ActivityScenarioRule<>(ChooseActivity.class);

    @Test
    public void testMusicButtonDisplayed() {
        // Check if the music button is displayed
        Espresso.onView(withId(R.id.btnmusic)).check(matches(isDisplayed()));
    }

    @Test
    public void testFilmButtonDisplayed() {
        // Check if the film button is displayed
        Espresso.onView(withId(R.id.btnflm)).check(matches(isDisplayed()));
    }

    @Test
    public void testLogoutButtonDisplayed() {
        // Check if the logout TextView is displayed
        Espresso.onView(withId(R.id.logout)).check(matches(isDisplayed()));
    }

    @Test
    public void testMusicButton() {
        // Click on the music button
        Espresso.onView(withId(R.id.btnmusic)).perform(ViewActions.click());

        // Check if MainActivity is launched
        Espresso.onView(withId(R.id.headerText)).check(matches(isDisplayed()));
    }

    @Test
    public void testFilmButton() {
        // Click on the film button
        Espresso.onView(withId(R.id.btnflm)).perform(ViewActions.click());

        // Check if MainActivity1 is launched
        Espresso.onView(withId(R.id.playerView)).check(matches(isDisplayed()));
    }

    @Test
    public void testLogoutButton() {
        // Click on the logout button
        Espresso.onView(withId(R.id.logout)).perform(ViewActions.click());

        // Check if LoginActivity is launched
        Espresso.onView(withId(R.id.email)).check(matches(isDisplayed()));
    }
}
