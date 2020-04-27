package com.example.wishwash_demo.Firebase;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Firebase {
    private Map<String, String> user = new HashMap<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //user.put("first", "second");
}
