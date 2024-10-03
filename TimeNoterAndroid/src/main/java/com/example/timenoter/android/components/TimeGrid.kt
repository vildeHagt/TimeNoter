package com.example.timenoter.android.components

import android.app.Dialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.timenoter.android.R
import com.example.timenoter.android.data.model.TimeEntry
import com.example.timenoter.android.theme.TimeColors
import kotlin.math.max

@Preview
@Composable
fun TimeGridPreview() {
    val timeEntries = listOf(
        TimeEntry(1, "12/05", 1, 0, "Bugfiksing"),
        TimeEntry(2, "13/06", -1, -20, ""),
        TimeEntry(3, "24/06", 2, 10, ""),
        TimeEntry(4, "24/06", 0, -45, "Dyrlege")
    )
    TimeGrid(timeEntries, { })
}

@Composable
fun TimeGrid(timeEntries: List<TimeEntry>, saveNote: (TimeEntry) -> Unit) {
    var seeMore by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(timeEntries.size) { index ->
            val hours = timeEntries[index].accumulatedHours
            var minutes = timeEntries[index].accumulatedMinutes
            val timeString = buildString {
                if (hours != 0) {
                    append("$hours" + "h ")
                }
                if (minutes != 0) {
                    if (hours != 0 && minutes < 0) minutes *= -1
                    append("$minutes" + "min")
                }
            }.trim()

            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .background(
                        TimeColors.Basics.backgroundShadow, shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = timeEntries[index].dayStamp,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TimeColors.Basics.text
                    )

                    Text(
                        text = timeString,
                        fontSize = 16.sp,
                        color = TimeColors.Basics.text,
                        modifier = Modifier.padding(start = 18.dp)
                    )

                    if (!seeMore) {
                        NoterIcon(
                            modifier = Modifier.size(40.dp),
                            icon = painterResource(id = R.drawable.dots_icon),
                            onClick = { seeMore = true }
                        )
                    } else {
                        Box(
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            if (timeEntries[index].note.isEmpty()) {
                                NoterIcon(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .offset((-35).dp),
                                    icon = painterResource(id = R.drawable.add_note_icon),
                                    onClick = {
                                        showDialog = true
                                        seeMore = false
                                    })
                            } else {
                                NoterIcon(
                                    modifier = Modifier.size(40.dp),
                                    icon = painterResource(id = R.drawable.edit_icon),
                                    onClick = { seeMore = false })
                            }

                            NoterIcon(
                                modifier = Modifier.size(40.dp),
                                icon = painterResource(id = R.drawable.delete_icon),
                                onClick = { seeMore = false })
                        }
                    }
                }
            }

            if (showDialog) {
                NoteDialog(
                    {
                        val newTimeEntryNote = timeEntries[index]
                        newTimeEntryNote.note = it
                        saveNote(newTimeEntryNote)
                    },
                    { showDialog = false }
                )
            }
        }
    }
}
