package com.quixada.ufc.projectx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.marginTop
import kotlinx.coroutines.*

class SortRolesActivity : AppCompatActivity() {
    private var playersList = CharacterList(charactersList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort_roles)

        populateCharacters()
        populateRoles()
        shuffleCharactersArray()
        sortRoles()
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
            //TODO narrate that every player must sleep now

            runOnUiThread {
                Thread.sleep(5000)
                playerRoleContainer.isInvisible = false
            }

            for (character in charactersList) {
                runOnUiThread { characterName.text = character.title }
                runOnUiThread { characterRole.text = character.role.roleName }
                runOnUiThread { characterRoleDescription.text = character.role.roleDescription }
                //TODO narrate that this specific player must wake up to see his/her role
                Thread.sleep(20000)
                //TODO narrate that this specific player must sleep now
            }
            val intent = Intent(applicationContext, Timer::class.java)
            intent.putExtra("playerList", playersList)
            startActivity(intent)
        }).start()

        //TODO start the timer activity in the end of this function

    }

    private fun narratePlayersRoles () {
        //TODO narrate function
    }

    private fun sortRoles() {
        var playerCount = charactersList.size-1
        for(character in charactersList) {
            character.role = setRole(playerCount)
            playerCount--
        }
    }

    private fun setRole(playerCount : Int) : Role {

        if (playerCount == 0) {
            val hasTraitor = hasTraitor()
            if(!hasTraitor) {
                for(role in roles) {
                    if(role.roleName == "Traitor") return role
                }
            }
        }

        val rolesCount = roles.size-1
        val roleIdx = (0..rolesCount).random()
        val randomRole : Role = roles[roleIdx]
        if(randomRole.roleName != "Crew member") roles.removeAt(roleIdx)

        return randomRole
    }

    //this function is to guarantee that every match has a traitor
    private fun hasTraitor () : Boolean {
        var hasTraitor = false

        for(character in charactersList) {
            if(character.role.roleName == "Traitor") hasTraitor = true
        }

        return hasTraitor
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

        val character7 = Character(
            R.drawable.rockerboy,
            "Rockerboy",
            getString(R.string.description_place_holder)
        )
        charactersList.add(character7)

        val character8 = Character(
            R.drawable.solo,
            "Solo",
            getString(R.string.description_place_holder)
        )
        charactersList.add(character8)

        val character9 = Character(
            R.drawable.techie,
            "Techie",
            getString(R.string.description_place_holder)
        )
        charactersList.add(character9)
    }

    private fun populateRoles () {
        val role1 = Role(
            "Traitor",
            "You are the traitor. Kill all crews members to win the game." +
                    " Traitor can only kill one crew member peer round."
        )
        roles.add(role1)

        val role2 = Role(
            "Suicidal",
            "You win the game if you are voted out or killed, make a lot of trouble so the crew members" +
                    " think you are the traitor."+
                " You can always skip vote if you want."
        )

        roles.add(role2)

        val role3 = Role(
            "Hitman",
            "You notice there's a traitor among the crew members." +
                    " Be the last one alive to win the game." +
                    " Hitman can only kill one crew member peer round." +
                    " You must skip vote every time."
        )

        roles.add(role3)

        val role4 = Role(
            "Punisher",
            "You are part of the crew and can kill one crew member you suspect is the traitor" +
                    " without consequences. Vote out all traitors to win the game"
        )

        roles.add(role4)

        val role5 = Role(
            "Sheriff",
            "You are part of the crew and can kill any crew member you suspect is the traitor" +
                    " but if you kill an innocent crewman you are eliminated. Vote out all traitors to win the game"
        )

        roles.add(role5)

        val role6 = Role(
            "Crew member",
            "You are part of the crew. Vote out all traitors to win the game"
        )

        roles.add(role6)
    }
}
