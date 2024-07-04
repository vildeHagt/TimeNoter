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
import com.example.timenoter.android.components.SaveButton
import com.example.timenoter.android.components.ScrollableField
import com.example.timenoter.android.components.ShareIcon
import com.example.timenoter.android.components.TimeGrid
import com.example.timenoter.android.components.TimerText
import com.example.timenoter.android.data.model.TimeProcessor.getTotalAccumulatedTime
import com.example.timenoter.android.utils.TimeEntryUtils

@Preview
@Composable
fun TimeMenuPreview() {
    TimeMenu()
}

@Composable
fun TimeMenu() {
    val timeList = (-120..120 step 10).toList()
    val context = LocalContext.current
    val timeEntries = remember { mutableStateOf(TimeEntryUtils.getTimeEntries(context)) }
    val (roundedHours, remainingMinutes) = getTotalAccumulatedTime(timeEntries.value)
    var savedTime by remember { mutableIntStateOf(0) }
    val savedTimeText = if ((roundedHours + remainingMinutes) < 0) {
        "You have $roundedHours hours and $remainingMinutes minutes available"
    } else "You are $roundedHours hours and $remainingMinutes minutes behind"

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
            ScrollableField(timeList = timeList) { savedTime = it}
            SaveButton(savedTime) { timeEntries.value = TimeEntryUtils.getTimeEntries(context) }
        }
        Column(
            modifier = Modifier
                .weight(1.15f)
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimerText(
                modifier = Modifier.padding(3.dp),
                timeText = if (roundedHours != 0 && remainingMinutes != 0) savedTimeText else "",
                fontWeight = FontWeight.SemiBold
            )
            TimeGrid(timeEntries.value)
        }
    }
}