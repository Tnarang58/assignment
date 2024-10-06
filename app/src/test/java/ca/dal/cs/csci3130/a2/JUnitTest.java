package ca.dal.cs.csci3130.a2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class JUnitTest {
    CredentialValidator validator;

    @Before
    public void setup() {
        validator = new CredentialValidator();
    }

    @Test
    public void checkIfBannerIDIsEmpty() {
        assertTrue(validator.isEmptyBannerID(""));
        assertFalse(validator.isEmptyBannerID("B00881234"));
    }

    @Test
    public void checkIfBannerIDIsValid() {
        assertTrue(validator.isValidBannerID("B00882313"));
    }

    @Test
    public void checkIfBannerIDIsInvalid() {
        assertFalse(validator.isValidBannerID("B88036452"));
        assertFalse(validator.isValidBannerID("abc12345"));
    }

    @Test
    public void checkIfEmailIsValid() {
        assertTrue(validator.isValidEmailAddress("abc123@dal.ca"));
    }

    @Test
    public void checkIfEmailIsInvalid() {
        assertFalse(validator.isValidEmailAddress("abc.123dal.ca"));
    }

    @Test
    public void checkIfEmailIsNotFromDAL() {
        assertTrue(validator.isDALEmailAddress("abc.123@dal.ca"));
        assertFalse(validator.isDALEmailAddress("abc.123@usask.ca"));
    }

    @Test
    public void checkIfRoleIsValid() {
        assertTrue(validator.isValidRole("Tutor"));
        assertTrue(validator.isValidRole("Student"));
        assertTrue(validator.isValidRole("Admin"));
    }

    @Test
    public void checkIfRoleIsInvalid() {
        assertFalse(validator.isValidRole("Select your role"));
    }
}