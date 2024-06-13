package com.example.timenoter.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timenoter.android.data.model.TimeEntry
import com.example.timenoter.android.theme.TimeColors

@Composable
fun TimeGrid(timeEntries: List<TimeEntry>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(timeEntries.size) { index ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .border(2.dp, TimeColors.MellowColors.SoftTeal)
                    .background(TimeColors.MellowColors.SoftTeal)
            ) {
                Text(
                    text = timeEntries[index].dayStamp,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = timeEntries[index].accumulatedTime + " minutter",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}
