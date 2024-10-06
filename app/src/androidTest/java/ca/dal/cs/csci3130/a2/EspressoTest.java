package ca.dal.cs.csci3130.a2;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.hamcrest.Matchers.allOf;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    public ActivityScenario<MainActivity> activityScenario;

    @Before
    public void setup() {
        activityScenario = ActivityScenario.launch(MainActivity.class);
        activityScenario.onActivity(activity -> {
            activity.loadRoleSpinner();
            activity.setupRegistrationButton();
            activity.initializeDatabaseAccess();
        });
    }

    /*** UAT-II**/
    @Test
    public void checkIfBannerIDIsEmpty() {
        onView(withId(R.id.bannerIDBox)).perform(typeText(""));
        onView(withId(R.id.emailBox)).perform(typeText("example.tutor@dal.ca"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Tutor"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_BANNER_ID)));
    }

    /*** UAT-III**/
    @Test
    public void checkIfBannerIDIsValid() {
        onView(withId(R.id.emailBox)).perform(typeText("example.student@dal.ca"));
        onView(withId(R.id.bannerIDBox)).perform(typeText("B00882356"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Student"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    /*** UAT-III**/
    @Test
    public void checkIfBannerIDIsInvalid() {
        onView(withId(R.id.emailBox)).perform(typeText("example.tutor@dal.ca"));
        onView(withId(R.id.bannerIDBox)).perform(typeText("B01881234"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Tutor"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_BANNER_ID)));
    }

    /*** UAT-IV**/
    @Test
    public void checkIfEmailIsValid() {
        onView(withId(R.id.bannerIDBox)).perform(typeText("b00881456"));
        onView(withId(R.id.emailBox)).perform(typeText("example.student@dal.ca"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Student"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    /*** UAT-IV**/
    @Test
    public void checkIfEmailIsInvalid() {
        onView(withId(R.id.bannerIDBox)).perform(typeText("B00882378"));
        onView(withId(R.id.emailBox)).perform(typeText("example.tutor.dal.ca"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Tutor"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_EMAIL_ADDRESS)));
    }

    /*** UAT-IV**/
    @Test
    public void checkIfEmailIsNotFromDAL() {

        onView(withId(R.id.bannerIDBox)).perform(typeText("B00882378"));
        onView(withId(R.id.emailBox)).perform(typeText("example.tutor@gmail.com"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Tutor"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_DAL_EMAIL)));
    }

    /*** UAT-V**/
    @Test
    public void checkIfRoleIsValid() {
        onView(withId(R.id.bannerIDBox)).perform(typeText("B00884568"));
        onView(withId(R.id.emailBox)).perform(typeText("admin123@dal.ca"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Admin"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.EMPTY_STRING)));
    }

    /*** UAT-V**/
    @Test
    public void checkIfRoleIsInvalid() {
        onView(withId(R.id.bannerIDBox)).perform(typeText("B00881235"));
        onView(withId(R.id.emailBox)).perform(typeText("abc123@dal.ca"));
        onView(withId(R.id.roleSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Select your role"))).perform(click());
        onView(withId(R.id.registerButton)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.INVALID_ROLE)));
    }
}