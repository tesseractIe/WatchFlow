package com.save.bike.watchflow.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.save.bike.watchflow.MenuItem;
import com.save.bike.watchflow.R;

import java.util.ArrayList;

public class MenuItemsAdapter extends BaseAdapter {

    private ArrayList<MenuItem> items;
    private Context context;

    public MenuItemsAdapter(ArrayList<MenuItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public MenuItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_activity_main, parent, false);
        }

        final MenuItem p = getItem(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.text_view_title)).setText(p.title);
        ((TextView) view.findViewById(R.id.text_view_status)).setText(p.status);
        ((TextView) view.findViewById(R.id.text_view_date)).setText(p.date);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, p.launchActivity));
            }
        });
        return view;
    }
}
