package uk.co.hughingram.ethscan

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.co.hughingram.ethscan.network.ApiClientProvider
import uk.co.hughingram.ethscan.view.MainActivity


@RunWith(AndroidJUnit4::class)
class UITests {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        val application = InstrumentationRegistry.getTargetContext().applicationContext
        (application as ApiClientProvider).apiClient = MockApiClient()
        activityTestRule.launchActivity(null)
    }

    @Test
    fun openTransactionDetails() {
        /** WHEN - I click on the first transaction in the list **/
        val listIndexToClick = 0
        val listItemMatcher = nthChildOf(withId(R.id.transaction_adapter), listIndexToClick)
        val selectedTransactionHash = getText(
            allOf(withParent(listItemMatcher), withId(R.id.hash))
        )
        onView(listItemMatcher).perform(click())

        /** THEN - the details page should be opened for that same item. **/
        onView(withId(R.id.transaction_hash))
            .check(matches(withText(selectedTransactionHash)))
    }
}

fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("with $childPosition child of type parentMatcher")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item?.parent !is ViewGroup) {
            return parentMatcher.matches(item?.parent)
        }
        val group = item.parent as ViewGroup
        return parentMatcher.matches(item.parent) && group.getChildAt(childPosition) == item
    }
}

fun getText(matcher: Matcher<View>): String {

    var string: String? = null
    onView(matcher).perform(object : ViewAction {
        override fun getConstraints(): Matcher<View> = isAssignableFrom(TextView::class.java)

        override fun getDescription(): String = "getting text from a TextView"

        override fun perform(uiController: UiController, view: View) {
            val tv = view as TextView //Save, because of check in getConstraints()
            string = tv.text.toString()
        }
    })
    return string ?: ""
}
