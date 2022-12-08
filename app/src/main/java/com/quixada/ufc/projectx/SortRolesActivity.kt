package com.quixada.ufc.projectx

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isInvisible
import kotlinx.coroutines.*
import java.lang.Math.random

class SortRolesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort_roles)

        populateCharacters()
        populateRoles()
        sortRoles()
        shuffleCharactersArray()
        val playerRoleContainer: LinearLayout = findViewById(R.id.player_role_container)
        val beforePlay : LinearLayout = findViewById(R.id.before_you_play_container)
        val playBtn: LinearLayout = findViewById(R.id.play_btn)

        playerRoleContainer.isInvisible = true

        playBtn.setOnClickListener {
            beforePlay.isInvisible = true
            val beforePlayParams = beforePlay.layoutParams as ViewGroup.MarginLayoutParams
            beforePlayParams.setMargins(0, 0, 0, 0)
            beforePlayParams.width = 0
            beforePlayParams.height = 0
            showAllPlayersRoles()
        }
    }

    private fun showAllPlayersRoles() {
        val playerRoleContainer: LinearLayout = findViewById(R.id.player_role_container)
        val characterName = findViewById<TextView>(R.id.character_name)
        val characterRole = findViewById<TextView>(R.id.character_role)
        val characterRoleDescription = findViewById<TextView>(R.id.character_role_description)

        Thread(Runnable {

            runOnUiThread {
                playerRoleContainer.isInvisible = false
                narratePlayersSleep("Crew member")
            }
            runOnUiThread { Thread.sleep(5000) }

            for (character in charactersList) {
                runOnUiThread { playerRoleContainer.isInvisible = false }
                runOnUiThread { characterName.text = character.title }
                runOnUiThread { characterRole.text = character.role.roleName }
                runOnUiThread { characterRoleDescription.text = character.role.roleDescription }
                runOnUiThread { narratePlayersWakeUp(character.title) }
                Thread.sleep( 10000)
                runOnUiThread { playerRoleContainer.isInvisible = true }
                runOnUiThread { narratePlayersSleep(character.title) }
                Thread.sleep( 5000)
            }
            val intent = Intent(applicationContext, Timer::class.java)
            var playersList = CharacterList(charactersList)
            intent.putExtra("playerList", playersList)
            startActivity(intent)
        }).start()
    }

    private fun narratePlayersSleep (character : String) {
        when(character) {
            "Journalist" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.journalist_sleep)
                mediaPlayer.start()
            }
            "Rockerboy" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.rockerboy_sleep)
                mediaPlayer.start()
            }
            "Cop" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.cop_sleep)
                mediaPlayer.start()
            }
            "Techie" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.techie_sleep)
                mediaPlayer.start()
            }
            "Fixer" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.fixer_sleep)
                mediaPlayer.start()
            }
            "Netrunner" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.netrunner_sleep)
                mediaPlayer.start()
            }
            "Corporate" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.corporate_sleep)
                mediaPlayer.start()
            }
            "Solo" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.solo_sleep)
                mediaPlayer.start()
            }
            "Nomad" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.nomad_sleep)
                mediaPlayer.start()
            }
            "Crew member" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.crews_members_sleep)
                mediaPlayer.start()
            }
        }
    }
    private fun narratePlayersWakeUp (character : String) {
        when(character) {
            "Journalist" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.journalist_wake_up)
                mediaPlayer.start()
            }
            "Rockerboy" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.rockerboy_wake_up)
                mediaPlayer.start()
            }
            "Cop" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.cop_wake_up)
                mediaPlayer.start()
            }
            "Techie" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.techie_wake_up)
                mediaPlayer.start()
            }
            "Fixer" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.fixer_wake_up)
                mediaPlayer.start()
            }
            "Netrunner" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.netrunner_wake_up)
                mediaPlayer.start()
            }
            "Corporate" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.corporate_wake_up)
                mediaPlayer.start()
            }
            "Solo" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.solo_wake_up)
                mediaPlayer.start()
            }
            "Nomad" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.nomad_wake_up)
                mediaPlayer.start()
            }
            "Crew member" -> {
                val mediaPlayer = MediaPlayer.create(this, R.raw.crew_members_wake_up)
                mediaPlayer.start()
            }
        }
    }

    private fun sortRoles() {
        val randomIdx = (0..5).random()
        charactersList[randomIdx].role = roles[0]
    }

    private fun shuffleCharactersArray ()  {
        charactersList = charactersList.shuffled() as MutableList<Character>
    }

    private fun populateCharacters() {
        val character1 = Character(
            R.drawable.cop,
            "Cop",
            getString(R.string.description_place_holder))
        charactersList.add(character1)

        val character2 = Character(
            R.drawable.corporate,
            "Corporate",
            getString(R.string.description_place_holder)
        )
        charactersList.add(character2)

        val character3 = Character(
            R.drawable.fixer,
            "Fixer",
            getString(R.string.description_place_holder)
        )
        charactersList.add(character3)

        val character4 = Character(
            R.drawable.journalist,
            "Journalist",
            getString(R.string.description_place_holder)
        )
        charactersList.add(character4)

        val character5 = Character(
            R.drawable.netrunner,
            "Netrunner",
            getString(R.string.description_place_holder)
        )
        charactersList.add(character5)

        val character6 = Character(
            R.drawable.nomad,
            "Nomad",
            getString(R.string.description_place_holder)
        )
        charactersList.add(character6)
    }

    private fun populateRoles () {
        val role1 = Role(
            "Traitor",
            "You are the traitor. Kill all crews members to win the game." +
                    " Traitor can only kill one crew member peer round."
        )
        roles.add(role1)

        val role6 = Role(
            "Crew member",
            "You are part of the crew. Vote out all traitors to win the game"
        )

        roles.add(role6)
    }
}
