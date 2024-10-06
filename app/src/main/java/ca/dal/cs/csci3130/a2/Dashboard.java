package ca.dal.cs.csci3130.a2;

class User {
    public String getLoginID() {
        return "demoUserID";
    }
}

public class Dashboard extends User {

    public String getLoginID() {
        return this.getLoginID();
    }
}










