package com.quixada.ufc.projectx

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.GridLayoutManager
import com.quixada.ufc.projectx.databinding.ActivityEliminatePlayerBinding

class TraitorsEliminatePlayerActivity : AppCompatActivity(), CharacterClickListener {
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

        val eliminatePlayerContainer: ConstraintLayout = findViewById(R.id.eliminate_player_container)
        eliminatePlayerContainer.isInvisible = true

        var mediaPlayer = MediaPlayer.create(this, R.raw.crews_members_sleep)
        mediaPlayer.start()

        Thread.sleep(5000)

        mediaPlayer = MediaPlayer.create(this, R.raw.traitor_wakeu_up)
        mediaPlayer.start()

        Thread.sleep(3000)
        eliminatePlayerContainer.isInvisible = false
    }

    override fun onClick(character: Character) {
        isTraitorVoting = false
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(Character_ID_EXTRA, character.id)
        intent.putExtra("playerList", playersList)
        startActivity(intent)
    }
}