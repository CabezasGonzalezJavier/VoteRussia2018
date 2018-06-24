package com.lumbralessoftware.voterussia2018

/**
 * Created by javiergonzalezcabezas on 7/6/18.
 */
class Player {
    private var club: String? = null
    private var goalsAgainst: Int? = null
    private var goalsFavor: Int? = null
    private var id: Int? = null
    private var imageURL: String? = null
    private var name: String? = null
    private var number: String? = null
    private var position: Int? = null
    private var team: Int? = null
    private var vote: Float? = null

    fun getGoalsAgainst(): Int? {
        return goalsAgainst
    }

    fun setGoalsAgainst(goalsAgainst: Int) {
        this.goalsAgainst = goalsAgainst
    }

    fun getGoalsFavor(): Int? {
        return goalsFavor
    }

    fun setGoalsFavor(goalsFavor: Int) {
        this.goalsFavor = goalsFavor
    }

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

    fun getNumber(): String? {
        return number
    }

    fun setNumber(number: String) {
        this.number = number
    }

    fun getPosition(): Int? {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    fun getTeam(): Int? {
        return team
    }

    fun setTeam(team: Int) {
        this.team = team
    }

    fun getVote(): Float? {
        return vote
    }

    fun setVote(vote: Float?) {
        this.vote = vote
    }

}

class Vote {

    var sum: Int? = null
    var id: Int? = null
    var one: Int? = null
    var two: Int? = null
    var three: Int? = null
    var four: Int? = null
    var five: Int? = null
    var total: Int? = null

}


class ElementList {

    private var name: String? = null
    private var section: Boolean? = null
    private var nextSection: Boolean? = null

    constructor(name: String, section: Boolean, nextSection: Boolean) {
        this.name = name
        this.section = section
        this.nextSection = nextSection
    }

    fun isSection(): Boolean? {
        return section
    }

    fun setSection(mSection: Boolean) {
        this.section = mSection
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun ismNextSection(): Boolean? {
        return nextSection
    }

    fun setmNextSection(mNextSection: Boolean) {
        this.nextSection = mNextSection
    }

}

class NewPlayer {

    private var club: String? = null
    private var goalsFavor: String? = null
    private var id: Int? = null
    private var imageURL: String? = null
    private var name: String? = null
    private var number: String? = null
    private var position: Int? = null
    private var team: Int? = null
    private var vote: Float? = null

    fun getGoalsFavor(): String? {
        return goalsFavor
    }

    fun setGoalsFavor(goalsFavor: String) {
        this.goalsFavor = goalsFavor
    }

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

    fun getNumber(): String? {
        return number
    }

    fun setNumber(number: String) {
        this.number = number
    }

    fun getPosition(): Int? {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    fun getTeam(): Int? {
        return team
    }

    fun setTeam(team: Int) {
        this.team = team
    }

    fun getVote(): Float? {
        return vote
    }

    fun setVote(vote: Float?) {
        this.vote = vote
    }
}