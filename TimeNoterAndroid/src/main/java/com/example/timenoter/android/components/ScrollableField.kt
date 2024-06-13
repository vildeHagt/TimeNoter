package com.example.timenoter.android.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.timenoter.android.theme.TimeColors

@Composable
fun ScrollableField(timeList: List<Int>) {
    val scrollState = rememberLazyListState()

    LaunchedEffect(timeList.size) {
        scrollState.scrollToItem(timeList.size/2)
    }

    LazyColumn(
        modifier = Modifier
            .border(3.dp, TimeColors.ModernColors.Blue, shape = RoundedCornerShape(20.dp))
            .width(200.dp)
            .height(60.dp),
        state = scrollState,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(timeList.size) { index ->
            TimerText(timeList[index].toString() + " minutes", 6.em, Modifier.padding(16.dp))
        }
    }

    LaunchedEffect(scrollState.isScrollInProgress) {
        if (!scrollState.isScrollInProgress) {
            val firstVisibleItem = scrollState.firstVisibleItemIndex
            val firstVisibleItemOffset = scrollState.firstVisibleItemScrollOffset
            val targetIndex =
                if (firstVisibleItemOffset > 0) firstVisibleItem + 1 else firstVisibleItem
            scrollState.animateScrollToItem(targetIndex)
        }
    }
}