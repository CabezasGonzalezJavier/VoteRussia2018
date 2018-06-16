package com.lumbralessoftware.voterussia2018.player;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lumbralessoftware.voterussia2018.ElementList;
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
 * Created by javiergonzalezcabezas on 16/6/18.
 */

public class PlayerListMenuAdapter
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public static final int ITEM_FINAL = 0;
        public static final int ITEM = 1;
        public static final int SECTION = 2;
        private Context context;
        private List<ElementList> list;
        private static PlayerListMenuAdapter.MyClickListener sClickListener;
        // Allows to remember the last item shown on screen
        private int mLastPositionSection = -1;


        static class SectionHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.player_list_menu_header_text)
            TextView textViewSection;

            SectionHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        static class DataObjectHolder extends RecyclerView.ViewHolder
                implements View
                .OnClickListener {
            @BindView(R.id.player_list_menu_item_flag_imageView)
            ImageView flag;

            @BindView(R.id.player_list_menu_item_name)
            TextView name;

            @BindView(R.id.player_list_menu_item_view)
            View view;

            DataObjectHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                sClickListener.onItemClick(getAdapterPosition(), true);
            }
        }

    public void setOnItemClickListener(PlayerListMenuAdapter.MyClickListener myClickListener) {
        this.sClickListener = myClickListener;
    }

    public PlayerListMenuAdapter(Context context, List<ElementList> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_list_menu_item, parent, false);

        View viewSection = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_list_menu_header, parent, false);

        switch (viewType) {
            case ITEM_FINAL:
            case ITEM:
                return new PlayerListMenuAdapter.DataObjectHolder(view);
            case SECTION:
                return new PlayerListMenuAdapter.SectionHolder(viewSection);
            default:
                return new PlayerListMenuAdapter.SectionHolder(viewSection);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {

            case ITEM:
                PlayerListMenuAdapter.DataObjectHolder dataObjectHolder = (PlayerListMenuAdapter.DataObjectHolder) holder;
                dataObjectHolder.name.setText(list.get(position).getName());
                setFlag(((DataObjectHolder) holder).flag,position);
                break;

            case ITEM_FINAL:
                PlayerListMenuAdapter.DataObjectHolder dataObjectHolderFinal = (PlayerListMenuAdapter.DataObjectHolder) holder;
                dataObjectHolderFinal.name.setText(list.get(position).getName());
                dataObjectHolderFinal.view.setVisibility(View.GONE);
                setFlag(((DataObjectHolder) holder).flag,position);
                break;
            case SECTION:
                PlayerListMenuAdapter.SectionHolder sectionHolder = (PlayerListMenuAdapter.SectionHolder) holder;
                sectionHolder.textViewSection.setText(list.get(position).getName());
                mLastPositionSection = position;
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface MyClickListener {
        void onItemClick(int position, boolean addItem);
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).isSection()) {
            return SECTION;
        } else {
            if (list.get(position).ismNextSection()) {
                return ITEM_FINAL;
            } else {
                return ITEM;
            }
        }
    }

    private void setFlag(ImageView imageView, int position) {
            switch (position) {
                case 1:
                    imageView.setImageResource(R.drawable.goalkeeper);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_goalkeeper));
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.defender);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_defender));
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.midfield);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_midfield));
                    break;
                case 4:
                    imageView.setImageResource(R.drawable.forward);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_forward));
                    break;
                //A
                case 6:
                    imageView.setImageResource(R.drawable.flag0);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 7:
                    imageView.setImageResource(R.drawable.flag1);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 8:
                    imageView.setImageResource(R.drawable.flag2);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 9:
                    imageView.setImageResource(R.drawable.flag3);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                //B
                case 11:
                    imageView.setImageResource(R.drawable.flag4);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 12:
                    imageView.setImageResource(R.drawable.flag5);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 13:
                    imageView.setImageResource(R.drawable.flag6);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 14:
                    imageView.setImageResource(R.drawable.flag7);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                //C
                case 16:
                    imageView.setImageResource(R.drawable.flag8);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 17:
                    imageView.setImageResource(R.drawable.flag9);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 18:
                    imageView.setImageResource(R.drawable.flag10);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 19:
                    imageView.setImageResource(R.drawable.flag11);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                //D
                case 21:
                    imageView.setImageResource(R.drawable.flag12);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 22:
                    imageView.setImageResource(R.drawable.flag13);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 23:
                    imageView.setImageResource(R.drawable.flag14);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 24:
                    imageView.setImageResource(R.drawable.flag15);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                //E
                case 26:
                    imageView.setImageResource(R.drawable.flag16);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 27:
                    imageView.setImageResource(R.drawable.flag17);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 28:
                    imageView.setImageResource(R.drawable.flag18);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 29:
                    imageView.setImageResource(R.drawable.flag19);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;

                //F
                case 31:
                    imageView.setImageResource(R.drawable.flag20);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 32:
                    imageView.setImageResource(R.drawable.flag21);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 33:
                    imageView.setImageResource(R.drawable.flag22);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 34:
                    imageView.setImageResource(R.drawable.flag23);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                //G
                case 36:
                    imageView.setImageResource(R.drawable.flag24);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 37:
                    imageView.setImageResource(R.drawable.flag25);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 38:
                    imageView.setImageResource(R.drawable.flag26);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 39:
                    imageView.setImageResource(R.drawable.flag27);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                //H
                case 41:
                    imageView.setImageResource(R.drawable.flag28);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 42:
                    imageView.setImageResource(R.drawable.flag29);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 43:
                    imageView.setImageResource(R.drawable.flag30);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;
                case 44:
                    imageView.setImageResource(R.drawable.flag31);
                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_white_background));
                    break;

            }
    }
}
