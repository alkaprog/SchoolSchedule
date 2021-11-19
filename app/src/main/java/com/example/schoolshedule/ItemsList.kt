package com.example.schoolshedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

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
                    if (item.isNotEmpty()) {
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
        }


        var textField = EditText(this)

        lv.addView(textField)


        var addItemButton = Button(this)
        addItemButton.text = "Добавить предмет"
        addItemButton.setOnClickListener {
            if (textField.text.toString() != "") {
                if (subjectName != null) {
                    addAnItem(subjectName, textField.text.toString())
                }
            }
        }
        lv.addView(addItemButton)

        var deleteItemButton = Button(this)
        deleteItemButton.text = "Удалить предмет"
        deleteItemButton.setOnClickListener {
            if (textField.text.toString() != "") {
                if (subjectName != null) {
                    deleteAnItem(subjectName, textField.text.toString())
                }
            }
        }
        lv.addView(deleteItemButton)

        var deleteAllItemsButton = Button(this)
        deleteAllItemsButton.text = "Удалить все предметы добавленные пользователем"
        deleteAllItemsButton.setOnClickListener {
            if (subjectName != null) {
                deleteAllUserItems(subjectName)
            }
        }
        lv.addView(deleteAllItemsButton)
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
                            row += "$itemName"
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

    private fun deleteAnItem(subjectName: String, itemName: String) {
        var pref = getSharedPreferences("UserData", MODE_PRIVATE)
        var editor = pref.edit()

        if (pref.contains(subjectName)) {
            var listOfUserItems = pref.getString(subjectName, "defaultName")?.split(",")

            var row: String = ""

            if (listOfUserItems != null) {
                if (listOfUserItems.contains(itemName)) {
                    for (item in listOfUserItems) {
                        if (item != itemName) {
                            if (row.isNotEmpty()) {
                                row += ",$item"
                            } else {
                                row += "$item"
                            }
                        }
                        /*
                    if (row.length > 1) {
                        if (row[row.length - 1] == ',') {
                            row = row.substring(0, row.length - 1)
                        }
                    }

                    if (row.length >= 0) {
                        if (row[0] == ',') {
                            row = row.substring(1)
                        }
                    }
*/
                        editor.putString(subjectName, row)
                        editor.commit()
                    }
                }

            }
            recreate()
        }

    }

    private fun deleteAllUserItems(subjectName: String) {
        var pref = getSharedPreferences("UserData", MODE_PRIVATE)
        var editor = pref.edit()
        editor.remove(subjectName).commit()
        recreate()
    }
}