package com.example.timenoter.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.timenoter.android.theme.TimeColors
import java.sql.Time

@Preview
@Composable
fun PreviewNoteDialog() {
    NoteDialog(saveNote = { }) { }
}

@Composable
fun NoteDialog(saveNote: (String) -> Unit, closeDialog: () -> Unit) {
    var text by remember { mutableStateOf("Write a note...") }

    Dialog(onDismissRequest = { closeDialog() }) {
        Card(
            colors = CardColors(
                containerColor = TimeColors.Basics.backgroundShadow,
                contentColor = TimeColors.Basics.text,
                CardDefaults.cardColors().disabledContainerColor,
                CardDefaults.cardColors().disabledContentColor
            )
        ) {
            Column(
                Modifier.fillMaxWidth()
            ) {

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    textStyle = TextStyle(color = TimeColors.Basics.text)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(
                        onClick = { closeDialog() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Dismiss")
                    }

                    TextButton(
                        modifier = Modifier.padding(8.dp),
                        onClick = { closeDialog() }
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}
