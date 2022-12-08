package com.quixada.ufc.projectx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.quixada.ufc.projectx.databinding.ActivityEliminatePlayerBinding

class CrewMembersEliminatePlayerActivity : AppCompatActivity(), CharacterClickListener {
    private lateinit var binding: ActivityEliminatePlayerBinding
    private lateinit var playersList : CharacterList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEliminatePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newPlayerList = intent.extras?.get("playerList") as CharacterList
        playersList = newPlayerList

        val mainActivity = this
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = CardAdapter(playersList.charactersList, mainActivity)
        }
    }
    override fun onClick(character: Character) {
        isTraitorVoting = true
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(Character_ID_EXTRA, character.id)
        intent.putExtra("playerList", playersList)
        startActivity(intent)
    }

}