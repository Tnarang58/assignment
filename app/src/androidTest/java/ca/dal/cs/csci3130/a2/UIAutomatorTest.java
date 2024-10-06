package ca.dal.cs.csci3130.a2;

import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class UIAutomatorTest {

    private static final int LAUNCH_TIMEOUT = 5000;
    final String launcherPackageName = "ca.dal.cs.csci3130.a2";
    private UiDevice device;

    @Before
    public void setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Context context = ApplicationProvider.getApplicationContext();
        Intent launcherIntent = context.getPackageManager().getLaunchIntentForPackage(launcherPackageName);
        launcherIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(launcherIntent);
        device.wait(Until.hasObject(By.pkg(launcherPackageName).depth(0)), LAUNCH_TIMEOUT);
    }

    /**
     * UAT-I
     **/
    @Test
    public void checkIfLandingPageIsVisible() {
        UiObject bannerIDBox = device.findObject(new UiSelector().text("Banner ID"));
        assertTrue(bannerIDBox.exists());
        UiObject emailBox = device.findObject(new UiSelector().text("Email"));
        assertTrue(emailBox.exists());
        UiObject roleSpinner = device.findObject(new UiSelector().textContains("Select your role"));
        roleSpinner.exists();
        UiObject registerButton = device.findObject(new UiSelector().text("REGISTER"));
        assertTrue(registerButton.exists());
    }

    /**
     * UAT-VI
     **/
    @Test
    public void checkIfMoved2WelcomePage() throws UiObjectNotFoundException {
        UiObject bannerIDBox = device.findObject(new UiSelector().text("Banner ID"));
        bannerIDBox.setText("B00881234");
        UiObject emailBox = device.findObject(new UiSelector().text("Email"));
        emailBox.setText("example.tutor@dal.ca");
        UiObject roleSpinner = device.findObject(new UiSelector().textContains("Select your role"));
        roleSpinner.click();
        List<UiObject2> roles = device.findObjects(By.res("android:id/text1"));
        roles.get(1).click();
        UiObject registerButton = device.findObject(new UiSelector().text("REGISTER"));
        registerButton.clickAndWaitForNewWindow();
        UiObject welcomeLabel = device.findObject(new UiSelector().textContains("Welcome"));
        assertTrue(welcomeLabel.exists());
    }

    /**
     * Q3
     **/
    @Test
    public void checkRetrieveCredentials() throws UiObjectNotFoundException {
        UiObject bannerIDBox = device.findObject(new UiSelector().text("Banner ID"));
        bannerIDBox.setText("B00881234");
        UiObject emailBox = device.findObject(new UiSelector().text("Email"));
        emailBox.setText("example.tutor@dal.ca");
        UiObject roleSpinner = device.findObject(new UiSelector().textContains("Select your role"));
        roleSpinner.click();
        List<UiObject2> roles = device.findObjects(By.res("android:id/text1"));
        roles.get(1).click();
        UiObject registerButton = device.findObject(new UiSelector().text("REGISTER"));
        registerButton.clickAndWaitForNewWindow();
        //missing step, add the required step here!
        UiObject snackBarText = device.findObject(new UiSelector().textContains("B00881234-example.tutor@dal.ca-Tutor"));
        assertTrue(snackBarText.exists());
    }
}
