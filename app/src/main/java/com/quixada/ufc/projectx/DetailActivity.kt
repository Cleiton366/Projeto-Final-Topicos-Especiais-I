package com.quixada.ufc.projectx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.quixada.ufc.projectx.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var playersList : CharacterList
    lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newPlayerList = intent.extras?.get("playerList") as CharacterList
        playersList = newPlayerList

        val characterID = intent.getIntExtra(Character_ID_EXTRA, -1)
        val character = characterFromID(characterID)
        this.character = character!!
        if(character != null) {
            binding.cover.setImageResource(character.cover)
            binding.title.text = character.title
            binding.description.text = character.description
        }

        val eliminatePLayerBtn = findViewById<LinearLayout>(R.id.kill_btn)
        eliminatePLayerBtn.setOnClickListener {
            eliminatePlayer(character)
        }
    }

    private fun characterFromID(characterID: Int): Character? {
        for(character in playersList.charactersList) {
            if(character.id == characterID)
                return character
        }
        return null
    }

    private fun eliminatePlayer(character: Character) {
        eliminatedCharactersList.add(character)
        playersList.charactersList.remove(character)
        var crewMembersQtd = 0

        var hasTraitor = false
        var hasCrew = false

        for(character in playersList.charactersList) {
            if(character.role.roleName == "Traitor") hasTraitor = true
            if(character.role.roleName == "Crew member") {
                hasCrew = true
                crewMembersQtd++
            }
        }

        if (!hasTraitor) {
            //crew members wins
            val intent = Intent(applicationContext, GameOver::class.java)
            intent.putExtra("winner", "crew members")
            intent.putExtra("playerList", playersList)
            startActivity(intent)
        } else if(crewMembersQtd == 1) {
            //traitor wins
            val intent = Intent(applicationContext, GameOver::class.java)
            intent.putExtra("playerList", playersList)
            intent.putExtra("winner", "traitor")
            startActivity(intent)
        } else if(isTraitorVoting) {
            val intent = Intent(applicationContext, TraitorsEliminatePlayerActivity::class.java)
            intent.putExtra("playerList", playersList)
            startActivity(intent)
        } else {
            val intent = Intent(applicationContext, Timer::class.java)
            intent.putExtra("playerList", playersList)
            startActivity(intent)
        }
    }

}