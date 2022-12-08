package com.quixada.ufc.projectx

import java.io.Serializable

var charactersList = mutableListOf<Character>()
var eliminatedCharactersList = mutableListOf<Character>()
var isTraitorVoting = false

val Character_ID_EXTRA = "characterExtra"

class Character (
    var cover: Int,
    var title: String,
    var description: String,
    val id: Int? = charactersList.size,
    var role: Role = Role(
        "Crew member",
        "You are part of the crew. Vote out the traitor to win the game"
    )
) : Serializable