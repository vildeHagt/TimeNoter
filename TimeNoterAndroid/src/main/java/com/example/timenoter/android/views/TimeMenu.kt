package com.example.timenoter.android.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import com.example.timenoter.android.components.SaveButton
import com.example.timenoter.android.components.ScrollableButton
import com.example.timenoter.android.components.TimeGrid
import com.example.timenoter.android.components.TimerText
import com.example.timenoter.android.data.model.TimeEntry
import com.example.timenoter.android.data.model.TimeProcessor.getTotalAccumulatedTime

@Preview
@Composable
fun TimeMenuPreview() {
    TimeMenu()
}

@Composable
fun TimeMenu() {
    val timeList = (10..120 step 10).toList()
    val appTitle = "TimeNoter"
    val timeEntries = listOf(
        TimeEntry("12/05", "50"),
        TimeEntry("13/06", "120"),
        TimeEntry("24/06", "35"),
    )
    val (roundedHours, remainingMinutes) = getTotalAccumulatedTime(timeEntries)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column {
            TimerText(timeText = appTitle, fontSize = 10.em)
        }
        Column {
            ScrollableButton(timeList = timeList)
            SaveButton()
        }
        Column {
            TimerText("Total accumulated time: $roundedHours hours and $remainingMinutes minutes")
            TimeGrid(timeEntries)
        }
    }
}