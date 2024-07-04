package com.example.timenoter.android.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.timenoter.android.R
import com.example.timenoter.android.data.model.TimeEntry
import com.example.timenoter.android.theme.TimeColors
import com.example.timenoter.android.utils.TimeEntryUtils.exportTimeEntriesToJson

@Composable
fun ShareIcon(timeEntries: List<TimeEntry>) {
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Box {
            Icon(
                painterResource(id = R.drawable.shareicon),
                contentDescription = "Share time",
                tint = TimeColors.Basics.icon,
                modifier = Modifier.size(40.dp)
                    .clickable {
                        exportTimeEntriesToJson(context, timeEntries, "TimeNoterFile")
                }
            )
        }
    }
}