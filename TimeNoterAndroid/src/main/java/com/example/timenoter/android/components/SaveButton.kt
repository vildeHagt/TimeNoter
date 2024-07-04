package com.example.timenoter.android.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.timenoter.android.theme.TimeColors
import com.example.timenoter.android.utils.TimeEntryUtils

@Preview
@Composable
fun SaveButtonPreview() {
    SaveButton(30) { }
}

@Composable
fun SaveButton(timeToNote: Int, refreshGrid: () -> Unit) {
    val localContext = LocalContext.current
    Button(
        modifier = Modifier
            .padding(15.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(containerColor = TimeColors.ModernColors.Blue),
        onClick = {
            TimeEntryUtils.onButtonPress(localContext, timeToNote)
            refreshGrid()
        }
    ) {
        TimerText(timeText = "SAVE", fontSize = 6.em, textColor = TimeColors.MellowColors.SoftBlue)
    }
}