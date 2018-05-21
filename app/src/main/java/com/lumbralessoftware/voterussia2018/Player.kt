package com.lumbralessoftware.voterussia2018

/**
 * Created by javiergonzalezcabezas on 21/5/18.
 */
class Player {

    internal var id: Int = 0
    internal var vote: Int = 0
    internal var name: String = ""
    internal var imageURL: String = ""
    internal var position: String = ""

    fun Playerr(id: Int, vote: Int, name: String, imageURL: String, position: String): ??? {
        this.id = id
        this.vote = vote
        this.name = name
        this.imageURL = imageURL
        this.position = position
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getVote(): Int {
        return vote
    }

    fun setVote(vote: Int) {
        this.vote = vote
    }

    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getImageURL(): String {
        return imageURL
    }

    fun setImageURL(imageURL: String) {
        this.imageURL = imageURL
    }

    fun getPosition(): String {
        return position
    }

    fun setPosition(position: String) {
        this.position = position
    }
}