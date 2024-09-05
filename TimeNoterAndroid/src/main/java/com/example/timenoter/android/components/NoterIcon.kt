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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.timenoter.android.R
import com.example.timenoter.android.theme.TimeColors

@Composable
fun NoterIcon(
    icon: Painter,
    onClick: () -> Unit,
    horizontal: Arrangement.Horizontal = Arrangement.End,
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = horizontal
    ) {
        Box {
            Icon(
                icon,
                contentDescription = "Share time",
                tint = TimeColors.Basics.icon,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        onClick()
                    }
            )
        }
    }
}