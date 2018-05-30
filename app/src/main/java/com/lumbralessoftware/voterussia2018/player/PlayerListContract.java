package com.lumbralessoftware.voterussia2018.player;

import com.lumbralessoftware.voterussia2018.BaseView;
import com.lumbralessoftware.voterussia2018.Player;

import java.util.List;

/**
 * Created by javiergonzalezcabezas on 21/5/18.
 */

public class PlayerListContract {

    interface Presenter {
        void fetch();
    }

    interface View extends BaseView<Presenter> {
        void showPlayer(List<Player> list);

        void showError();

        void setLoadingIndicator(boolean active);

    }
}
