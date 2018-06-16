package com.lumbralessoftware.voterussia2018.player;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lumbralessoftware.voterussia2018.ElementList;
import com.lumbralessoftware.voterussia2018.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javiergonzalezcabezas on 16/6/18.
 */

public class PlayerListMenuAdapter
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        public static final int ITEM_FINAL = 0;
        public static final int ITEM = 1;
        public static final int SECTION = 2;
        private Context mContext;
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
        mContext = context;
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
                break;

            case ITEM_FINAL:
                PlayerListMenuAdapter.DataObjectHolder dataObjectHolderFinal = (PlayerListMenuAdapter.DataObjectHolder) holder;
                dataObjectHolderFinal.name.setText(list.get(position).getName());
                dataObjectHolderFinal.view.setVisibility(View.GONE);
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
}
