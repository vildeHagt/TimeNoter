package com.example.timenoter.android.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun TimerText(
    timeText: String,
    fontSize: TextUnit = 6.em,
    modifier: Modifier = Modifier.padding(10.dp),
    textColor: Color = Color.Black
) {
    Text(
        modifier = modifier,
        text = timeText,
        textAlign = TextAlign.Center,
        fontSize = fontSize,
        overflow = TextOverflow.Visible,
        color = textColor
    )
}