package com.save.bike.watchflow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.save.bike.watchflow.R;

public class MainActivity extends WearableActivity {

    private Button buttonTracking;
    private Button buttonRelax;
    private Button buttonInspireMe;
    private Button buttonLifestyle;
    private TextView textViewTracking;
    private TextView textViewRelax;
    private TextView textViewInspire;
    private TextView textViewLiveStyle;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonTracking = findViewById(R.id.buttonTracking);
        buttonRelax = findViewById(R.id.buttonRelax);
        buttonInspireMe = findViewById(R.id.buttonInspireMe);
        buttonLifestyle = findViewById(R.id.buttonLifestyle);

        textViewTracking = findViewById(R.id.textViewTracking);
        textViewRelax = findViewById(R.id.textViewRelax);
        textViewInspire = findViewById(R.id.textViewInspireMe);
        textViewLiveStyle = findViewById(R.id.textViewLifestyle);

        context = this;

        textViewTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TrackingActivity.class));
            }
        });

        textViewRelax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RelaxActivity.class));
            }
        });

        textViewInspire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, InspireMeActivity.class));
            }
        });

        textViewLiveStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LifestyleActivity.class));
            }
        });

        buttonTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, TrackingActivity.class));
            }
        });

        buttonRelax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RelaxActivity.class));
            }
        });

        buttonInspireMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, InspireMeActivity.class));
            }
        });

        buttonLifestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LifestyleActivity.class));
            }
        });

    }
}
