package com.lumbralessoftware.voterussia2018.player;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lumbralessoftware.voterussia2018.Player;
import com.lumbralessoftware.voterussia2018.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.lumbralessoftware.voterussia2018.Constants.ARGENTINA;
import static com.lumbralessoftware.voterussia2018.Constants.AUSTRALIA;
import static com.lumbralessoftware.voterussia2018.Constants.BELGIUM;
import static com.lumbralessoftware.voterussia2018.Constants.BRAZIL;
import static com.lumbralessoftware.voterussia2018.Constants.COLOMBIA;
import static com.lumbralessoftware.voterussia2018.Constants.COSTA_RICA;
import static com.lumbralessoftware.voterussia2018.Constants.CROATIA;
import static com.lumbralessoftware.voterussia2018.Constants.DEFENDER;
import static com.lumbralessoftware.voterussia2018.Constants.DENAMARK;
import static com.lumbralessoftware.voterussia2018.Constants.EGYPT;
import static com.lumbralessoftware.voterussia2018.Constants.ENGLAND;
import static com.lumbralessoftware.voterussia2018.Constants.FORWARD;
import static com.lumbralessoftware.voterussia2018.Constants.FRANCE;
import static com.lumbralessoftware.voterussia2018.Constants.GERMANY;
import static com.lumbralessoftware.voterussia2018.Constants.GOALKEEPER;
import static com.lumbralessoftware.voterussia2018.Constants.ICELAND;
import static com.lumbralessoftware.voterussia2018.Constants.IRAN;
import static com.lumbralessoftware.voterussia2018.Constants.JAPAN;
import static com.lumbralessoftware.voterussia2018.Constants.MEXICO;
import static com.lumbralessoftware.voterussia2018.Constants.MIDFIELD;
import static com.lumbralessoftware.voterussia2018.Constants.MOROCCO;
import static com.lumbralessoftware.voterussia2018.Constants.NIGERIA;
import static com.lumbralessoftware.voterussia2018.Constants.PANAMA;
import static com.lumbralessoftware.voterussia2018.Constants.PERU;
import static com.lumbralessoftware.voterussia2018.Constants.POLAND;
import static com.lumbralessoftware.voterussia2018.Constants.PORTUGAL;
import static com.lumbralessoftware.voterussia2018.Constants.RUSSIA;
import static com.lumbralessoftware.voterussia2018.Constants.SAUDI_ARABIA;
import static com.lumbralessoftware.voterussia2018.Constants.SENEGAL;
import static com.lumbralessoftware.voterussia2018.Constants.SERBIA;
import static com.lumbralessoftware.voterussia2018.Constants.SOUTH_KOREA;
import static com.lumbralessoftware.voterussia2018.Constants.SPAIN;
import static com.lumbralessoftware.voterussia2018.Constants.SWEDEN;
import static com.lumbralessoftware.voterussia2018.Constants.SWITZERLAND;
import static com.lumbralessoftware.voterussia2018.Constants.TUNISIA;
import static com.lumbralessoftware.voterussia2018.Constants.URUGUAY;

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

        @BindView(R.id.player_list_item_goal_imageView)
        ImageView goalFavorImage;

        @BindView(R.id.player_list_item_goal_textView)
        TextView goalsFavor;

        @BindView(R.id.player_list_item_goal_against_imageView)
        ImageView goalAgainstImage;

        @BindView(R.id.player_list_item_goal_against_textView)
        TextView goalsAgainst;


        @BindView(R.id.player_list_item_rate_textView)
        Button rate;

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
        setImagePosition(list.get(position).getPosition(), holder.positon);
        Glide.with(context)
                .load(list.get(position).getImageURL())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.profileImageView);
        setFlag(list.get(position).getTeam(), holder.flag);
        if (list.get(position).getGoalsFavor()>0) {
            holder.goalFavorImage.setImageResource(R.drawable.goal);
            holder.goalsFavor.setText(String.valueOf(list.get(position).getGoalsFavor()));
        } else {
            holder.goalFavorImage.setImageResource(R.drawable.circle_white_background);
            holder.goalsFavor.setText("");
        }

        if(list.get(position).getGoalsAgainst()>0) {
            holder.goalAgainstImage.setImageResource(R.drawable.goal_agaist);
            holder.goalsAgainst.setText(String.valueOf(list.get(position).getGoalsAgainst()));
        } else {
            holder.goalAgainstImage.setImageResource(R.drawable.circle_white_background);
            holder.goalsAgainst.setText("");
        }
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

    public void setFlag(int flag, ImageView imageView) {
        switch (flag) {
            //A
            case RUSSIA:
                imageView.setImageResource(R.drawable.flag0);
                break;
            case SAUDI_ARABIA:
                imageView.setImageResource(R.drawable.flag1);
                break;
            case EGYPT:
                imageView.setImageResource(R.drawable.flag2);
                break;
            case URUGUAY:
                imageView.setImageResource(R.drawable.flag3);
                break;
            //B
            case PORTUGAL:
                imageView.setImageResource(R.drawable.flag4);
                break;
            case SPAIN:
                imageView.setImageResource(R.drawable.flag5);
                break;
            case MOROCCO:
                imageView.setImageResource(R.drawable.flag6);
                break;
            case IRAN:
                imageView.setImageResource(R.drawable.flag7);
                break;
            //C
            case FRANCE:
                imageView.setImageResource(R.drawable.flag8);
                break;
            case AUSTRALIA:
                imageView.setImageResource(R.drawable.flag9);
                break;
            case PERU:
                imageView.setImageResource(R.drawable.flag10);
                break;
            case DENAMARK:
                imageView.setImageResource(R.drawable.flag11);
                break;
            //D
            case ARGENTINA:
                imageView.setImageResource(R.drawable.flag12);
                break;
            case ICELAND:
                imageView.setImageResource(R.drawable.flag13);
                break;
            case CROATIA:
                imageView.setImageResource(R.drawable.flag14);
                break;
            case NIGERIA:
                imageView.setImageResource(R.drawable.flag15);
                break;
            //E
            case BRAZIL:
                imageView.setImageResource(R.drawable.flag16);
                break;
            case SWITZERLAND:
                imageView.setImageResource(R.drawable.flag17);
                break;
            case COSTA_RICA:
                imageView.setImageResource(R.drawable.flag18);
                break;
            case SERBIA:
                imageView.setImageResource(R.drawable.flag19);
                break;
            //F
            case GERMANY:
                imageView.setImageResource(R.drawable.flag20);
                break;
            case MEXICO:
                imageView.setImageResource(R.drawable.flag21);
                break;
            case SWEDEN:
                imageView.setImageResource(R.drawable.flag22);
                break;
            case SOUTH_KOREA:
                imageView.setImageResource(R.drawable.flag23);
                break;
            //G
            case BELGIUM:
                imageView.setImageResource(R.drawable.flag24);
                break;
            case PANAMA:
                imageView.setImageResource(R.drawable.flag25);
                break;
            case TUNISIA:
                imageView.setImageResource(R.drawable.flag26);
                break;
            case ENGLAND:
                imageView.setImageResource(R.drawable.flag27);
                break;
            //H
            case POLAND:
                imageView.setImageResource(R.drawable.flag28);
                break;
            case SENEGAL:
                imageView.setImageResource(R.drawable.flag29);
                break;
            case COLOMBIA:
                imageView.setImageResource(R.drawable.flag30);
                break;
            case JAPAN:
                imageView.setImageResource(R.drawable.flag31);
                break;
            default:
                imageView.setImageResource(R.drawable.flag31);

        }

    }

}
