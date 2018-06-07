package com.lumbralessoftware.voterussia2018.player;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lumbralessoftware.voterussia2018.Player;
import com.lumbralessoftware.voterussia2018.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javiergonzalezcabezas on 5/6/18.
 */

public class PlayerListAdapter extends RecyclerView
        .Adapter<PlayerListAdapter.DataObjectHolder> {

    List<Player> list;
    Context context;
    private static final int GOALKEEPER = 0;
    private static final int DEFENDER = 1;
    private static final int MIDFIELD = 2;
    private static final int FORWARD = 3;

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
        holder.number.setText(list.get(position).getNumber());
       // if (list.get(position).getStart()==1) {
         //   holder.number.setBackground(ContextCompat.getDrawable(context, R.drawable.ic_star_white));
       // }
        holder.rate.setText(String.valueOf(list.get(position).getVote()));
        holder.rating.setRating(13.67f);
        setImagePosition(list.get(position).getPosition(), holder.positon);
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

        @BindView(R.id.player_list_item_profile_imageView)
        ImageView profileImageView;

        @BindView(R.id.player_list_item_flag_imageView)
        ImageView flag;

        @BindView(R.id.player_list_item_name_textView)
        TextView name;

        @BindView(R.id.player_list_item_club_textView)
        TextView club;

        @BindView(R.id.player_list_item_number_textView)
        TextView number;

        @BindView(R.id.player_list_item_position_imageView)
        ImageView positon;

        @BindView(R.id.player_list_item_rate_textView)
        TextView rate;

        @BindView(R.id.player_list_item_rate_ratingBar)
        RatingBar rating;

        DataObjectHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

    }

    private void setImagePosition(int position, ImageView imageView) {
        switch (position) {
            case GOALKEEPER:
                imageView.setImageResource(R.drawable.goalkeeper);
                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_goalkeeper));
                break;
            case DEFENDER:
                imageView.setImageResource(R.drawable.defender);
                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_defender));
                break;
            case MIDFIELD:
                imageView.setImageResource(R.drawable.midfield);
                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_midfield));
                break;
            case FORWARD:
                imageView.setImageResource(R.drawable.forward);
                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_forward));
                break;
        }
    }

}
