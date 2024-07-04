package com.example.timenoter.android.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timenoter.android.data.model.TimeEntry
import com.example.timenoter.android.theme.TimeColors

@Preview
@Composable
fun TimeGridPreview() {
    val timeEntries = listOf(
        TimeEntry(1,"12/05", 50),
        TimeEntry(2, "13/06", 120),
        TimeEntry(3, "24/06", 35),
        TimeEntry(4, "24/06", -60)
    )
    TimeGrid(timeEntries)
}

@Composable
fun TimeGrid(timeEntries: List<TimeEntry>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .padding(6.dp)
            .fillMaxSize()
    ) {
        items(timeEntries.size) { index ->
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .background(TimeColors.Basics.backgroundShadow, shape = RoundedCornerShape(16.dp))
            ) {
                Row(
                    modifier = Modifier
                        .padding(14.dp)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = timeEntries[index].dayStamp,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TimeColors.Basics.text
                    )
                    Text(
                        text = timeEntries[index].accumulatedTime.toString() + " minutes",
                        fontSize = 18.sp,
                        color = TimeColors.Basics.text,
                        modifier = Modifier.padding(start = 18.dp)
                    )
                }
            }
        }
    }
}
