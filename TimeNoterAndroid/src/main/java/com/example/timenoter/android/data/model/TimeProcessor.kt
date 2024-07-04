package com.example.timenoter.android.data.model

object TimeProcessor {
    fun getTotalAccumulatedTime(timeEntries: List<TimeEntry>): SavedTime {
        var totalHours = 0
        var totalMinutes = 0
        var totalDays = 0

        timeEntries.forEach { timeEntry ->
            totalDays += timeEntry.accumulatedTime / 1440
            totalHours += timeEntry.accumulatedTime / 60
            totalMinutes += timeEntry.accumulatedTime % 60
        }

        totalDays += totalHours / 8
        totalHours += totalHours / 60 - (totalDays*8)
        totalMinutes %= 60

        return SavedTime(totalDays, totalHours, totalMinutes)
    }
}