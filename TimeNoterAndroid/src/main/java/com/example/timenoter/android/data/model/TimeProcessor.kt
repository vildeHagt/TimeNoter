package com.example.timenoter.android.data.model

object TimeProcessor {
    fun getTotalAccumulatedTime(timeEntries: List<TimeEntry>): Pair<Int, Int> {
        var totalHours = 0
        var totalMinutes = 0

        timeEntries.forEach { timeEntry ->
            totalHours += timeEntry.accumulatedTime.toInt() / 60
            totalMinutes += timeEntry.accumulatedTime.toInt() % 60
        }

        totalHours += totalMinutes / 60
        totalMinutes %= 60

        return totalHours to totalMinutes
    }
}