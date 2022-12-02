package com.quixada.ufc.projectx

import java.io.Serializable

var charactersList = mutableListOf<Character>()
//TODO use this array bellow to show eliminated players on timer as a list so players know they've been eliminated next round
var eliminatedCharactersList = mutableListOf<Character>()

val Character_ID_EXTRA = "characterExtra"

class Character (
    var cover: Int,
    var title: String,
    var description: String,
    val id: Int? = charactersList.size,
    var role: Role = Role(
        "Crew member",
        "You are part of the crew. Vote out all traitors to win the game"
    )
) : Serializable