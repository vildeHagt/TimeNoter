package com.example.timenoter.android.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.timenoter.android.theme.TimeColors

@Preview
@Composable
fun SaveButtonPreview() {
    Button(Modifier, "SAVE") { }
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    title: String,
    buttonColor: Color = TimeColors.Basics.buttonBackground,
    buttonTextColor: Color = TimeColors.Basics.buttonText,
    onButtonPress: () -> Unit
) {
    Button(
        modifier = modifier
            .size(60.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(buttonColor),
        onClick = {
            onButtonPress()
        }
    ) {
        TimerText(
            modifier = Modifier.padding(0.dp),
            timeText = title,
            fontSize = 7.em,
            textColor = buttonTextColor,
            fontWeight = FontWeight.Bold
        )
    }
}