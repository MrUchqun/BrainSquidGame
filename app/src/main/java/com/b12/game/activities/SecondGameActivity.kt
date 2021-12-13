package com.b12.game.activities

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import com.b12.game.Base
import com.b12.game.CustomWinnerAlert
import com.b12.game.R
import java.util.*

class SecondGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_game)

        findViewById<CardView>(R.id.game3x3).setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SecondGame3x3Activity::class.java)
            startActivity(intent)
        })
        findViewById<CardView>(R.id.game4x4).setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SecondGame4x4Activity::class.java)
            startActivity(intent)
        })
        findViewById<CardView>(R.id.game5x5).setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SecondGame5x5Activity::class.java)
            startActivity(intent)
        })

    }
}