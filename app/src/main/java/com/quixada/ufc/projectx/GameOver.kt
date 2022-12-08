package com.quixada.ufc.projectx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class GameOver : AppCompatActivity() {
    private lateinit var playersList : CharacterList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val winner = intent.extras?.get("winner")
        val newPlayerList = intent.extras?.get("playerList") as CharacterList
        playersList = newPlayerList

        val returnMenuBtn = findViewById<LinearLayout>(R.id.return_to_menu)
        val winnerTittle = findViewById<TextView>(R.id.winner_tittle)
        val winnerDescription = findViewById<TextView>(R.id.winner_description)

        if(winner == "crew members") {
            winnerTittle.text = "Crew members won"
            winnerDescription.text = "Crew members eliminated the traitor"
        } else if(winner == "traitor") {
            lateinit var winnerCharacter : Character
            for(character in playersList.charactersList) {
                if(character.role.roleName == "Traitor") winnerCharacter = character
            }
            winnerTittle.text = "${winnerCharacter.title} won"
            winnerDescription.text = "${winnerCharacter.title} was the ${winnerCharacter.role.roleName}"
        }

        returnMenuBtn.setOnClickListener {
            val intent = Intent(applicationContext, MainMenu::class.java)
            startActivity(intent)
        }
    }
}