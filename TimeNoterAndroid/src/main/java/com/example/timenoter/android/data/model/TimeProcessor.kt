package com.example.timenoter.android.data.model

object TimeProcessor {
    fun getTotalAccumulatedTime(timeEntries: List<TimeEntry>): SavedTime {
        var totalHours = 0
        var totalMinutes = 0
        var totalDays = 0

        timeEntries.forEach { timeEntry ->
            totalDays += timeEntry.accumulatedMinutes / 1440
            totalHours += timeEntry.accumulatedHours
            totalMinutes += timeEntry.accumulatedMinutes
        }

        return SavedTime(totalDays, totalHours, totalMinutes)
    }
}