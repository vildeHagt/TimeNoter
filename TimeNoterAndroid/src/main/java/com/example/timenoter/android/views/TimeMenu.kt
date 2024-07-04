package com.example.timenoter.android.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.timenoter.android.components.SaveButton
import com.example.timenoter.android.components.ScrollableField
import com.example.timenoter.android.components.ShareIcon
import com.example.timenoter.android.components.TimeGrid
import com.example.timenoter.android.components.TimerText
import com.example.timenoter.android.data.model.TimeProcessor.getTotalAccumulatedTime
import com.example.timenoter.android.theme.TimeColors
import com.example.timenoter.android.utils.TimeEntryUtils

@Preview
@Composable
fun TimeMenuPreview() {
    TimeMenu()
}

@Composable
fun TimeMenu() {
    val hoursList = (-8..8 step 1).toList()
    val minutesList = (-60..60 step 10).toList()
    val context = LocalContext.current
    val timeEntries = remember { mutableStateOf(TimeEntryUtils.getTimeEntries(context)) }
    val (roundedDays, roundedHours, remainingMinutes) = getTotalAccumulatedTime(timeEntries.value)
    var savedTimeHours by remember { mutableIntStateOf(0) }
    var savedTimeMinutes by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            ShareIcon(timeEntries.value)
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Row {
                ScrollableField(timeList = hoursList) { savedTimeHours = it }
                TimerText(timeText = ":", fontWeight = FontWeight.ExtraBold)
                ScrollableField(timeList = minutesList) { savedTimeMinutes = it }
            }

            SaveButton(savedTimeHours, savedTimeMinutes) { timeEntries.value = TimeEntryUtils.getTimeEntries(context) }
        }
        Column(
            modifier = Modifier
                .weight(1.15f)
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimerText(
                modifier = Modifier.padding(3.dp),
                timeText = remainingTimeText(roundedDays, roundedHours, remainingMinutes),
                fontWeight = FontWeight.SemiBold,
                fontSize = 4.em,
            )
            TimeGrid(timeEntries.value)
        }
    }
}

private fun remainingTimeText(days: Int, hours: Int, minutes: Int): String {
    val totalTime = days + hours + minutes
    if (totalTime == 0) return ""
    val daysText = if (days != 0) " $days workdays" else ""
    val hoursText = if (hours != 0) " $hours hours" else ""
    val minutesText = if (minutes != 0) " $minutes minutes" else ""

    return if (totalTime > 0) "You have$daysText$hoursText$minutesText available" else "You are$daysText$hoursText$minutesText behind"
}