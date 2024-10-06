package ca.dal.cs.csci3130.a2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

public class WelcomeActivity extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseCRUD crud;

    String bannerID;
    String role;
    String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Retrieve the passed data from the intent
        bannerID = getIntent().getStringExtra("BANNER_ID");
        role = getIntent().getStringExtra("ROLE");
        email = getIntent().getStringExtra("EMAIL");


        showWelcomeMessage();

    }

    protected void showWelcomeMessage() {
        // Create the welcome message
        String welcomeMessage = "Welcome " + bannerID + "! Your role is: " + role +
                ".\nA welcome email was sent to " + email;

        // Find the TextView with the ID welcomeLabel and set the welcome message
        TextView welcomeLabel = findViewById(R.id.welcomeLabel);
        welcomeLabel.setText(welcomeMessage);
    }

    protected void initializeDatabaseAccess() {
        //initialize FirebaseDatabase and FirebaseCRUD
        database = FirebaseDatabase.getInstance("https://fir-realtime-database-85da3.firebaseio.com");
        crud = new FirebaseCRUD(database);
    }

    protected void setupRetrieveButton() {
        Button retrieveButton = findViewById(R.id.retrieveFromDBButton);
        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Incomplete method, add the missing code retrieving the credentials!
            }
        });
    }

    protected void showRetrievedItems(View retrieveButton, String message) {
        //Incomplete method, add the missing code!
    }
}