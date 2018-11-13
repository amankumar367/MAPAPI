package com.learning.aman.mapapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

    private RecyclerView mUserDetails;
    private DatabaseReference mDatabase;

    private TextView mTotalDistance, mTotalTime, mAverageSpeed;
    private String totalDistance, totalTime;
    private double mAvgSpeed = 0;

    private static final String TAG = "DetailsActivity";
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

//           mUserDetails = (RecyclerView) findViewById(R.id.user_recyclerView);
//           mUserDetails.setHasFixedSize(true);
//           mUserDetails.setLayoutManager(new LinearLayoutManager(this));

           mTotalDistance = (TextView) findViewById(R.id.totalDistance);
           mTotalTime = (TextView) findViewById(R.id.totalTime);
           mAverageSpeed = (TextView) findViewById(R.id.avgSpeed);

           totalDistance = getIntent().getStringExtra("Distacne");
           totalTime = getIntent().getStringExtra("Time");
           Log.e(TAG, totalDistance+" - totalDistance | totalTime - "+totalTime);

           mTotalDistance.setText(String.valueOf(Double.parseDouble(totalDistance) / 1000));
           mTotalTime.setText(totalTime);

           try
           {
               SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
               Date Date1 = format.parse(totalTime);
               Date Date2 = format.parse("00:00:00");

               long millse = Date1.getTime() - Date2.getTime();
               long mills = Math.abs(millse);

               mAvgSpeed = Double.parseDouble(totalDistance) / ((double) mills/1000); // mAvgSpeed is in meter / sec
               mAvgSpeed = (mAvgSpeed * 60 * 60) / 1000;  // mAvgSpeed is in km / h
               Log.e(TAG, mAvgSpeed+" - AvgSpeed");


               mAverageSpeed.setText(String.valueOf(mAvgSpeed+" Km/h"));

               Log.e(TAG,"Time In SEC - "+mills/1000);
           }
           catch (Exception e)
           {

           }

       }
}