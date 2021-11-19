package com.example.schoolshedule

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import javax.security.auth.Subject
import kotlin.math.log

class ItemsList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_list)

        val subjectName = intent.getStringExtra("SubjectName")

        val title: TextView = findViewById(R.id.SubjectName)
        title.text = subjectName

        var listOfItems = resources.getStringArray(
            resources.getIdentifier(
                subjectName, "array",
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

        var pref = getSharedPreferences("UserData", MODE_PRIVATE)
        var editor = pref.edit()
        if (pref.contains(subjectName)) {
            var listOfUserItems = pref.getString(subjectName, "defaultName")?.split(",")
            if (listOfUserItems != null) {
                for (item in listOfUserItems) {
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


        var textField = EditText(this)

        lv.addView(textField)


        var btn = Button(this)
        btn.text = "Добавить предмет"
        btn.setOnClickListener {
            if (textField.text.toString()!="") {
                if (subjectName != null) {
                    addAnItem(subjectName,textField.text.toString())
                }
            }
        }
        lv.addView(btn)


    }

    private fun addAnItem(subjectName: String, itemName: String) {
        var pref = getSharedPreferences("UserData", MODE_PRIVATE)
        var editor = pref.edit()
        if (pref.contains(subjectName)) {
            var row: String? = pref.getString(subjectName, "defaultName")
            var listOfUserItems = row?.split(",")

            if (listOfUserItems != null) {
                if (!listOfUserItems.contains(itemName)) {
                    if (row != null) {
                        if (row.isNotEmpty()) {
                            row += ",$itemName"
                        } else {
                            row += ",$itemName"
                        }
                        editor.putString(subjectName, row)
                        editor.commit()
                    }
                }

            }
        } else {
            editor.putString(subjectName, itemName)
            editor.commit()
        }
        recreate()
    }
}