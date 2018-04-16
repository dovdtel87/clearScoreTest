package com.example.davidmartinezgarcia.clearscoreproject

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.davidmartinezgarcia.clearscoreproject.feature.scoresummary.view.ScoreSummaryActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by david.martinezgarcia on 14/04/2018.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ScoreSummaryActivityTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<ScoreSummaryActivity> = ActivityTestRule(ScoreSummaryActivity::class.java)


    @Test
    fun checkTestScoreIsDisplayed() {
        onView(withId(R.id.donut_view)).check(matches(isDisplayed()))
        onView(withId(R.id.text_score)).check(matches(isDisplayed()))
    }

}