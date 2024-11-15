---
title: 滑动选择器
layout: home
parent: 扩展组件
usetocbot: true
---

<div style="text-align: center; margin: 20px 0;">
  <h1>滑动选择器</h1>
  <a href="https://jitpack.io/#Chen-Xi-g/VerveDialog">
      <img src="https://jitpack.io/v/Chen-Xi-g/VerveDialog.svg" alt="Jitpack Version">
  </a>
  <a href="https://developer.android.com/compose">
      <img src="https://img.shields.io/badge/Jetpack%20Compose%20-63C487?logo=jetpackcompose&logoColor=white" alt="Jetpack Compose Badge">
  </a>
  <a href="https://developer.android.com/kotlin?hl=zh-cn">
      <img src="https://img.shields.io/badge/Language-Kotlin-2376bc?labelColor=5384EC&color=7F32DA" alt="Kotlin Language Badge">
  </a>
  <a href="https://github.com/Chen-Xi-g/VerveDialog/blob/main/LICENSE">
      <img src="https://img.shields.io/badge/License-MIT-38519B?labelColor=272c3c" alt="License Badge">
  </a>
</div>
<img src="{{ site.baseurl }}/assets/images/Picker.png" alt="DefaultDialog" style="max-width: 40%; height: auto;">

{% tabs picker %}

{% tab picker 使用方式 %}

```kotlin
val list = listOf("风格1", "风格2", "风格3", "风格4", "风格5")
VerveDialog(...) {
    Picker(list = list){ selectedItem ->
        Log.d(TAG, "当前选择内容：$selectedItem")
    }
}
```

{% endtab %}

{% tab picker 代码%}

```kotlin
@Composable
fun DialogScope.Picker(
    list: List<String>, // 数据列表
    modifier: Modifier = Modifier, // 整体布局修饰符
    startIndex: Int = 0, // 初始选中项索引
    visibleItemsCount: Int = 3, // 可见项数量， 必须是奇数，否则分割线位置会有问题
    waitForPositiveButton: Boolean = false, // 是否等待确定按钮
    textModifier: Modifier = Modifier, // 文本修饰符
    dividerModifier: Modifier = Modifier.height(1.dp), // 分割线修饰符
    textStyle: TextStyle = LocalTextStyle.current, // 文本样式
    dividerColor: Color = LocalContentColor.current, // 分割线颜色
    isLooper: Boolean = true, // 是否循环滚动
    onItemSelected: (String) -> Unit = {} // 数据回调
) {
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
```

{% endtab %}

{% tab picker 参数详情 %}

| 参数名              | 类型                   | 默认值                   | 描述             |
| ------------------- | ---------------------- | ------------------------ | ---------------- |
| list                | List<String>           |                           | 数据列表         |
| modifier            | Modifier               | Modifier                 | 整体布局修饰符   |
| startIndex          | Int                    | 0                         | 初始选中项索引   |
| visibleItemsCount   | Int                    | 3                         | 可见项数量， 必须是奇数，否则分割线位置会有问题 |
| waitForPositiveButton | Boolean               | false                    | 是否等待确定按钮 |
| textModifier        | Modifier               | Modifier                 | 文本修饰符       |
| dividerModifier     | Modifier               | Modifier.height(1.dp)    | 分割线修饰符     |
| textStyle           | TextStyle              | LocalTextStyle.current   | 文本样式         |
| dividerColor        | Color                  | LocalContentColor.current| 分割线颜色       |
| isLooper            | Boolean                | true                     | 是否循环滚动     |
| onItemSelected      | (String) -> Unit       | {}                       | 选择数据回调     |

{% endtab %}

{% endtabs %}
