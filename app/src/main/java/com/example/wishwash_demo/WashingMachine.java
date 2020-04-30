package com.example.wishwash_demo;

public class WashingMachine {
    String Id;
    String Name;

    public WashingMachine(String id , String name) {
        Id = id;
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
