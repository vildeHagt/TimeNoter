package com.example.timenoter.android.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.timenoter.android.theme.TimeColors

@Preview
@Composable
fun SaveButtonPreview() {
    NoterButton(Modifier, "SAVE") { }
}

@Composable
fun NoterButton(
    modifier: Modifier = Modifier,
    title: String,
    buttonColor: Color = TimeColors.Basics.buttonBackground,
    buttonTextColor: Color = TimeColors.Basics.buttonText,
    fontSize: TextUnit = 7.em,
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
        NoterText(
            modifier = Modifier.padding(0.dp),
            timeText = title,
            fontSize = fontSize,
            textColor = buttonTextColor,
            fontWeight = FontWeight.Bold
        )
    }
}