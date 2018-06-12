package com.lumbralessoftware.voterussia2018.player;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lumbralessoftware.voterussia2018.Player;
import com.lumbralessoftware.voterussia2018.Utils;
import com.lumbralessoftware.voterussia2018.rating.RatingDialogFragment;
import com.lumbralessoftware.voterussia2018.rating.RatingPresenter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.lumbralessoftware.voterussia2018.Constants.FIREBASE_POSITION;
import static com.lumbralessoftware.voterussia2018.Constants.FIREBASE_TEAM;
import static com.lumbralessoftware.voterussia2018.Constants.PLAYERS;

/**
 * Created by javiergonzalezcabezas on 21/5/18.
 */

public class PlayerListPresenter implements PlayerListContract.Presenter {
    private PlayerListContract.View view;
    private AppCompatActivity activity;

    DatabaseReference databaseReference;
    DatabaseReference player;

    public PlayerListPresenter(PlayerListContract.View view, AppCompatActivity activity) {
        this.view = view;
        this.activity = activity;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        player = databaseReference.child(PLAYERS);
        this.view.setPresenter(this);
    }

    @Override
    public void fetch() {

        if (Utils.INSTANCE.isOnline(activity)) {
            player.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<Player> list = new ArrayList<>();
                    for (DataSnapshot children : dataSnapshot.getChildren()) {
                        Player player = children.getValue(Player.class);
                        list.add(player);
                    }
                    view.showPlayer(list);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    view.showError();
                }
            });
        } else {
            view.noInternet();
        }
    }



    @Override
    public void goToRating(int id, @NotNull String name, @NotNull String image) {

        RatingDialogFragment ratingDialogFragment = RatingDialogFragment.newInstance(id, name, image);
        ratingDialogFragment.show(activity.getSupportFragmentManager(), "dialog");
        new RatingPresenter(ratingDialogFragment, id, activity);
    }

    @Override
    public void fetchPlayerWithPosition(int position) {
        if (Utils.INSTANCE.isOnline(activity)) {
            player.orderByChild(FIREBASE_POSITION).equalTo(position).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<Player> list = new ArrayList<>();
                    for (DataSnapshot children : dataSnapshot.getChildren()) {
                        Player player = children.getValue(Player.class);
                        list.add(player);
                    }
                    view.showPlayer(list);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    view.showError();
                }
            });
        } else {
            view.noInternet();
        }
    }

    @Override
    public void fetchPlayerWithTeam(int team) {
        if (Utils.INSTANCE.isOnline(activity)) {
            player.orderByChild(FIREBASE_TEAM).equalTo(team).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<Player> list = new ArrayList<>();
                    for (DataSnapshot children : dataSnapshot.getChildren()) {
                        Player player = children.getValue(Player.class);
                        list.add(player);
                    }
                    view.showPlayer(list);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    view.showError();
                }
            });
        } else {
            view.noInternet();
        }
    }
}
