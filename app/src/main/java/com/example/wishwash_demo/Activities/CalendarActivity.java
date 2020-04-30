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
import com.example.wishwash_demo.Fragments.NewBookingFragment;
import com.example.wishwash_demo.Fragments.TipsFragment;
import com.example.wishwash_demo.R;
import com.example.wishwash_demo.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CalendarActivity extends AppCompatActivity implements CalendarFragment.CalendarFragmentListener, NewBookingFragment.NewBookingFragmentListener {
    private BottomNavigationView bottomNavigationView;
    private Toolbar actionBar;
    private final String TAG = "CalendarActivity";
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private SharedPreferences sp;
    private CalendarFragment calendarFragment;
    private NewBookingFragment newBookingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");
        setContentView(R.layout.activity_calendar);

        calendarFragment = new CalendarFragment();
        newBookingFragment = new NewBookingFragment();

        // Try to: If user is already loggen in, forward to CalendarActivity:
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

    // Inflates --- CalendarFragment/fragment_calendar --- as the first fragment to be shown when app is started
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

    @Override
    public void onDateChosen(Calendar c) {
        // Second, open NewBookingFragment
        // openFragment(NewBookingFragment.newInstance(YEAR, MONTH, DAYOFMONTH)); -> takes three int arguments, f.x. NewBookingFragment.newInstance(2020, 4, 29)

        // Third, show all times for the chosen day -> maybe externalize
        // TODO: Insert code here
    }

    @Override
    public void onTimeChosen(Calendar c) {
        // First, update firebase with new info -> maybe externalize
        // TODO: Insert code here

        // Second, update calendar in CalendarFragment (dayOfMonth background color should change to display that the user has booked the current day)
        // calendarFragment.updateDayOfMonthBackgroundColor(userHasBookedDate) or something like that


    }
}
