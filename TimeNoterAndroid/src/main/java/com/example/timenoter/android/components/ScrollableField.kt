package com.example.timenoter.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
fun ScrollableField(
    timeList: List<Int>,
    visibleTime: (targetTime: Int) -> Unit,
    isMinutesField: Boolean,
    isNegative: Boolean = false
) {
    val scrollState = rememberLazyListState()

    LaunchedEffect(timeList.size) {
        scrollState.scrollToItem(timeList.size / 2)
    }

    Column(
        modifier = Modifier
            .background(
                color = TimeColors.Basics.backgroundShadow,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .width(100.dp)
                .height(60.dp),
            state = scrollState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(timeList.size) { index ->
                TimerText(timeList[index].toString(), 6.em, Modifier.padding(16.dp))
            }
        }

        LaunchedEffect(scrollState.isScrollInProgress) {
            if (!scrollState.isScrollInProgress) {
                val firstVisibleItem = scrollState.firstVisibleItemIndex
                val firstVisibleItemOffset = scrollState.firstVisibleItemScrollOffset
                val targetIndex = if (!isMinutesField && isNegative) {
                    firstVisibleItem + (timeList.size)/2 //-6 + 12 = 6. -3 + 6 = 3. -1 + 2
                } else if (firstVisibleItemOffset > 0 && timeList.size < firstVisibleItem + 1) {
                    firstVisibleItem + 1
                } else {
                    firstVisibleItem
                }
                scrollState.animateScrollToItem(targetIndex)
                visibleTime(timeList[targetIndex])
            }
        }
    }
}