package com.example.timenoter.android.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.timenoter.android.components.SaveButton
import com.example.timenoter.android.components.ScrollableField
import com.example.timenoter.android.components.TimeGrid
import com.example.timenoter.android.components.TimerText
import com.example.timenoter.android.data.model.TimeEntry
import com.example.timenoter.android.data.model.TimeProcessor.getTotalAccumulatedTime
import com.example.timenoter.android.theme.TimeColors

@Preview
@Composable
fun TimeMenuPreview() {
    TimeMenu()
}

@Composable
fun TimeMenu() {
    val timeList = (-120..120 step 10).toList()
    val appTitle = "TimeNoter"
    val timeEntries = listOf(
        TimeEntry("12/05", "50"),
        TimeEntry("13/06", "120"),
        TimeEntry("24/06", "35"),
        TimeEntry("24/06", "-60")
    )
    val (roundedHours, remainingMinutes) = getTotalAccumulatedTime(timeEntries)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(10.dp),
        ) {
            TimerText(
                timeText = appTitle,
                fontSize = 10.em,
                textColor = TimeColors.ModernColors.Blue
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            ScrollableField(timeList = timeList)
            SaveButton()
        }
        Column(
            modifier = Modifier
                .weight(1.15f)
                .border(
                    BorderStroke(3.dp, TimeColors.ModernColors.Blue),
                    RoundedCornerShape(5.dp)
                )
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TimerText(
                modifier = Modifier.padding(6.dp),
                timeText = "TOTAL TIME",
                textColor = TimeColors.ModernColors.Blue,
                fontWeight = FontWeight.SemiBold
            )
            TimerText(
                modifier = Modifier.padding(3.dp),
                timeText = "$roundedHours hours and $remainingMinutes minutes",
                textColor = TimeColors.ModernColors.Blue,
                fontWeight = FontWeight.SemiBold
            )
            TimeGrid(timeEntries)
        }
    }
}