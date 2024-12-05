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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.timenoter.android.theme.TimeColors

@Composable
fun ScrollableField(
    timeList: List<Int>,
    visibleTime: (targetTime: Int) -> Unit,
) {
    val scrollState = rememberLazyListState()
    val density = LocalDensity.current
    var itemHeightPx by remember { mutableStateOf(0) }

    LaunchedEffect(timeList.size) {
        scrollState.scrollToItem(0)
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
                .width(90.dp)
                .height(60.dp)
                .onGloballyPositioned { coordinates ->
                    itemHeightPx = coordinates.size.height
                },
            state = scrollState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(timeList.size) { index ->
                NoterText(timeList[index].toString(), 6.em, Modifier.padding(16.dp))
            }
        }

        LaunchedEffect(scrollState.isScrollInProgress) {
            if (!scrollState.isScrollInProgress) {
                val firstVisibleItem = scrollState.firstVisibleItemIndex
                val firstVisibleItemOffset = scrollState.firstVisibleItemScrollOffset
                val halfwayPoint = itemHeightPx / 2

                val targetIndex = if (firstVisibleItemOffset > halfwayPoint) {
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