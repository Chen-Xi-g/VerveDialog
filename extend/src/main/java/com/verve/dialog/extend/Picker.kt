package com.verve.dialog.extend

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.verve.dialog.DialogScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

/**
 * Dialog 滚动选择器
 *
 * @param list 数据列表
 * @param modifier 整体布局修饰符
 * @param startIndex 初始选中项索引
 * @param visibleItemsCount 可见项数量， 必须是奇数，否则分割线位置会有问题
 * @param waitForPositiveButton 是否等待确定按钮
 * @param textModifier 文本修饰符
 * @param dividerModifier 分割线修饰符
 * @param textStyle 文本样式
 * @param dividerColor 分割线颜色
 * @param isLooper 是否循环滚动
 * @param onItemSelected 选择数据回调
 */
@Composable
fun DialogScope.Picker(
    list: List<String>,
    modifier: Modifier = Modifier,
    startIndex: Int = 0,
    visibleItemsCount: Int = 3,
    waitForPositiveButton: Boolean = true,
    textModifier: Modifier = Modifier,
    dividerModifier: Modifier = Modifier.height(1.dp),
    textStyle: TextStyle = LocalTextStyle.current,
    dividerColor: Color = LocalContentColor.current,
    isLooper: Boolean = true,
    onItemSelected: (String) -> Unit = {}
) {

    if (list.isEmpty()) return

    val visibleItemsMiddle = visibleItemsCount / 2
    val paddedItems = if (isLooper) list else List(visibleItemsMiddle) { "" } + list + List(
        visibleItemsMiddle
    ) { "" }

    // 如果是循环模式，则无限滚动，否则限制为非循环数据的大小
    val listScrollCount = if (isLooper) Integer.MAX_VALUE else paddedItems.size
    val listScrollMiddle = if (isLooper) listScrollCount / 2 else startIndex + visibleItemsMiddle
    val listStartIndex =
        if (isLooper) {
            listScrollMiddle - listScrollMiddle % list.size - visibleItemsMiddle + startIndex
        } else {
            startIndex  // 非循环模式下，初始位置为第一个实际数据项
        }

    fun getItem(index: Int) = paddedItems[index % paddedItems.size]

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = listStartIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val itemHeightPixels = remember { mutableIntStateOf(0) }
    val itemHeightDp = pixelsToDp(itemHeightPixels.intValue)

    val fadingEdgeGradient = remember {
        Brush.verticalGradient(
            0f to Color.Transparent,
            0.5f to Color.Black,
            1f to Color.Transparent
        )
    }

    // 点击确定按钮后回调
    if (waitForPositiveButton) {
        Callback {
            onItemSelected(getItem(listState.firstVisibleItemIndex + visibleItemsMiddle))
        }
    } else {
        LaunchedEffect(listState) {
            snapshotFlow { listState.firstVisibleItemIndex }
                .map { index -> getItem(index + visibleItemsMiddle) }
                .filter { it.isNotEmpty() }  // 过滤掉空白项
                .distinctUntilChanged()
                .collect { item -> onItemSelected(item) }
        }
    }


    Box(modifier = modifier) {
        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeightDp * visibleItemsCount)
                .fadingEdge(fadingEdgeGradient)
        ) {
            items(count = listScrollCount) { index ->
                val item = getItem(index)
                Text(
                    text = item,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = textStyle,
                    modifier = Modifier
                        .onSizeChanged { size -> itemHeightPixels.value = size.height }
                        .then(textModifier)
                        .alpha(if (item.isEmpty()) 0f else 1f)  // 空白项不可见
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(top = itemHeightDp * visibleItemsMiddle)
                .then(dividerModifier),
            color = dividerColor
        )

        HorizontalDivider(
            modifier = Modifier
                .padding(top = (itemHeightDp * visibleItemsMiddle) + itemHeightDp)
                .then(dividerModifier),
            color = dividerColor
        )
    }
}

fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }

@Composable
private fun pixelsToDp(pixels: Int) = with(LocalDensity.current) { pixels.toDp() }
