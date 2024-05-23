package com.example.testingmyentertainment;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class RegistrationActivityTest {

    @Rule
    public ActivityScenarioRule<RegistrationActivity> activityScenarioRule =
            new ActivityScenarioRule<>(RegistrationActivity.class);

    @Test
    public void testInputFieldsAreDisplayed() {
        // Check if the email input field is displayed
        onView(withId(R.id.email))
                .check(matches(isDisplayed()));

        // Check if the password input field is displayed
        onView(withId(R.id.passwd))
                .check(matches(isDisplayed()));

        // Check if the registration button is displayed
        onView(withId(R.id.btnregister))
                .check(matches(isDisplayed()));
    }

}
