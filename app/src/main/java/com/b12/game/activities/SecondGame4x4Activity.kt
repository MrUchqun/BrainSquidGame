package com.b12.game.activities

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.*
import com.b12.game.Base
import com.b12.game.CustomWinnerAlert
import com.b12.game.R
import java.util.*

class SecondGame4x4Activity : AppCompatActivity() {
    private val keyTimer: String = "Timer4x4"
    private val keyStepsCount: String = "Steps4x4"
    private var gridLayout: GridLayout? = null
    private var clickSound: MediaPlayer? = null
    private var btn: Button? = null
    private var invisiblebtn: Button? = null
    private var chronometer: Chronometer? = null
    private var x = 3
    private var y = 3
    private var btnCount: Int? = 0
    private var checkWin = false
    private var isStarted = false
    private var stepCount = 0
    private var newRecord = false
    private val numbers = listOf<String>("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15")
    private var nextNumbers: MutableList<String> =
        mutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15")
    private val map = TreeMap<String, Drawable>()


    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_game4x4)

        gridLayout = findViewById(R.id.grid_4x4)

        //clickSound = MediaPlayer.create(this, R.raw.click)

        invisiblebtn = findViewById(R.id.btn4x4_16)
        btnCount = gridLayout?.childCount
        btn = gridLayout?.getChildAt(btnCount!!-1) as Button?
        btn?.visibility = View.INVISIBLE
        btn?.text = ""
        chronometer = findViewById(R.id.chronometer4x4)
        chronometer?.base = SystemClock.elapsedRealtime()
        loadMap()
        loadNumbers(numbers)

        findViewById<ImageView>(R.id.help_for_4x4).setOnClickListener {
            Toast.makeText(applicationContext,"Press 2 seconds to see image", Toast.LENGTH_SHORT).show()
        }

        findViewById<ImageView>(R.id.help_for_4x4).setOnLongClickListener(View.OnLongClickListener {
            Log.d("Click", "Clicked")
            val countDownTimer = object: CountDownTimer(2000, 1000){
                override fun onTick(p0: Long) {
                    findViewById<ImageView>(R.id.real_pic_4x4).visibility = View.VISIBLE
                }

                override fun onFinish() {
                    findViewById<ImageView>(R.id.real_pic_4x4).visibility = View.GONE
                }
            }
            countDownTimer.start()
            return@OnLongClickListener true
        })

    }

    fun onClick4x4(view: View) {
        if (isStarted) {
            var btn1 = view as Button?
            val button: Button?
            val tx = btn1?.tag.toString()[1].code - '0'.code
            val ty = btn1?.tag.toString()[0].code - '0'.code

            if (Math.abs(x - tx) == 1 && Math.abs(y - ty) == 0 || Math.abs(x - tx) == 0 && Math.abs(y - ty) == 1) {
                x = tx
                y = ty
                //if (Base.getInstance()?.getIsValumeOn("Volume")!!) clickSound?.start()

                if (btn?.text?.isEmpty() == true) {
                    btn?.text = btn1?.text
                    btn1?.text = ""
                    btn?.visibility = View.VISIBLE
                    btn1?.visibility = View.INVISIBLE
                    button = btn1
                    btn1 = btn
                    btn = button

                    var drawable = btn?.background
                    btn?.background = btn1?.background
                    btn1?.background = drawable
                }
                stepCount++
                findViewById<TextView>(R.id.stepCount4x4).text = stepCount.toString()
                getNumbers()
                if (isCheckWin()){
                    checkRecord(chronometer?.text.toString(), stepCount)

                    chronometer?.stop()
//                    val winningSound: MediaPlayer = MediaPlayer.create(this, R.raw.applause)
//                    if (Base.getInstance()?.getIsValumeOn("Volume")!!) winningSound.start()

                    val customWinnerAlert = CustomWinnerAlert(this, keyTimer, keyStepsCount)
                    customWinnerAlert.setTimer(chronometer?.text.toString())
                    customWinnerAlert.setSteps(stepCount)
                    customWinnerAlert.setIsRecord(newRecord)

                    customWinnerAlert.show()
                    customWinnerAlert.getHome()?.setOnClickListener {
//                        winningSound.stop()
                        finish()
                    }
                    customWinnerAlert.getReplay()?.setOnClickListener {
                        restart()
                        customWinnerAlert.dismiss()
                    }
                }
            }
        }
    }

    fun loadMap(){
        for (i in 0 until btnCount!! - 1) {
            map[numbers[i]] = (gridLayout?.getChildAt(i) as Button).background

        }
    }

    fun loadNumbers(numbers1: List<String>) {
        for (i in 0 until btnCount!! - 1) {
            (gridLayout?.getChildAt(i) as Button).text = numbers1[i]
            (gridLayout?.getChildAt(i) as Button).background = map[numbers1[i]]
        }
    }

    fun getNumbers() {
        for (i in 0 until btnCount!! - 1) {
            nextNumbers[i] = (gridLayout?.getChildAt(i) as Button).text.toString()
        }
    }

    fun isCheckWin(): Boolean {
        var temp = 0
        for (i in 0 until btnCount!! - 1) {
            if (nextNumbers[i] == numbers[i]) {
                temp++
            }
        }
        checkWin = temp == btnCount!! - 1
        return checkWin
    }

    fun startGame4x4(view: View){
//        if (Base.getInstance()?.getIsValumeOn("Volume")!!) clickSound?.start()
        if (!isStarted || checkWin){
            isStarted = true
            loadNumbers(numbers.shuffled())
            findViewById<TextView>(R.id.start4x4text).text = "RESTART"
            findViewById<ImageView>(R.id.start4x4img).setImageResource(R.drawable.ic_baseline_refresh_black_24)
            chronometer?.base = SystemClock.elapsedRealtime()
            chronometer?.start()
        } else {
            isStarted = true
            restart()
            findViewById<TextView>(R.id.start4x4text).text = "RESTART"
            findViewById<ImageView>(R.id.start4x4img).setImageResource(R.drawable.ic_baseline_refresh_black_24)
            chronometer?.start()
//            loadNumbers(numbers.shuffled())
        }
    }

    fun restart(){
        btn?.visibility = View.VISIBLE
        btn = invisiblebtn
        invisiblebtn?.visibility = View.INVISIBLE
        invisiblebtn?.text = ""
        loadNumbers(numbers.shuffled())
        x=3
        y=3
        chronometer?.base = SystemClock.elapsedRealtime()
        stepCount = 0
        findViewById<TextView>(R.id.stepCount4x4).text = stepCount.toString()
    }

    fun checkRecord(msg: String, steps: Int){
        val msgMinut = (msg[0].toString()+msg[1].toString()).toInt()
        val msgSecund = (msg[3].toString()+msg[4].toString()).toInt()
        val recordTime = Base.getInstance()?.getFinishedTime(keyTimer)
        val recordSteps = Base.getInstance()?.getStepCount(keyStepsCount)
        val recordMinut = (recordTime?.get(0).toString()+recordTime?.get(1).toString()).toInt()
        val recordSecund = (recordTime?.get(3).toString()+recordTime?.get(4).toString()).toInt()

        if (msgMinut < recordMinut || msgMinut == recordMinut && msgSecund < recordSecund || msgMinut == recordMinut && msgSecund == recordSecund && steps < recordSteps!! || recordTime.equals("00:00")){
            Base.getInstance()?.setFinishedTime(keyTimer, msg)
            Base.getInstance()?.setStepCount(keyStepsCount, steps)
            newRecord = true
        } else newRecord = false
    }
}