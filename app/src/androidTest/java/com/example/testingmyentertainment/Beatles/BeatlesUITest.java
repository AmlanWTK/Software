package com.example.testingmyentertainment.Beatles;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.init;
import static androidx.test.espresso.intent.Intents.release;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

import com.example.testingmyentertainment.Musics.PlayerActivity;
import com.example.testingmyentertainment.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BeatlesUITest {

    @Rule
    public ActivityScenarioRule<Beatles> activityRule = new ActivityScenarioRule<>(Beatles.class);
//check krtce listview ase kina
    @Test
    public void testListViewDisplayed() {
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }
    // check kortce searchview ase kina

    @Test
    public void testSearchViewDisplayed() {
        onView(withId(R.id.search_view)).check(matches(isDisplayed()));
    }
//listview empty kina and favourite_toggole button ase kina
    @Test
    public void testToggleFavorite() {
        onData(anything())//data find in adapter
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .onChildView(withId(R.id.favorite_toggle))
                .perform(click())
                .check(matches(isDisplayed())); // Ensure it is displayed and toggled
    }


//listview click a playayer activity kaj korse kina
    @Test
    public void testClickSongStartsPlayerActivity() {
        init();
        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());

        intended(hasComponent(PlayerActivity.class.getName()));
        release();
    }
//on click a favouritesongs avtivity kaj korse kina
    @Test
    public void testShowFavoritesButton() {
        init();
        onView(withId(R.id.btnShowFavorites)).perform(click());
        intended(hasComponent(FavoriteSongsActivity.class.getName()));
        release();
    }
}
