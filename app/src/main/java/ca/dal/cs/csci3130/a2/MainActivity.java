
package ca.dal.cs.csci3130.a2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String WELCOME_MESSAGE = "ca.dal.csci3130.a2.welcome";
    FirebaseDatabase database;
    FirebaseCRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.loadRoleSpinner();
        this.setupRegistrationButton();
        this.initializeDatabaseAccess();
    }

    protected void loadRoleSpinner() {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Select your role");
        roles.add("Tutor");
        roles.add("Student");
        roles.add("Admin");
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, roles);
        roleAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        roleSpinner.setAdapter(roleAdapter);
    }

    protected void setupRegistrationButton() {
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
    }

    protected void initializeDatabaseAccess() {
        //initialize FirebaseDatabase and FirebaseCRUD
        database = FirebaseDatabase.getInstance("https://fir-realtime-database-85da3.firebaseio.com");
        crud = new FirebaseCRUD(database);
    }

    @Override
    public void onClick(View view) {
        // Corrected and completed the buggy code
        String bannerID = getBannerID(); // Retrieve the banner ID
        String errorMessage = new String(); // Initialize an empty error message
        String email = getEmailAddress(); // Retrieve the email address
        String role = getRole(); // Retrieve the user role
        CredentialValidator validator = new CredentialValidator(); // Create an instance of CredentialValidator

// Validate if the banner ID is empty
        if (validator.isEmptyBannerID(bannerID)) {
            errorMessage = getResources().getString(R.string.EMPTY_BANNER_ID).trim(); // Set error message for empty banner ID
        }

// Validate if the banner ID is in a valid format
        if (!validator.isValidBannerID(bannerID)) {
            errorMessage = getResources().getString(R.string.INVALID_BANNER_ID).trim(); // Set error message for invalid banner ID
        }

// Validate if the email address is valid
        if (!validator.isValidEmailAddress(email)) {
            errorMessage = getResources().getString(R.string.INVALID_EMAIL_ADDRESS).trim(); // Set error message for invalid email address
        } else if (!validator.isDALEmailAddress(email)) {
            errorMessage = getResources().getString(R.string.INVALID_DAL_EMAIL).trim(); // Set error message for invalid Dal email address
        }

// Validate if the role is valid
        if (!validator.isValidRole(role)) {
            errorMessage = getResources().getString(R.string.INVALID_ROLE).trim(); // Set error message for invalid role
        }

        setStatusMessage(errorMessage); // Set the status message to display any errors

// If there are no errors, navigate to the welcome activity
        if (errorMessage.isEmpty()) {
            saveInfoToFirebase(bannerID, email, role); // Save the information to Firebase
            move2WelcomeWindow(bannerID, email, role); // Move to the Welcome window
        }

    }

    protected String getBannerID() {
        EditText bannerIDBox = findViewById(R.id.bannerIDBox);
        return bannerIDBox.getText().toString().trim();
    }

    protected String getEmailAddress() {
        EditText emailAddress = findViewById(R.id.emailBox);
        return emailAddress.getText().toString().trim();
    }

    protected String getRole() {
        Spinner role = findViewById(R.id.roleSpinner);
        return role.getSelectedItem().toString().trim();
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    protected void move2WelcomeWindow(String bannerID, String emailAddress, String role) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.putExtra("BANNER_ID", bannerID);
        intent.putExtra("ROLE", role);
        intent.putExtra("EMAIL", emailAddress);
        startActivity(intent);
        finish();

    }

    protected void saveInfoToFirebase(String bannerID, String emailAddress, String role) {
        // Set the values for each reference using the bannerID
        crud.setNetID(bannerID);
        crud.setEmailAddress(emailAddress);
        crud.setRole(role);


    }

}
