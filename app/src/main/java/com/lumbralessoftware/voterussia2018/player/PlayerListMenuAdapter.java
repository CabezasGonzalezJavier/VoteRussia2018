package com.lumbralessoftware.voterussia2018.player;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.lumbralessoftware.voterussia2018.ElementList;
import com.lumbralessoftware.voterussia2018.R;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javiergonzalezcabezas on 16/6/18.
 */

public class PlayerListMenuAdapter
    extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SectionIndexer {

        HashMap<String, Integer> mMapIndex;
        String[] mSections;
        public static final int ITEM_FINAL = 0;
        public static final int ITEM = 1;
        public static final int SECTION = 2;
        private Context mContext;
        private List<ElementList> mList;
        private static PlayerListMenuAdapter.MyClickListener sClickListener;
        // Allows to remember the last item shown on screen
        private int mLastPositionSection = -1;

        @Override
        public Object[] getSections() {
            return mSections;
        }

        @Override
        public int getPositionForSection(int i) {
            return mMapIndex.get(mSections[i]);
        }

        @Override
        public int getSectionForPosition(int i) {
            return 0;
        }

        static class SectionHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.section_with_line_list_item_header_text)
            TextView mTextViewSection;

            SectionHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        static class DataObjectHolder extends RecyclerView.ViewHolder
                implements View
                .OnClickListener {
            @BindView(R.id.section_with_line_list_item_name)
            TextView mName;

            @BindView(R.id.section_with_line_list_item_view)
            View mView;

            DataObjectHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                mName.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
            }
        }

    public void setOnItemClickListener(PlayerListMenuAdapter.MyClickListener myClickListener) {
        this.sClickListener = myClickListener;
    }

    public PlayerListMenuAdapter(Context context, List<ElementList> list, String[] sections, HashMap<String, Integer> mapIndex) {
        mContext = context;
        mList = list;
        mSections = sections;
        mMapIndex = mapIndex;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.section_with_line_list_item, parent, false);

        View viewSection = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.section_with_lien_list_item_header, parent, false);

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
                dataObjectHolder.mName.setText(mList.get(position).getName());
                break;

            case ITEM_FINAL:
                PlayerListMenuAdapter.DataObjectHolder dataObjectHolderFinal = (PlayerListMenuAdapter.DataObjectHolder) holder;
                dataObjectHolderFinal.mName.setText(mList.get(position).getName());
                dataObjectHolderFinal.mView.setVisibility(View.GONE);
                break;
            case SECTION:
                PlayerListMenuAdapter.SectionHolder sectionHolder = (PlayerListMenuAdapter.SectionHolder) holder;
                sectionHolder.mTextViewSection.setText(mList.get(position).getName());
                mLastPositionSection = position;
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void refreshData(List<ElementList> dataset) {
        mList.clear();
        mList.addAll(dataset);
        notifyDataSetChanged();
    }

    interface MyClickListener {
        void onItemClick(int position, boolean addItem);
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).isSection()) {
            return SECTION;
        } else {
            if (mList.get(position).ismNextSection()) {
                return ITEM_FINAL;
            } else {
                return ITEM;
            }
        }
    }
}
