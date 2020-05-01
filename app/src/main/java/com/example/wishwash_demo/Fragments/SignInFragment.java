package com.example.wishwash_demo.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wishwash_demo.Activities.CalendarActivity;
import com.example.wishwash_demo.Activities.SignInActivity;
import com.example.wishwash_demo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Context.MODE_PRIVATE;

public class SignInFragment extends Fragment {
    // Declaring variables:
    private EditText editText_email, editText_password;
    private Button btn_back, btn_signin;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        View v = inflater.inflate(R.layout.fragment_sign_in, container, false);

        // Assigning variables:
        editText_email = v.findViewById(R.id.EditText_signinFragment_email);
        editText_password = v.findViewById(R.id.EditText_signinFragment_password);
        btn_back = v.findViewById(R.id.Button_signinFragment_back);
        btn_signin = v.findViewById(R.id.Button_signinFragment_signin);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Toast.makeText(getContext(), "You have successfully logged in", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(getContext(), "Login failed. If you don't have a user, please sign up", Toast.LENGTH_SHORT).show();
            }
        };

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }
        });

        // TO SIGN OUT FROM FIREBASE:
        // FirebaseAuth.getInstance().signOut();
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Login if user exists in database
                // Inspiration from: https://www.youtube.com/watch?v=V0ZrnL-i77Q
                String email = editText_email.getText().toString();
                String password = editText_password.getText().toString();

                if (!SignInActivity.isValidEmail(email)) {
                    Toast.makeText(getContext(), "Email not accepted! Try again", Toast.LENGTH_SHORT).show();
                } else if (!SignInActivity.isValidPassword(password)) {
                    Toast.makeText(getContext(), "Password not accepted! Try again", Toast.LENGTH_SHORT).show();
                } else {
                        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "SignIn unsuccessful. Please try again", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "Successful sign in", Toast.LENGTH_SHORT).show();
                                    Log.d("SignInFragment", "Successful sign in.");
                                    Intent toCalendarActivityIntent = new Intent(getActivity(), CalendarActivity.class);
                                    startActivity(toCalendarActivityIntent);
                                    //sp.edit().putBoolean("logged",true).apply();
                                }
                            }
                        });
                    }
                }
            });

        return v;
        }
    }
