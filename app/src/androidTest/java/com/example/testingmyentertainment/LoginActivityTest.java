package com.example.testingmyentertainment;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);
    @Test
    public void testInputFieldsAreDisplayed() {
        // Check if the email input field is displayed
        onView(withId(R.id.email))
                .check(matches(isDisplayed()));

        // Check if the password input field is displayed
        onView(withId(R.id.password))
                .check(matches(isDisplayed()));

        // Check if the registration button is displayed
        onView(withId(R.id.login))
                .check(matches(isDisplayed()));
    }


    @Test
    public void testRegButtonClickNavigatesToRegistrationActivity() {
        // Click on the registration button
        Espresso.onView(withId(R.id.Regid)).perform(ViewActions.click());

        // Verify that any view from RegistrationActivity layout is displayed
        Espresso.onView(withId(R.id.email)).check(matches(isDisplayed()));
    }
}

