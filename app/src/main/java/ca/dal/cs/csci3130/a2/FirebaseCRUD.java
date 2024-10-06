package ca.dal.cs.csci3130.a2;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseCRUD {
    private FirebaseDatabase database;
    private DatabaseReference bannerIDRef = null;
    private DatabaseReference emailRef = null;
    private DatabaseReference roleRef = null;
    private String extractedBannerID;
    private String extractedEmailAddress;
    private String extractedRole;


    public FirebaseCRUD(FirebaseDatabase database) {
        this.database = database;
        this.initializeDatabaseRefs();
        this.initializeDatabaseRefListeners();
    }

    protected void initializeDatabaseRefs() {
        this.bannerIDRef = getBannerIDRef();
        this.emailRef =getEmailAddressRef();
        this.roleRef = getRoleRef();
    }

    protected DatabaseReference getBannerIDRef() {
        return this.database.getReference("bannerID");
    }

    protected DatabaseReference getEmailAddressRef() {
        return this.database.getReference("emailAddress");
    }

    protected DatabaseReference getRoleRef() {
        return this.database.getReference("role");
    }


    protected void initializeDatabaseRefListeners() {
        //Incomplete method, add the missing code.
        this.setBannerIDListener();
        this.setEmailListener();
        this.setRoleListener();
    }

    protected void setNetID(String netID) {
        this.bannerIDRef.setValue(netID);

    }

    protected void setEmailAddress(String emailAddress) {
        this.emailRef.setValue(emailAddress);
    }

    protected void setRole(String role) {
        this.roleRef.setValue(role);
    }

    protected void setBannerIDListener() {
        //Incomplete method, add the missing code.
        this.bannerIDRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                extractedBannerID = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error if necessary
            }
        });
    }

    protected void setEmailListener() {
        this.emailRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                extractedEmailAddress = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    protected void setRoleListener() {
        //Incomplete method, add the missing code!
        this.roleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                extractedRole = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error if necessary
            }
        });
    }

    public String getExtractedBannerID() {
        return this.extractedBannerID;

    }

    public String getExtractedEmailAddress() {
        return this.extractedEmailAddress;
    }

    public String getExtractedRole() {
        return this.extractedRole;

    }



    /*A few methods have been removed! Add your code by understanding the existing code!*/
}
