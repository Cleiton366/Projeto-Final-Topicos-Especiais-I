package com.quixada.ufc.projectx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.quixada.ufc.projectx.databinding.ActivityEliminatePlayerBinding

class EliminatePlayerActivity : AppCompatActivity(), CharacterClickListener {
    private lateinit var binding: ActivityEliminatePlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEliminatePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainActivity = this
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(applicationContext, 2)
            adapter = CardAdapter(charactersList, mainActivity)
        }
    }
    override fun onClick(character: Character) {
        val intent = Intent(applicationContext, DetailActivity::class.java)
        intent.putExtra(Character_ID_EXTRA, character.id)
        startActivity(intent)
    }

}