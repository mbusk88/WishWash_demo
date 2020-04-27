package com.example.wishwash_demo.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.wishwash_demo.Fragments.BookingsFragment;
import com.example.wishwash_demo.Fragments.CalendarFragment;
import com.example.wishwash_demo.Fragments.ChatFragment;
import com.example.wishwash_demo.Fragments.TipsFragment;
import com.example.wishwash_demo.R;
import com.example.wishwash_demo.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CalendarActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Toolbar actionBar;
    private final String TAG = "CalendarActivity";
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.activity_calendar);

        sp = getSharedPreferences("login", MODE_PRIVATE);

        if(sp.getBoolean("logged",false)){
            goToCalendarActivity();
        }

        actionBar = findViewById(R.id.action_bar);
        bottomNavigationView = findViewById(R.id.BottomNavigation_Calendar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        setSupportActionBar(actionBar);

        initFirstFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
    }

    // Inflates --- SignInSignUpFragment/fragment_sign_in_sign_up --- as the first fragment to be shown when app is started
    private void initFirstFragment() {
        Fragment fragment = new CalendarFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FrameLayout_Calendar, fragment);
        transaction.commit();
    }

    // When BottomNavigationView item is clicked, inflate belonging fragment.
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.Navigation_Calendar:
                            openFragment(CalendarFragment.newInstance("", ""));
                            return true;
                        case R.id.Navigation_Bookings:
                            openFragment(BookingsFragment.newInstance("", ""));
                            return true;
                        case R.id.Navigation_Chat:
                            openFragment(ChatFragment.newInstance("", ""));
                            return true;
                        case R.id.Navigation_Tips:
                            openFragment(TipsFragment.newInstance("", ""));
                            return true;
                    }
                    return false;
                }
            };

    public void openFragment(Fragment fragment) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.FrameLayout_Calendar, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void goToCalendarActivity() {
        Intent toCalendarActivityIntent = new Intent(CalendarActivity.this, CalendarActivity.class);
        startActivity(toCalendarActivityIntent);
    }
}
