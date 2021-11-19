package com.example.schoolshedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class ItemsForParticularDay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_for_particular_day)
        val dayName = intent.getStringExtra("SubjectName")

        var listOfSubjects = resources.getStringArray(
            resources.getIdentifier(
                rusNameToEng(dayName), "array",
                packageName
            )
        )
        var itemsList: Array<String> = arrayOf()

        var pref = getSharedPreferences("UserData", MODE_PRIVATE)

        for (subject in listOfSubjects) {
            var subjectWithoutSpaces = subject.replace(" ", "")
            var listOfItems = resources.getStringArray(
                resources.getIdentifier(
                    subjectWithoutSpaces, "array",
                    packageName
                )
            )

            for (item in listOfItems) {
                if (!itemsList.contains(item)) {
                    itemsList += item
                }
            }
            if (pref.contains(subjectWithoutSpaces)) {
                var listOfUserItems =
                    pref.getString(subjectWithoutSpaces, "defaultName")?.split(",")
                if (listOfUserItems != null) {
                    for (item in listOfUserItems) {
                        if (item.isNotEmpty()) {
                            if (!itemsList.contains(item)) {
                                itemsList += item
                            }
                        }
                    }
                }
            }


        }


        var lv: LinearLayout = findViewById(R.id.items_for_particular_day)

        for (item in itemsList) {
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

    private fun rusNameToEng(value: String?): String {
        val array = when (value) {
            "Понелельник" -> "monday"
            "Вторник" -> "tuesday"
            "Среда" -> "wednesday"
            "Четверг" -> "thursday"
            "Пятница" -> "friday"
            else -> "saturday"
        }
        return array
    }
}