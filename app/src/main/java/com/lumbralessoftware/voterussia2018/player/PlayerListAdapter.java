package com.lumbralessoftware.voterussia2018.player;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import static com.lumbralessoftware.voterussia2018.Constants.DEFENDER;
import static com.lumbralessoftware.voterussia2018.Constants.FORWARD;
import static com.lumbralessoftware.voterussia2018.Constants.GOALKEEPER;
import static com.lumbralessoftware.voterussia2018.Constants.MIDFIELD;

/**
 * Created by javiergonzalezcabezas on 5/6/18.
 */

public class PlayerListAdapter extends RecyclerView
        .Adapter<PlayerListAdapter.DataObjectHolder> {

    private List<Player> list;
    private Context context;
    private static PlayerListAdapter.ListenerPlayer listenerPlayer;

    interface ListenerPlayer {
        void rating(int id);
    }

    public PlayerListAdapter(PlayerListAdapter.ListenerPlayer listener, List<Player> list, Context context) {
        this.listenerPlayer = listener;
        this.list = list;
        this.context = context;
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
        Button rate;

        @BindView(R.id.player_list_item_rate_ratingBar)
        RatingBar rating;

        DataObjectHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            rate.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            listenerPlayer.rating(getLayoutPosition());
        }

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
