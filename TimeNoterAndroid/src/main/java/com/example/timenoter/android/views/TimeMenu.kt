package com.example.timenoter.android.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.timenoter.android.R
import com.example.timenoter.android.components.SaveButton
import com.example.timenoter.android.components.ScrollableField
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
    val timeList = (-120..120 step 10).toList()
    val context = LocalContext.current
    val timeEntries = remember { mutableStateOf(TimeEntryUtils.getTimeEntries(context)) }
    val (roundedHours, remainingMinutes) = getTotalAccumulatedTime(timeEntries.value)
    val savedTime = remember { mutableIntStateOf(0) }

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
                timeText = stringResource(id = R.string.app_name),
                fontSize = 10.em,
                textColor = TimeColors.ModernColors.Blue
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            ScrollableField(timeList = timeList) { savedTime.intValue = it}
            SaveButton(savedTime.intValue) { timeEntries.value = TimeEntryUtils.getTimeEntries(context) }
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
            TimeGrid(timeEntries.value)
        }
    }
}