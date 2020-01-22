package com.example.loginapplicationrealm

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm
    private lateinit var username: EditText
    private lateinit var userPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        Realm.init(this)
        realm = Realm.getDefaultInstance()
    }

    private fun initViews() {
        username = findViewById(R.id.usernameET)
        userPassword = findViewById(R.id.userPasswordET)
    }

    private fun getName(): String {
        return username.text.toString()
    }

    private fun getPassword(): String {
        return this.userPassword.text.toString()
    }

    fun login(view: View) {
        val name = getName()
        val password = getPassword()

        if (!getName().isValidUsername()) {
            Toast.makeText(this, "Username is not Valid", Toast.LENGTH_LONG).show()
        }

        if (!getPassword().isValidPassword()) {
            Toast.makeText(this, "Password is not Valid", Toast.LENGTH_LONG).show()
        } else if (name.isValidUsername() && password.isValidPassword()) {
            checkDatabaseAndShowResult()
        }
    }

    private fun checkDatabaseAndShowResult() {
        val result = realm.where(User::class.java)
            .equalTo("name", getName())
            .and().contains("password", getPassword())
            .findAll()
        if (result.isNotEmpty()) {
            Toast.makeText(this, "Log in successful", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "User Not Found", Toast.LENGTH_LONG).show()
        }
    }

    private fun String.isValidUsername(): Boolean {
        return this.isNotEmpty() && this.length in 5..25
    }

    private fun String.isValidPassword(): Boolean {
        return this.length in 8..15
    }

    fun register(view: View) {

        if (!getName().isValidUsername()) {
            Toast.makeText(this, "Username is not Valid", Toast.LENGTH_LONG).show()
        }
        if (!getPassword().isValidPassword()) {
            Toast.makeText(this, "Password is not Valid", Toast.LENGTH_LONG).show()
        }
        val user = User()

        user.name = getName()
        user.password = getPassword()

        realm.beginTransaction()
        realm.copyToRealm(user)
        realm.commitTransaction()
    }
}
