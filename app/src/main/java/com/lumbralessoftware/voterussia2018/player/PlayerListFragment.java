package com.lumbralessoftware.voterussia2018.player;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lumbralessoftware.voterussia2018.Player;
import com.lumbralessoftware.voterussia2018.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javiergonzalezcabezas on 21/5/18.
 */

public class PlayerListFragment extends Fragment implements PlayerListContract.View{

    private PlayerListContract.Presenter presenter;
    View view;

    @BindView(R.id.player_list_fragment_recyclerView)
    RecyclerView recyclerView;

    public static PlayerListFragment newInstance() {
        return new PlayerListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.player_list_fragment, container, false);
        ButterKnife.bind(this, view);
        presenter.fetch();
        return view;
    }
    @Override
    public void setPresenter(PlayerListContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void showError() {
        Snackbar.make(view, getString(R.string.error), Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showPlayer(@NotNull List<Player> list) {
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        PlayerListAdapter adapter= new PlayerListAdapter(list, getActivity());
        recyclerView.setAdapter(adapter);
    }
}
