package com.example.schoolshedule

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.R.attr.right

import android.R.attr.left
import android.content.Intent
import android.content.SharedPreferences
import android.view.View


class MainActivity : AppCompatActivity() {
    private lateinit var pref: SharedPreferences
    private var subjectSchedule = arrayOf<Array<String>>()
    private var dayViews = arrayOf<LinearLayout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fillSchedule()
        addListenersToTitles()
        init()
    }

    private fun addListenersToTitles() {
        var tv: TextView = findViewById(R.id.title_monday)
        var txt: String = tv.text as String
        tv.setOnClickListener {
            val intent = Intent(this, ItemsForParticularDay::class.java).putExtra(
                "SubjectName",
                txt
            )
            startActivity(intent)
        }

        tv = findViewById(R.id.title_tuesday)
        txt = tv.text as String
        tv.setOnClickListener {
            val intent = Intent(this, ItemsForParticularDay::class.java).putExtra(
                "SubjectName",
                txt
            )
            startActivity(intent)
        }

        tv = findViewById(R.id.title_wednesday)
        txt = tv.text as String
        tv.setOnClickListener {
            val intent = Intent(this, ItemsForParticularDay::class.java).putExtra(
                "SubjectName",
                txt
            )
            startActivity(intent)
        }

        tv = findViewById(R.id.title_thursday)
        txt = tv.text as String
        tv.setOnClickListener {
            val intent = Intent(this, ItemsForParticularDay::class.java).putExtra(
                "SubjectName",
                txt
            )
            startActivity(intent)
        }

        tv = findViewById(R.id.title_friday)
        txt = tv.text as String
        tv.setOnClickListener {
            val intent = Intent(this, ItemsForParticularDay::class.java).putExtra(
                "SubjectName",
                txt
            )
            startActivity(intent)
        }

        tv = findViewById(R.id.title_saturday)
        txt = tv.text as String
        tv.setOnClickListener {
            val intent = Intent(this, ItemsForParticularDay::class.java).putExtra(
                "SubjectName",
                txt
            )
            startActivity(intent)
        }
    }

    private fun fillSchedule() {
        for (i in 0..5) {
            var array = resources.getStringArray(stringArrayByNum(i))
            subjectSchedule += array
        }

        for (i in 0..5) {
            var view: LinearLayout = findViewById(viewIdByNum(i))
            dayViews += view
        }

        for (i in 0..5) {
            for (subject in subjectSchedule[i]) {
                val tv = TextView(this)
                tv.text = subject
                tv.textSize = 18F
                tv.setOnClickListener {
                    val intent = Intent(this, ItemsList::class.java).putExtra(
                        "SubjectName",
                        subject.replace(" ", "")
                    )
                    startActivity(intent)
                }
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(50, 10, 0, 10)

                tv.layoutParams = params
                dayViews[i].addView(tv)
            }
        }
    }

    private fun stringArrayByNum(value: Int): Int {
        val array = when (value) {
            0 -> R.array.monday
            1 -> R.array.tuesday
            2 -> R.array.wednesday
            3 -> R.array.thursday
            4 -> R.array.friday
            else -> R.array.saturday
        }
        return array
    }

    private fun viewIdByNum(value: Int): Int {
        val id = when (value) {
            0 -> R.id.monday
            1 -> R.id.tuesday
            2 -> R.id.wednesday
            3 -> R.id.thursday
            4 -> R.id.friday
            else -> R.id.saturday
        }
        return id
    }

    private fun init() {
        pref = getSharedPreferences("UserData", MODE_PRIVATE)
    }

}