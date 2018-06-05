package com.lumbralessoftware.voterussia2018.player;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lumbralessoftware.voterussia2018.R;

import java.util.List;

/**
 * Created by javiergonzalezcabezas on 5/6/18.
 */

public class PlayerListAdapter extends RecyclerView
        .Adapter<PlayerListAdapter.DataObjectHolder> {

    List<Player> list;
    Context context;

    public PlayerListAdapter(List<Player> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_list_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.club.setText(list.get(position).getClub());
        Glide.with(context)
                .load(list.get(position).getImageURL())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.profileImageView);
        Glide.with(context)
                .load(list.get(position).getTeam())
                .into(holder.flag);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        ImageView profileImageView;
        ImageView flag;
        TextView name;
        TextView club;

        DataObjectHolder(View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.player_list_item_imageView);
            name = itemView.findViewById(R.id.player_list_item_name_textView);
            club = itemView.findViewById(R.id.player_list_item_club_textView);
            flag = itemView.findViewById(R.id.player_list_item_flag_imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

    }

}
