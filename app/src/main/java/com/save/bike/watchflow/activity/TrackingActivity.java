package com.save.bike.watchflow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.widget.ListView;

import com.save.bike.watchflow.MenuItem;
import com.save.bike.watchflow.adapter.MenuItemsAdapter;
import com.save.bike.watchflow.R;

import java.util.ArrayList;

public class TrackingActivity extends WearableActivity {

    WearableRecyclerView listView;
    Context context;
    ArrayList<MenuItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        listView = findViewById(R.id.activity_main_list_view);
        context = this;

        listItems = new ArrayList<>();
        listItems.add(new MenuItem("Blood sugar","Last://////","2011-11-11 20:20:13",HeartRateActivity.class));
        listItems.add(new MenuItem("Weight","Last://////","2011-11-11 20:20:13",HeartRateActivity.class));
        listItems.add(new MenuItem("Heart Rate","Last://////","2011-11-11 20:20:13",HeartRateActivity.class));
        listItems.add(new MenuItem("Activity","Last://////","2011-11-11 20:20:13",HeartRateActivity.class));
        listItems.add(new MenuItem("Mood","Last://////","2011-11-11 20:20:13",HeartRateActivity.class));
        MenuItemsAdapter adapter = new MenuItemsAdapter(listItems);
        adapter.setListener(new MenuItemsAdapter.ItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                startAct(position);
            }
        });
        listView.setEdgeItemsCenteringEnabled(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(llm);
        listView.setAdapter(adapter);

        setAmbientEnabled();
    }

    private void startAct(int pos){
        startActivity(new Intent(context,listItems.get(pos).launchActivity));
    }

}
