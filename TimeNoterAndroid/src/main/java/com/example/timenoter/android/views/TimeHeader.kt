package com.example.timenoter.android.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.timenoter.android.R
import com.example.timenoter.android.theme.TimeColors

@Composable
fun TimeHeader() {
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
            )
        }
    }
}