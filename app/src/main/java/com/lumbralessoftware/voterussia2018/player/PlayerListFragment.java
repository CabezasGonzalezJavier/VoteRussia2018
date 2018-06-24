package com.lumbralessoftware.voterussia2018.player;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lumbralessoftware.voterussia2018.NewPlayer;
import com.lumbralessoftware.voterussia2018.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by javiergonzalezcabezas on 21/5/18.
 */

public class PlayerListFragment extends Fragment implements PlayerListContract.View, PlayerListAdapter.ListenerPlayer {

    private PlayerListContract.Presenter presenter;
    View view;

    @BindView(R.id.player_list_fragment_recyclerView)
    RecyclerView recyclerView;

    private List<NewPlayer> list;

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
        Snackbar.make(getActivity().findViewById(R.id.player_list_activity_container), getString(R.string.error), Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showPlayer(@NotNull List<NewPlayer> list) {
        this.list = list;
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        PlayerListAdapter adapter = new PlayerListAdapter(this, list, getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void rating(int id) {
        presenter.goToRating(id, list.get(id).getName(), list.get(id).getImageURL());
    }

    @Override
    public void noInternet() {

        Snackbar.make(getActivity().findViewById(R.id.player_list_activity_container), getString(R.string.no_internet), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.retry), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.fetch();
                    }
                }).show();
    }
}
