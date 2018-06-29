package com.lumbralessoftware.voterussia2018.rating

import com.lumbralessoftware.voterussia2018.BaseView

/**
 * Created by javiergonzalezcabezas on 9/6/18.
 */
interface RatingContract {

    interface Presenter {
        fun sumit()

        fun getVotes()

        fun addValueVote(vote: Int)

        fun updateVote()

        fun calculateVote(): Float

        fun updatePlayer(vote: Float)
    }

    interface View : BaseView<Presenter> {

        fun setTitle()

        fun setMessageRating()

        fun showError()

        fun dismissDialog()

        fun noInternet()

        fun successfulVote()

        fun votedMessage()

    }
}