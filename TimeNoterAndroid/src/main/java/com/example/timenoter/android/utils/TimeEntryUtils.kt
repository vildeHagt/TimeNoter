package com.example.timenoter.android.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.timenoter.android.data.model.TimeEntry
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileOutputStream
import java.io.OutputStreamWriter
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

        val existingTimeEntryIndex = existingTimeEntry(timeEntry, context)
        val newTimeEntryList = editTimeEntryList(timeEntry, existingTimeEntryIndex, timeEntryList)

        val updatedJson = gson.toJson(newTimeEntryList)
        editor.putString("time_entries", updatedJson)
        editor.apply()
    }

    private fun editTimeEntryList(newTimeEntry: TimeEntry, existingTimeEntryIndex: Int, timeEntryList: MutableList<TimeEntry>): MutableList<TimeEntry> {
        if (existingTimeEntryIndex != -1) {
            if (newTimeEntry.accumulatedHours == 0 && newTimeEntry.accumulatedMinutes == 0) {
                timeEntryList.removeAt(existingTimeEntryIndex)
            } else {
                timeEntryList[existingTimeEntryIndex].accumulatedHours = newTimeEntry.accumulatedHours
                timeEntryList[existingTimeEntryIndex].accumulatedMinutes = newTimeEntry.accumulatedMinutes
            }
        } else {
            timeEntryList.add(newTimeEntry)
        }
        return timeEntryList
    }

    private fun existingTimeEntry(timeEntry: TimeEntry, context: Context): Int {
        val timeEntries = getTimeEntries(context)
        return timeEntries.indexOfFirst { it.dayStamp == timeEntry.dayStamp }
    }

    fun onButtonPress(context: Context, hours: Int, minutes: Int) {
        val calendar = Calendar.getInstance()
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val newTimeEntry = TimeEntry(
            id = UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE,
            dayStamp = "$day/$month",
            accumulatedHours = hours,
            accumulatedMinutes = minutes,
            note = "")
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

    fun exportTimeEntriesToJson(context: Context, timeEntries: List<TimeEntry>, fileName: String) {
        val gson = Gson()
        val json = gson.toJson(timeEntries)

        try {
            val fileOutputStream: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            val outputStreamWriter = OutputStreamWriter(fileOutputStream)
            outputStreamWriter.write(json)
            outputStreamWriter.close()
            fileOutputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
