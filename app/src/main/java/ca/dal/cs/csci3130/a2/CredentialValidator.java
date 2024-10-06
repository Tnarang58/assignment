package ca.dal.cs.csci3130.a2;

import androidx.core.util.PatternsCompat;

public class CredentialValidator {
    protected boolean isEmptyBannerID(String bannerID) {
        return bannerID.isEmpty();
    }

    protected boolean isValidBannerID(String bannerID) {

        //A valid banner ID starts with 'b00' or 'B00' and then six numbers, where the third
        // character cannot be '0'.
        return bannerID.matches("[bB]00[1-9][0-9]{5}");

    }

    protected boolean isValidEmailAddress(String emailAddress) {

        //Use PatternsCompat to check if the email address is valid
        return PatternsCompat.EMAIL_ADDRESS.matcher(emailAddress).matches();

    }

    protected boolean isDALEmailAddress(String emailAddress) {

        //A valid Dal email address ends with "@dal.ca"
        return emailAddress.endsWith("@dal.ca")&&isValidEmailAddress(emailAddress);

    }

    protected boolean isValidRole(String role) {

        //Validating against the possible values for the role
        return role != null && (role.equals("Tutor") || role.equals("Student") || role.equals("Admin"));
    }
}
