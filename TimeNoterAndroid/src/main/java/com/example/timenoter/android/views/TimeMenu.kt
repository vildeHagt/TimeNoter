package com.example.timenoter.android.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.timenoter.android.R
import com.example.timenoter.android.components.Button
import com.example.timenoter.android.components.ScrollableField
import com.example.timenoter.android.components.NoterIcon
import com.example.timenoter.android.components.TimeGrid
import com.example.timenoter.android.components.TimerText
import com.example.timenoter.android.data.model.TimeProcessor.getTotalAccumulatedTime
import com.example.timenoter.android.theme.TimeColors
import com.example.timenoter.android.utils.TimeEntryUtils
import com.example.timenoter.android.utils.TimeEntryUtils.exportTimeEntriesToJson

@Preview
@Composable
fun TimeMenuPreview() {
    TimeMenu()
}

@Composable
fun TimeMenu() {
    val hoursList = (0..8 step 1).toList()
    val minutesList = (0..60 step 10).toList()
    val context = LocalContext.current
    val timeEntries = remember { mutableStateOf(TimeEntryUtils.getTimeEntries(context)) }
    val (roundedDays, roundedHours, remainingMinutes) = getTotalAccumulatedTime(timeEntries.value)
    var savedTimeHours by remember { mutableIntStateOf(0) }
    var savedTimeMinutes by remember { mutableIntStateOf(0) }
    var valueToggle by remember { mutableStateOf("+") }

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
            NoterIcon(painterResource(id = R.drawable.shareicon), {
                exportTimeEntriesToJson(
                    context,
                    timeEntries.value,
                    "TimeNoterFile"
                )
            })
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Row {
                Button(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp),
                    valueToggle,
                    if (valueToggle == "-") TimeColors.Basics.backgroundShadow else TimeColors.Basics.buttonBackground,
                    if (valueToggle == "-") TimeColors.Basics.text else TimeColors.Basics.buttonText
                ) {
                    valueToggle = if (valueToggle == "+") "-" else "+"
                }
                ScrollableField(
                    timeList = hoursList,
                    { savedTimeHours = it },
                    isMinutesField = false
                )
                TimerText(timeText = ":", fontWeight = FontWeight.ExtraBold)
                ScrollableField(
                    timeList = minutesList,
                    { savedTimeMinutes = it },
                    isMinutesField = true,
                    isNegative = savedTimeHours < 0
                )
                NoterIcon(painterResource(id = R.drawable.save), {
                    TimeEntryUtils.onButtonPress(
                        context,
                        if (valueToggle == "-") savedTimeHours * -1 else savedTimeHours,
                        if (valueToggle == "-") savedTimeMinutes * -1 else savedTimeMinutes
                    )
                    timeEntries.value = TimeEntryUtils.getTimeEntries(context)
                }, Arrangement.Center)
            }

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