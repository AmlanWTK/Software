package com.example.testingmyentertainment;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.testingmyentertainment.Musics.folk.Indian_folk;
import com.example.testingmyentertainment.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class IndianFolkUITest {

    @Rule
    public ActivityScenarioRule<Indian_folk> activityScenarioRule =
            new ActivityScenarioRule<>(Indian_folk.class);

    @Test
    public void testActivityInView() {
        Espresso.onView(withId(R.id.listView)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.search_view)).check(matches(isDisplayed()));
    }

    @Test
    public void testSearchView() {
        // Perform type text action on the search view
        Espresso.onView(withId(R.id.search_view))
                .perform(ViewActions.typeText("Resham Firiri"));

        // Check if the typed text is displayed correctly in the search view
        Espresso.onView(withId(R.id.search_view))
                .check(matches(ViewMatchers.withText("Resham Firiri")));
    }

    @Test
    public void testListViewItemClick() {
        // Click on the first item in the list view
        Espresso.onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());

        // Verify that the PlayerActivity is displayed
        Espresso.onView(withId(R.id.jcplayer)).check(matches(isDisplayed()));
    }

    @Test
    public void testListViewScroll() {
        // Scroll to the bottom of the list view
        Espresso.onView(withId(R.id.listView)).perform(ViewActions.swipeUp());

        // Verify that the last item in the list view is displayed
        Espresso.onView(withText("Hori Khelan Ka")).check(matches(isDisplayed()));
    }


}
