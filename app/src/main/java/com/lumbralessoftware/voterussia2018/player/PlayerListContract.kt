package com.lumbralessoftware.voterussia2018.player

import com.lumbralessoftware.voterussia2018.BaseView
import com.lumbralessoftware.voterussia2018.NewPlayer

/**
 * Created by javiergonzalezcabezas on 31/5/18.
 */
interface PlayerListContract {

    interface Presenter {
        fun fetch()

        fun fetchPlayerWithPosition(position :Int)

        fun fetchPlayerWithTeam(team :Int)

        fun goToRating(id: Int, name: String, image:String)
    }

    interface View : BaseView<Presenter> {
        fun showPlayer(list: List<NewPlayer>)

        fun showError()

        fun setLoadingIndicator(active: Boolean)

        fun noInternet()

    }
}