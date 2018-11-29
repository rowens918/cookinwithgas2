package com.example.android.cookinwithgas;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestIntent {

    @Rule
    public IntentsTestRule<RecipeListActivity> mActivityRule = new IntentsTestRule<>(RecipeListActivity.class);

    @Before
    public void stubAllExternalIntents() {
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Test
    public void intentTest() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recipe_list)).perform(RecyclerViewActions.scrollToPosition(3));

        onView(withId(R.id.recipe_list)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

        intended(hasExtraWithKey(RecipeDetailFragment.ARG_ITEM_ID));

        onView(withId(R.id.next_step)).perform(ViewActions.click());

        onView(withId(R.id.tv_recipe_step_id)).check(matches(withText("1")));

        onView(withId(R.id.back_step)).perform(ViewActions.click());

        onView(withId(R.id.tv_recipe_step_id)).check(matches(withText("0")));

        onView(withId(R.id.rv_step_list)).perform(RecyclerViewActions.scrollToPosition(5));

        onView(withId(R.id.rv_step_list)).perform(RecyclerViewActions.actionOnItemAtPosition(5, click()));

        onView(withId(R.id.tv_recipe_step_id)).check(matches(withText("4")));

    }
}
