package com.example.timenoter.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.timenoter.android.theme.TimeColors
import com.example.timenoter.android.utils.TimeEntryUtils

@Preview
@Composable
fun SaveButtonPreview() {
    SaveButton(30, 20) { }
}

@Composable
fun SaveButton(hoursToNote: Int, minutesToNote: Int, refreshGrid: () -> Unit) {
    val localContext = LocalContext.current

    Button(
        modifier = Modifier
            .padding(15.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = TimeColors.Basics.buttonBackground),
        onClick = {
            TimeEntryUtils.onButtonPress(localContext, hoursToNote, minutesToNote)
            refreshGrid()
        }
    ) {
        TimerText(modifier = Modifier.padding(0.dp), timeText = "SAVE", fontSize = 5.em, textColor = TimeColors.Basics.buttonText, fontWeight = FontWeight.Bold)
    }
}