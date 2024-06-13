package com.yourpackage.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.timenoter.android.data.model.TimeEntry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

object TimeEntryUtils {

    fun saveTimeEntry(context: Context, timeEntry: TimeEntry) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val gson = Gson()
        val json = sharedPreferences.getString("time_entries", null)
        val type = object : TypeToken<MutableList<TimeEntry>>() {}.type
        val timeEntryList: MutableList<TimeEntry> = if (json == null) {
            mutableListOf()
        } else {
            gson.fromJson(json, type)
        }

        timeEntryList.add(timeEntry)

        val updatedJson = gson.toJson(timeEntryList)
        editor.putString("time_entries", updatedJson)
        editor.apply()
    }

    fun onButtonPress(context: Context, timeToNote: Int) {
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val newTimeEntry = TimeEntry(id = UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE, dayStamp = "$day/$month", timeToNote)
        saveTimeEntry(context, newTimeEntry)
    }

    fun getTimeEntries(context: Context): List<TimeEntry> {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("time_entries", null)
        val type = object : TypeToken<MutableList<TimeEntry>>() {}.type
        return if (json == null) {
            mutableListOf()
        } else {
            gson.fromJson(json, type)
        }
    }
}
