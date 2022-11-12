package com.quixada.ufc.projectx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        handleOnClickEvents()
    }

    private fun handleOnClickEvents () {
        val playBtn = findViewById<LinearLayout>(R.id.play_btn)
        val howToPlayBtn = findViewById<LinearLayout>(R.id.about_btn)
        val aboutBtn = findViewById<LinearLayout>(R.id.about_btn)

        playBtn.setOnClickListener {
            val intent = Intent(applicationContext, SortRolesActivity::class.java)
            startActivity(intent)
        }
        howToPlayBtn.setOnClickListener {

        }
        aboutBtn.setOnClickListener {

        }
    }
}