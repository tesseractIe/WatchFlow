package com.save.bike.watchflow.activity;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;

import com.save.bike.watchflow.HeartRate;
import com.save.bike.watchflow.R;
import com.save.bike.watchflow.activity.view.GraphView;

import java.util.ArrayList;
import java.util.List;

public class HeartRateActivity extends WearableActivity {

    List<HeartRate> heartRateList;
    GraphView surfaceView;
    Button buttonDown;
    private boolean drawDetails = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);

        surfaceView = findViewById(R.id.surface_view_hr);
        buttonDown = findViewById(R.id.buttonDown);

        buttonDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawDetails){
                    drawDetails = surfaceView.drawDetailList();
                }else{
                    drawDetails = true;
                    surfaceView.drawShortList();
                }
            }
        });

        heartRateList = new ArrayList<>();

//        heartRateList.add(new HeartRate(100, 0));
//        heartRateList.add(new HeartRate(200, 120));

        heartRateList.add(new HeartRate(240, 360));
        heartRateList.add(new HeartRate(180, 365));

        heartRateList.add(new HeartRate(200, 366));
        heartRateList.add(new HeartRate(100, 367));
        heartRateList.add(new HeartRate(200, 368));
        heartRateList.add(new HeartRate(100, 369));
        heartRateList.add(new HeartRate(200, 370));
        heartRateList.add(new HeartRate(100, 371));
        heartRateList.add(new HeartRate(200, 372));
        heartRateList.add(new HeartRate(100, 373));
        heartRateList.add(new HeartRate(200, 374));
        heartRateList.add(new HeartRate(100, 375));

        heartRateList.add(new HeartRate(137, 380));
        heartRateList.add(new HeartRate(120, 390));
        heartRateList.add(new HeartRate(125, 400));
        heartRateList.add(new HeartRate(195, 415));
        heartRateList.add(new HeartRate(126, 417));
        heartRateList.add(new HeartRate(128, 420));
        heartRateList.add(new HeartRate(130, 450));
        heartRateList.add(new HeartRate(111, 460));
        heartRateList.add(new HeartRate(119, 500));
        heartRateList.add(new HeartRate(220, 520));
        heartRateList.add(new HeartRate(210, 530));
        heartRateList.add(new HeartRate(211, 540));
        heartRateList.add(new HeartRate(190, 1200));
        heartRateList.add(new HeartRate(150, 1300));
        heartRateList.add(new HeartRate(120, 1400));

        surfaceView.init(heartRateList);
    }

}
