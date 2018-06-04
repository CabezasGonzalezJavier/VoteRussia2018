package com.lumbralessoftware.voterussia2018.player

import com.lumbralessoftware.voterussia2018.BaseView

/**
 * Created by javiergonzalezcabezas on 31/5/18.
 */
interface PlayerListContract {

    interface Presenter {
        fun fetch()
    }

    interface View : BaseView<Presenter> {
        fun showPlayer(list: List<Player>)

        fun showError()

        fun setLoadingIndicator(active: Boolean)

    }
}