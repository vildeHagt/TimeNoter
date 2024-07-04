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

    private fun saveTimeEntry(context: Context, timeEntry: TimeEntry) {
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

        val oldTimeEntry = existingTimeEntry(timeEntry, context)
        if (oldTimeEntry != -1) {
            val difference = timeEntryList[oldTimeEntry].accumulatedTime + timeEntry.accumulatedTime
            if (timeEntry.accumulatedTime == 0 || difference == 0) timeEntryList.removeAt(oldTimeEntry)
            else timeEntryList[oldTimeEntry].accumulatedTime += timeEntry.accumulatedTime
        } else if (timeEntry.accumulatedTime != 0) timeEntryList.add(timeEntry)

        val updatedJson = gson.toJson(timeEntryList)
        editor.putString("time_entries", updatedJson)
        editor.apply()
    }

    private fun existingTimeEntry(timeEntry: TimeEntry, context: Context): Int {
        val timeEntries = getTimeEntries(context)
        return timeEntries.indexOfFirst { it.dayStamp == timeEntry.dayStamp }
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
