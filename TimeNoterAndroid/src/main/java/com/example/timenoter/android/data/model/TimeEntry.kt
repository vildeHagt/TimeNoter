package com.example.timenoter.android.data.model

data class TimeEntry(
    val id: Long,
    val dayStamp: String,
    var accumulatedHours: Int,
    var accumulatedMinutes: Int,
    var note: String
)