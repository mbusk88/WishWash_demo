package com.example.wishwash_demo;

import java.util.ArrayList;
import java.util.List;

public class User {
    private boolean isAdmin = false;
    private boolean signedIn = false;
    private String email, password;
    private String firstName, middleName, lastName;
    private String department;
    private List<Booking> bookingList;

    public User(){}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
        this.department = "";
        this.bookingList = new ArrayList<>();
    }

    public User(String email, String password, String firstName, String middleName, String lastName, String department, List<Booking> bookingList) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;

        if (!middleName.isEmpty())
            this.middleName = middleName;
        else
            this.middleName = "";

        this.lastName = lastName;
        this.department = department;

        if (!bookingList.isEmpty())
            this.bookingList = bookingList;
        else
            this.bookingList = new ArrayList<>();
    }

    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean admin) { isAdmin = admin; }

    public boolean isSignedIn() { return signedIn; }
    public void setSignedIn(boolean signedIn) { this.signedIn = signedIn; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public List<Booking> getBookingList() { return bookingList; }
    public void setBookingList(List<Booking> bookingList) { this.bookingList = bookingList; }
}
