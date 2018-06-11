package com.lumbralessoftware.voterussia2018

import android.content.Context
import android.net.ConnectivityManager


/**
 * Created by javiergonzalezcabezas on 11/6/18.
 */
object Utils {

    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}