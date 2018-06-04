package com.lumbralessoftware.voterussia2018.player

/**
 * Created by javiergonzalezcabezas on 4/6/18.
 */
class Player {
    private var club: String? = null
    private var id: Int? = null
    private var imageURL: String? = null
    private var name: String? = null
    private var position: String? = null
    private var team: String? = null
    private var vote: Int? = null

    fun getClub(): String? {
        return club
    }

    fun setClub(club: String) {
        this.club = club
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getImageURL(): String? {
        return imageURL
    }

    fun setImageURL(imageURL: String) {
        this.imageURL = imageURL
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getPosition(): String? {
        return position
    }

    fun setPosition(position: String) {
        this.position = position
    }

    fun getTeam(): String? {
        return team
    }

    fun setTeam(team: String) {
        this.team = team
    }

    fun getVote(): Int? {
        return vote
    }

    fun setVote(vote: Int?) {
        this.vote = vote
    }

}