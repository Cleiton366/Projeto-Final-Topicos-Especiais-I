package com.quixada.ufc.projectx

import java.io.Serializable

class CharacterList : Serializable {
    var charactersList : MutableList<Character>

    constructor(charactersList : MutableList<Character>) {
        this.charactersList = charactersList
    }
}