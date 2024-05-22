package com.example.testingmyentertainment.Beatles;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.testingmyentertainment.MainActivity;
import com.example.testingmyentertainment.Musics.mohinerghoraguli;
import com.example.testingmyentertainment.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CourseGVAdapterIntegrationTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testGridViewDisplayAndClick() {
        // Initialize Intents
        init();

  //check if the first one has the same name
        onData(anything())
                .inAdapterView(withId(R.id.idGVcourses1))
                .atPosition(0)
                .onChildView(withId(R.id.idTVCourse))
                .check(matches(withText("Classical")));

        // Perform a click
        onData(anything())
                .inAdapterView(withId(R.id.idGVcourses1))
                .atPosition(0)
                .perform(click());

        //expected ta open hosse kina
        intended(hasComponent( mohinerghoraguli.class.getName()));

        // Release Intents
        release();
    }
}
