package com.example.testingmyentertainment;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.testingmyentertainment.Musics.folk.bengali_folk;
import com.example.testingmyentertainment.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class BengaliFolkUITest {

    @Rule
    public ActivityScenarioRule<bengali_folk> activityScenarioRule =
            new ActivityScenarioRule<>(bengali_folk.class);

    @Test
    public void testActivityInView() {
        Espresso.onView(withId(R.id.listView)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.search_view)).check(matches(isDisplayed()));
    }





}
