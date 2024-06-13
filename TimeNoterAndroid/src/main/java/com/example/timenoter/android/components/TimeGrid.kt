package com.example.timenoter.android.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
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
                    .padding(4.dp)
                    .border(
                        BorderStroke(1.dp, TimeColors.ModernColors.Blue),
                        RoundedCornerShape(20)
                    )
            ) {
                Row(
                    modifier = Modifier.padding(18.dp)
                        .fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = timeEntries[index].dayStamp,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TimeColors.DarkColors.DarkBlue
                    )

                    Text(
                        text = timeEntries[index].accumulatedTime + " minutter",
                        fontSize = 18.sp,
                        color = TimeColors.DarkColors.DarkBlue,
                        modifier = Modifier.padding(start = 18.dp)
                    )
                }
            }
        }
    }
}
