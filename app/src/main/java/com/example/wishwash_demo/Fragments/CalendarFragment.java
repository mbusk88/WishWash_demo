package com.example.wishwash_demo.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.example.wishwash_demo.R;

import java.util.Calendar;

public class CalendarFragment extends Fragment {
    private CalendarFragmentListener listener;
    private CalendarView calendarView;
    private Calendar calendar;
    private Button btn_ok;

    public interface CalendarFragmentListener {
        void onDateChosen(Calendar c);
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendar = Calendar.getInstance();

        calendarView = v.findViewById(R.id.CalendarView_CalendarFragment);
        btn_ok = v.findViewById(R.id.Button_calendarFragment_ok);

        calendarView.setDate(System.currentTimeMillis(), false, true);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDateChosen(calendar);
            }
        });

        return v;
    }

    // When fragment is attached to an activity, check if interface is implemented.
    // Inspiration from: https://www.youtube.com/watch?v=i22INe14JUc
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CalendarFragmentListener) {
            listener = (CalendarFragmentListener) context;
        } else
            throw new RuntimeException(context.toString() + " must implement CalendarFragmentListener");
    }

    // When fragment is detached from the activity.
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
