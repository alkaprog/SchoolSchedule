package com.example.schoolshedule

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import kotlin.math.log

class ItemsList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_list)

        val subjectName = intent.getStringExtra("SubjectName")

        var listOfItems = resources.getStringArray(
            resources.getIdentifier(
                subjectName,"array",
                packageName
            )
        )

        var lv: LinearLayout = findViewById(R.id.items_list)

        for (item in listOfItems) {
            val tv = TextView(this)
            tv.text = item
            tv.textSize = 18F

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(50, 10, 0, 10)
            tv.layoutParams = params

            lv.addView(tv)
        }
    }
}