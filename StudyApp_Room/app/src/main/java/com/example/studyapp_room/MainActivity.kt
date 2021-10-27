package com.example.studyapp_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()

        var android = findViewById<ImageButton>(R.id.android)
        var kotlin = findViewById<ImageButton>(R.id.kotlin)

        android.setOnClickListener {
            val intent = Intent( this , AndroidActivity::class.java)
            startActivity(intent)
        }

        kotlin.setOnClickListener {
            val intent = Intent( this , KotlinActivity::class.java)
            startActivity(intent)}
    }
}