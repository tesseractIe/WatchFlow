package com.save.bike.watchflow.activity;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.ListView;

import com.save.bike.watchflow.MenuItem;
import com.save.bike.watchflow.adapter.MenuItemsAdapter;
import com.save.bike.watchflow.R;

import java.util.ArrayList;

public class TrackingActivity extends WearableActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        listView = findViewById(R.id.activity_main_list_view);

        ArrayList<MenuItem> listItems = new ArrayList<>();
        listItems.add(new MenuItem("Blood sugar","Last://////","2011-11-11 20:20:13",HeartRateActivity.class));
        listItems.add(new MenuItem("Weight","Last://////","2011-11-11 20:20:13",HeartRateActivity.class));
        listItems.add(new MenuItem("Heart Rate","Last://////","2011-11-11 20:20:13",HeartRateActivity.class));
        listItems.add(new MenuItem("Activity","Last://////","2011-11-11 20:20:13",HeartRateActivity.class));
        listItems.add(new MenuItem("Mood","Last://////","2011-11-11 20:20:13",HeartRateActivity.class));
        MenuItemsAdapter adapter = new MenuItemsAdapter(listItems,this);
        listView.setAdapter(adapter);

        setAmbientEnabled();
    }

}
