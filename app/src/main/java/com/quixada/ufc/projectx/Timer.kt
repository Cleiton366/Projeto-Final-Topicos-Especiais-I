package com.quixada.ufc.projectx

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isInvisible
import com.quixada.ufc.projectx.databinding.ActivityTimerBinding

class Timer : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private var timerStarted = true
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    private lateinit var timer: CountDownTimer
    private lateinit var playersList : CharacterList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mediaPlayer = MediaPlayer.create(this, R.raw.crew_members_wake_up)
        mediaPlayer.start()

        val newPlayerList = intent.extras?.get("playerList") as CharacterList
        playersList = newPlayerList

        val textV = findViewById<TextView>(R.id.timeTV)

        val eliminatedContainer = findViewById<LinearLayout>(R.id.players_eliminated_container)
        if(eliminatedCharactersList.size == 0) {
            eliminatedContainer.isInvisible = true
        } else {
            var eliminatedPlayers = ""
            for(character in eliminatedCharactersList) {
                eliminatedPlayers = "${eliminatedPlayers}, ${character.title } "
            }
            val eliminatedPlayersText = findViewById<TextView>(R.id.players_eliminated)
            eliminatedPlayersText.text = eliminatedPlayers
        }

        val voteButton = findViewById<Button>(R.id.voteButton)
        voteButton.setOnClickListener{
            val intent = Intent(applicationContext, CrewMembersEliminatePlayerActivity::class.java)
            intent.putExtra("playerList", playersList)
            startActivity(intent)
        }

        timer = object : CountDownTimer(240000,1000){

            override fun onTick(millisUntilFinished: Long) {
                textV.setText("Seconds Remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                val intent = Intent(applicationContext, CrewMembersEliminatePlayerActivity::class.java)
                intent.putExtra("playerList", playersList)
                startActivity(intent)
            }

        }
    }

    override fun onStart() {
        super.onStart()
        timer.start()
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
    }
}