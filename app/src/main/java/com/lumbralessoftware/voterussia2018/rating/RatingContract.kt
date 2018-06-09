package com.lumbralessoftware.voterussia2018.rating

import com.lumbralessoftware.voterussia2018.BaseView

/**
 * Created by javiergonzalezcabezas on 9/6/18.
 */
interface RatingContract {

    interface Presenter {
        fun sumit()

        fun getVotes()
    }

    interface View : BaseView<Presenter> {

        fun setTitle()

        fun setMessageRating()

        fun showError()

    }
}