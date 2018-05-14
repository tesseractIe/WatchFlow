package com.save.bike.watchflow.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.save.bike.watchflow.MenuItem;
import com.save.bike.watchflow.R;

import java.util.ArrayList;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.ViewHolder> {
    private ArrayList<MenuItem> data;
    private ItemSelectedListener itemSelectedListener;

    public MenuItemsAdapter(ArrayList<MenuItem> data) {
        this.data = data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private TextView textStatus;
        private TextView textDate;
        ViewHolder(View view) {
            super(view);
            textTitle = view.findViewById(R.id.text_view_title);
            textStatus = view.findViewById(R.id.text_view_status);
            textDate = view.findViewById(R.id.text_view_date);
        }
        void bind(final int position, final ItemSelectedListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemSelected(position);
                    }
                }
            });
        }
    }

    public void setListener(ItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    @Override
    public MenuItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_activity_main, parent, false));
    }

    @Override
    public void onBindViewHolder(MenuItemsAdapter.ViewHolder holder, final int position) {
        if (data != null && !data.isEmpty()) {
            holder.textDate.setText(data.get(position).date);
            holder.textStatus.setText(data.get(position).status);
            holder.textTitle.setText(data.get(position).title);
            holder.bind(position, itemSelectedListener);
        }
    }

    @Override
    public int getItemCount() {
        if (data != null && !data.isEmpty()) {
            return data.size();
        }
        return 0;
    }

    public interface ItemSelectedListener {
        void onItemSelected(int position);
    }
}
