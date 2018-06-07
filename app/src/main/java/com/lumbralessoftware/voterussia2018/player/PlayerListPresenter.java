package com.lumbralessoftware.voterussia2018.player;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lumbralessoftware.voterussia2018.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javiergonzalezcabezas on 21/5/18.
 */

public class PlayerListPresenter implements PlayerListContract.Presenter {
    private PlayerListContract.View view;

    DatabaseReference databaseReference;
    DatabaseReference player;

    public PlayerListPresenter(PlayerListContract.View view) {
        this.view = view;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        player = databaseReference.child("players");
        this.view.setPresenter(this);
    }

    @Override
    public void fetch() {

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
    }
}
