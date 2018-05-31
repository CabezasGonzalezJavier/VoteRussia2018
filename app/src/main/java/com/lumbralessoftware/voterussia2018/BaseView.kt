package com.lumbralessoftware.voterussia2018

/**
 * Created by javiergonzalezcabezas on 31/5/18.
 */
interface BaseView<T>{
    abstract fun setPresenter(presenter: T)
}