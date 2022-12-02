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

        val characterID = intent.getIntExtra(Character_ID_EXTRA, -1)
        val character = characterFromID(characterID)
        this.character = character!!
        if(character != null) {
            binding.cover.setImageResource(character.cover)
            binding.title.text = character.title
            binding.description.text = character.description
        }
        val newPlayerList = intent.extras?.get("playerList") as CharacterList
        playersList = newPlayerList

        val eliminatePLayerBtn = findViewById<LinearLayout>(R.id.kill_btn)
        eliminatePLayerBtn.setOnClickListener {
            eliminatePlayer(characterID)
        }
    }

    private fun characterFromID(characterID: Int): Character? {
        for(character in playersList.charactersList) {
            if(character.id == characterID)
                return character
        }
        return null
    }

    private fun eliminatePlayer(characterID: Int) {
        eliminatedCharactersList.add(character)
        playersList.charactersList.removeAt(characterID)
        //TODO go back to timer activity
        //if(character.role == )
        val intent = Intent(applicationContext, Timer::class.java)
        intent.putExtra("playerList", playersList)
        startActivity(intent)
    }
}