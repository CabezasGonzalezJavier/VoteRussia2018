package com.lumbralessoftware.voterussia2018.player;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.lumbralessoftware.voterussia2018.R;

/**
 * Created by javiergonzalezcabezas on 21/5/18.
 */

public class PlayerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_list_activity);
        initFragment();
    }

    private void initFragment () {
        PlayerListFragment playerListFragment = (PlayerListFragment) getSupportFragmentManager().
                findFragmentById(R.id.player_list_activity_container);
        if (playerListFragment == null) {
            playerListFragment = playerListFragment.newInstance();
            addFragmentToActivity(getSupportFragmentManager(),
                    playerListFragment, R.id.player_list_activity_container);
        }
        new PlayerListPresenter(playerListFragment);

    }

    public static void addFragmentToActivity(FragmentManager fragmentManager,
                                              Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }


}
