package com.quixada.ufc.projectx

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import kotlin.math.roundToInt
import com.quixada.ufc.projectx.*
import com.quixada.ufc.projectx.databinding.ActivityTimerBinding
import java.util.*
import java.util.Timer

class Timer : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private var timerStarted = true
    private lateinit var serviceIntent: Intent
    private var time = 0.0
    private lateinit var timer: CountDownTimer
    private lateinit var playersList : CharacterList

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newPlayerList = intent.extras?.get("playerList") as CharacterList
        playersList = newPlayerList

        val textV = findViewById<TextView>(R.id.timeTV)

        timer = object : CountDownTimer(240000,1000){
            override fun onTick(millisUntilFinished: Long) {
                textV.setText("Seconds Remaining: " + millisUntilFinished / 1000)
            }

            override fun onFinish() {
                val intent = Intent(applicationContext, EliminatePlayerActivity::class.java)
                intent.putExtra("playerList", playersList)
                startActivity(intent)
            }

        }

       // val votBtn = findViewById<Button>(R.id.startVot)

       // votBtn.setOnClickListener{
           // val intent = Intent(applicationContext, Voting::class.java)
           // startActivity(intent)
        //}
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