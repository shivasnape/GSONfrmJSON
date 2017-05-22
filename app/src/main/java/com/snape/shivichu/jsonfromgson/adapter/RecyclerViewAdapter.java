package com.snape.shivichu.jsonfromgson.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snape.shivichu.jsonfromgson.R;
import com.snape.shivichu.jsonfromgson.holder.view_holder.Datalist;
import com.snape.shivichu.jsonfromgson.holder.view_holder.RecyclerViewHolders;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<Datalist> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<Datalist> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.songTitle.setText("Song Title: " + itemList.get(position).getSongTitle());
        holder.songYear.setText("Song Year: " + itemList.get(position).getSongYear());
        holder.songAuthor.setText("Song Author: " + itemList.get(position).getSongAuthor());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
