---
title: 内置组件
layout: home
usetocbot: true
---

<div style="text-align: center; margin: 20px 0;">
  <h1>内置组件</h1>
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

内置组件用于在`VerveDialog`或`VerveBottomDialog`中添加基本功能，代码逻辑可参考注释。

## Title

Dialog中的标题组件

{: .warning }

参数中的`text`和`res`二者必须传递一个，否则显示为空字符串。

{% tabs title %}

{% tab title 使用方式 %}

```kotlin
VerveDialog(...) {
    Title(text = "提示")
}
```

{% endtab %}

{% tab title 代码%}

```kotlin
@Composable
fun DialogScope.Title(
    text: String? = null, // 文本内容（字符串）
    @StringRes res: Int? = null, // 文本内容（字符串资源ID）
    color: Color = DialogGlobalConfig.dialogTitleConfig.color, // 文本颜色
    style: TextStyle = DialogGlobalConfig.dialogTitleConfig.style, // 文本样式
    padding: PaddingValues = DialogGlobalConfig.dialogTitleConfig.padding, // 标题间距
    alignment: Alignment.Horizontal = DialogGlobalConfig.dialogTitleConfig.alignment // 文本对齐方式
) {
    Text(
        text = getString(res, text),
        color = color,
        style = style,
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
            .wrapContentHeight(Alignment.CenterVertically)
            .then(
                Modifier.wrapContentWidth(alignment)
            )
    )
}
```

{% endtab %}

{% tab title 参数详情 %}

| 参数名    | 类型                 | 默认值                                         | 描述                     |
| --------- | -------------------- | ---------------------------------------------- | ------------------------ |
| text      | String?              | null                                           | 文本内容（字符串）       |
| res       | Int?                 | null                                           | 文本内容（字符串资源ID） |
| color     | Color                | DialogGlobalConfig.dialogTitleConfig.color     | 文本颜色                 |
| style     | TextStyle            | DialogGlobalConfig.dialogTitleConfig.style     | 文本样式                 |
| padding   | PaddingValues        | DialogGlobalConfig.dialogTitleConfig.padding   | 标题间距                 |
| alignment | Alignment.Horizontal | DialogGlobalConfig.dialogTitleConfig.alignment | 文本对齐方式             |

{% endtab %}

{% endtabs %}



## IconTitle

Dialog中携带Icon的标题组件

{: .warning }

参数中的`text`和`res`二者必须传递一个，否则显示为空字符串。

{% tabs icon %}

{% tab icon 使用方式 %}

```kotlin
VerveDialog(...) {
    IconTitle(text = "提示") {
        Image(
            painter = painterResource(id = R.drawable.ic_dialog_icon),
            modifier = Modifier.size(38.dp),
            contentDescription = "Icon"
        )
    }
}
```

{% endtab %}

{% tab icon 代码%}

```kotlin
@Composable
fun DialogScope.IconTitle(
    text: String? = null, // 文本（字符串）
    @StringRes res: Int? = null, // 文本（字符串资源）
    color: Color = DialogGlobalConfig.dialogTitleConfig.color, // 文本颜色
    style: TextStyle = DialogGlobalConfig.dialogTitleConfig.style, // 文本样式
    padding: PaddingValues = DialogGlobalConfig.dialogTitleConfig.padding, // 标题间距
    arrangement: Arrangement.Horizontal = DialogGlobalConfig.dialogTitleConfig.iconArrangement, // 对齐方式
    icon: @Composable () -> Unit = {} // 标题前的图标
) {
    val titleText = getString(res, text)
    Row(
        modifier = Modifier
            .padding(padding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = arrangement
    ) {
        icon()
        Spacer(Modifier.width(15.dp))
        Text(
            text = titleText,
            color = color,
            style = style
        )
    }
}
```

{% endtab %}

{% tab icon 参数详情 %}

| 参数名        | 类型            | 默认值                                     | 描述                 |
|---------------|-----------------|--------------------------------------------|----------------------|
| text          | String?         | null                                       | 文本（字符串） |
| res           | Int?            | null                                       | 文本（字符串资源）   |
| color         | Color           | DialogGlobalConfig.dialogTitleConfig.color | 文本颜色               |
| style         | TextStyle       | DialogGlobalConfig.dialogTitleConfig.style | 文本样式               |
| padding       | PaddingValues   | DialogGlobalConfig.dialogTitleConfig.padding | 标题间距            |
| arrangement   | Arrangement.Horizontal | DialogGlobalConfig.dialogTitleConfig.iconArrangement | 对齐方式 |
| icon          | @Composable () -> Unit | 空函数                                    | 标题前的图标            |

{% endtab %}

{% endtabs %}



## Message

Dialog中携带Icon的标题组件

{: .warning }

参数中的`text`和`res`二者必须传递一个，否则显示为空字符串。

{% tabs message %}

{% tab message 使用方式 %}

```kotlin
VerveDialog(...) {
    Message(text = "这是一个普通Dialog弹窗")
}
```

{% endtab %}

{% tab message 代码%}

```kotlin
/**
 * Dialog 消息
 */
@Composable
fun DialogScope.Message(
    text: String? = null, // 文本（字符串）
    @StringRes res: Int? = null, // 文本（字符串资源）
    color: Color = DialogGlobalConfig.dialogMessageConfig.color, // 文本颜色
    style: TextStyle = DialogGlobalConfig.dialogMessageConfig.style, // 文本样式
    padding: PaddingValues = DialogGlobalConfig.dialogMessageConfig.padding, // 消息间距
    alignment: Alignment.Horizontal = DialogGlobalConfig.dialogMessageConfig.alignment // 对齐方式
) {
    Text(
        text = getString(res, text),
        color = color,
        style = style,
        modifier = Modifier
            .padding(padding)
            .then(
                Modifier.wrapContentWidth(alignment)
            )
    )
}
```

{% endtab %}

{% tab message 参数详情 %}

| 参数名      | 类型                      | 默认值                                       | 描述                 |
|-------------|---------------------------|----------------------------------------------|----------------------|
| text        | String?                   | null                                         | 文本（来自字符串字面量） |
| res         | Int?                      | null                                         | 文本（来自字符串资源）   |
| color       | Color                     | DialogGlobalConfig.dialogMessageConfig.color | 文本颜色               |
| style       | TextStyle                 | DialogGlobalConfig.dialogMessageConfig.style | 文本样式               |
| padding     | PaddingValues             | DialogGlobalConfig.dialogMessageConfig.padding | 消息间距            |
| alignment   | Alignment.Horizontal      | DialogGlobalConfig.dialogMessageConfig.alignment | 对齐方式          |
{% endtab %}

{% endtabs %}



## Input

Dialog中添加输入框

{% tabs input %}

{% tab input 使用方式 %}

```kotlin
VerveDialog(...) {
    Input(placeholder = "请输入内容") { text ->
        Log.d(TAG, "输入内容回调：$text")
    }
}
```

{% endtab %}

{% tab input 代码%}

```kotlin
/**
 * Dialog 输入框
 */
@Composable
fun DialogScope.Input(
    placeholder: String = "", // 占位符
    prefill: String = "", // 预填充文本
    border: BorderStroke = DialogGlobalConfig.dialogInputConfig.border, // 边框
    padding: PaddingValues = DialogGlobalConfig.dialogInputConfig.padding, // 与Dialog的间距
    contentPadding: PaddingValues = DialogGlobalConfig.dialogInputConfig.contentPadding, // 输入框内部间距
    waitForPositiveButton: Boolean = true, // 等待点击确定按钮后返回输入文本
    focusRequester: FocusRequester = FocusRequester(), // 输入框焦点请求
    focusOnShow: Boolean = DialogGlobalConfig.dialogInputConfig.focusOnShow, // 是否在显示时显示软键盘
    alignment: Alignment = DialogGlobalConfig.dialogInputConfig.alignment, // 对齐方式
    isTextValid: (String) -> Boolean = { true }, // 输入文本校验，校验未通过时禁用确定按钮
    onInput: (String) -> Unit = {} // 输入回调
) {
    // 当前输入文本
    var text by remember { mutableStateOf(prefill) }
    // 校验文本是否符合规则
    val valid = remember(text) { isTextValid(text) }
    // 如果是BottomSheet需要创建协程处理动画
    val coroutineScope = if (state.isBottomSheet) {
        rememberCoroutineScope()
    } else {
        null
    }
    // 设置确定按钮是否可用
    PositiveButtonState(isValid = valid)
    // 输入内容回调
    if (waitForPositiveButton) {
        Callback { onInput(text) }
    }

    BasicTextField(
        value = text,
        onValueChange = { newText ->
            // 更新文本
            text = newText
            if (!waitForPositiveButton) {
                onInput(newText)
            }
        },
        textStyle = textStyle, // 文本样式
        modifier = Modifier
            .focusRequester(focusRequester) // 输入框焦点
            .fillMaxWidth()
            .padding(padding) // 与Dialog的间距
            .border(border = border, shape = shape) // 边框
            .padding(contentPadding), // 输入框内部间距
        decorationBox = { innerTextField ->
            // 占位符
            Box(contentAlignment = alignment) {
                // 如果没有输入内容，显示占位符
                if (text.isEmpty()) {
                    Text(
                        text = placeholder, // 占位符文本
                        style = placeholderStyle
                    )
                }
                // 这是实际的输入框
                innerTextField()
            }
        }
    )

    // 如果需要在显示时显示软键盘
    if (focusOnShow) {
        DisposableEffect(Unit) {
            // 如果是BottomSheet，需要延迟显示软键盘
            if (state.isBottomSheet) {
                coroutineScope?.launch {
                    delay(AnimationConstants.DefaultDurationMillis.toLong())
                    focusRequester.requestFocus()
                }
            } else {
                focusRequester.requestFocus()
            }
            onDispose { }
        }
    }
}
```

{% endtab %}

{% tab input 参数详情 %}

| 参数名                  | 类型                    | 默认值                                    | 描述                                                   |
|---------------------|-----------------------|---------------------------------------|------------------------------------------------------|
| placeholder          | String                | 空字符串                                    | 占位符                                                 |
| prefill             | String                | 空字符串                                    | 预填充文本                                             |
| textStyle           | TextStyle             | DialogGlobalConfig.dialogInputConfig.textStyle | 输入文本样式                                          |
| placeholderStyle     | TextStyle             | DialogGlobalConfig.dialogInputConfig.placeholderStyle | 占位符样式                                          |
| border               | BorderStroke          | DialogGlobalConfig.dialogInputConfig.border | 边框                                                  |
| shape                | RoundedCornerShape     | DialogGlobalConfig.dialogInputConfig.shape | 输入框形状                                            |
| padding              | PaddingValues         | DialogGlobalConfig.dialogInputConfig.padding | 与Dialog的间距                                       |
| contentPadding       | PaddingValues         | DialogGlobalConfig.dialogInputConfig.contentPadding | 输入框内部间距                                       |
| waitForPositiveButton | Boolean               | true                                   | 等待点击确定按钮后返回输入文本                      |
| focusRequester       | FocusRequester        | FocusRequester()                     | 输入框焦点请求                                       |
| focusOnShow         | Boolean               | DialogGlobalConfig.dialogInputConfig.focusOnShow | 是否在显示时显示软键盘                               |
| alignment           | Alignment             | DialogGlobalConfig.dialogInputConfig.alignment | 对齐方式                                            |
| isTextValid         | (String) -> Boolean   | { true }                             | 输入文本校验，校验未通过时禁用确定按钮             |
| onInput             | (String) -> Unit      | {}                                   | 输入回调                                             |


{% endtab %}

{% endtabs %}



## 单选列表

Dialog中创建单选列表。

{: .important }

单选列表有两个快速使用的组件`SingleChoiceString`和`SingleChoice`，分别是`字符串集合`和`泛型集合`的单选列表。

### String集合单选

{: .important }

> 字符串实际上调用的是`SingleChoice`，只是内部添加了默认的`RadioButton`和`Text`。

{% tabs singleS %}

{% tab singleS 使用方式 %}

```kotlin
VerveDialog(...) {
    SingleChoiceString(list = list) { index, item ->
        Log.d(TAG, "选择索引：$index, 选择内容：$item")
    }
}
```

{% endtab %}

{% tab singleS 代码%}

```kotlin
/**
 * Dialog 字符串类型单选
 */
@Composable
fun DialogScope.SingleChoiceString(
    list: List<String>, // 集合数据
    state: LazyListState = rememberLazyListState(), // 列表状态
    radioButtonColors: RadioButtonColors = RadioButtonDefaults.colors(), // 单选按钮颜色
    choiceTextStyle: TextStyle = MaterialTheme.typography.bodyMedium, // 单选文本样式
    isEnabled: (index: Int, item: String) -> Boolean = { _, _ -> true }, // 指定Item是否可点击
    initialSelection: Int = -1, // 初始化选择的Item
    waitForPositiveButton: Boolean = true, // 是否点击确定按钮后返回选择结果
    onChoiceChange: (index: Int, item: String) -> Unit // 选择回调
) {
    SingleChoice(
        list = list,
        state = state,
        isEnabled = isEnabled,
        initialSelection = initialSelection,
        waitForPositiveButton = waitForPositiveButton,
        radioButton = { _, _, selected, enabled, onClick ->
           // 默认按钮
            RadioButton(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                colors = radioButtonColors
            )
        },
        choiceText = { _, item, enabled ->
          // 默认文本
            Text(
                item,
                color = if (enabled) {
                    choiceTextStyle.color
                } else {
                    choiceTextStyle.color.copy(0.5f)
                },
                style = choiceTextStyle
            )
        },
        onChoiceChange = onChoiceChange
    )
}
```

{% endtab %}

{% tab singleS 参数详情 %}

| 参数名                | 类型                                  | 默认值                              | 描述                           |
| --------------------- | ------------------------------------- | ----------------------------------- | ------------------------------ |
| list                  | List<String>                          | -                                   | 集合数据                       |
| state                 | LazyListState                         | rememberLazyListState()             | 列表状态                       |
| radioButtonColors     | RadioButtonColors                     | RadioButtonDefaults.colors()        | 单选按钮颜色                   |
| choiceTextStyle       | TextStyle                             | MaterialTheme.typography.bodyMedium | 单选文本样式                   |
| isEnabled             | (index: Int, item: String) -> Boolean | { _, _ -> true }                    | 指定Item是否可点击             |
| initialSelection      | Int                                   | -1                                  | 初始化选择的Item               |
| waitForPositiveButton | Boolean                               | true                                | 是否点击确定按钮后返回选择结果 |
| onChoiceChange        | (index: Int, item: String) -> Unit    | -                                   | 选择回调                       |

{% endtab %}

{% endtabs %}



### 泛型集合单选

{: .warning }

> 传入自己的数据集合，选择结果回调会返回所传入的泛型类型。
>
> 泛型集合单选需要你自己实现单选按钮（radioButton）和单选文本（choiceText）。

{% tabs singleT %}

{% tab singleT 使用方式 %}

```kotlin
VerveDialog(...) {
    SingleChoice(
        list = List<T>,
        radioButton = { index, item, selected, enabled, onClick ->
            RadioButton(
                selected = selected,
                onClick = onClick,
                enabled = enabled
            )
        },
        choiceText = { index, item, enabled ->
            Text(
                item,
                color = if (enabled) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(0.5f)
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    ) { index, item ->
        Log.d(TAG, "选择索引：$index, 选择内容：$item")
    }
}
```

{% endtab %}

{% tab singleT 代码%}

```kotlin
/**
 * Dialog 泛型类型单选
 */
@Composable
fun <T> DialogScope.SingleChoice(
    list: List<T>, // 集合数据
    state: LazyListState = rememberLazyListState(), // 列表状态
    isEnabled: (index: Int, item: T) -> Boolean = { _, _ -> true }, // 指定Item是否可点击
    initialSelection: Int = -1, // 初始化选择的Item
    waitForPositiveButton: Boolean = true, // 是否点击确定按钮后返回选择结果
    radioButton: @Composable (index: Int, item: T, selected: Boolean, enabled: Boolean, onClick: () -> Unit) -> Unit, // 单选按钮布局
    choiceText: @Composable (index: Int, item: T, enabled: Boolean) -> Unit, // 单选文本布局
    onChoiceChange: (index: Int, item: T) -> Unit // 选择回调
) {
  // 已选择的索引
    var selectedItem by remember { mutableIntStateOf(initialSelection) }
  // 有选择的数据，PositiveButton可以点击。
    PositiveButtonState(isValid = selectedItem != -1)
  // waitForPositiveButton = true，触发PositiveButton时回调。
    if (waitForPositiveButton) {
        Callback {
            if (selectedItem != -1) {
                onChoiceChange(selectedItem, list[selectedItem])
            }
        }
    }
  // Item选择事件
    val onSelect = { index: Int ->
        // 判断当前Item是否为Enable状态
        if (isEnabled(index, list[index])) {
            selectedItem = index
          // waitForPositiveButton = false，每次选择都会回调。
            if (!waitForPositiveButton) {
                onChoiceChange(selectedItem, list[selectedItem])
            }
        }
    }

  // 默认的列表控件
    DialogListItems(
        list = list,
        state = state,
        closeOnClick = false,
        onClick = { index, _ -> onSelect(index) },
        isEnabled = isEnabled
    ) { index, item ->
        val enabled = isEnabled(index, item)
        val selected = remember(selectedItem) { index == selectedItem }

        SingleChoiceItem(
            item = item,
            index = index,
            selected = selected,
            enabled = enabled,
            onSelect = onSelect,
            radioButton = radioButton,
            choiceText = choiceText
        )
    }
}
```

{% endtab %}

{% tab singleT 参数详情 %}

| 参数名                | 类型                                                          | 默认值                     | 描述                                               |
|-------------------|-------------------------------------------------------------|-------------------------|--------------------------------------------------|
| list              | List<T>                                                    | -                       | 集合数据                                           |
| state             | LazyListState                                              | rememberLazyListState() | 列表状态                                          |
| isEnabled         | (index: Int, item: T) -> Boolean                          | { _, _ -> true }       | 指定Item是否可点击                                |
| initialSelection   | Int                                                        | -1                      | 初始化选择的Item                                  |
| waitForPositiveButton | Boolean                                                   | true                    | 是否点击确定按钮后返回选择结果                  |
| radioButton       | @Composable (index: Int, item: T, selected: Boolean, enabled: Boolean, onClick: () -> Unit) -> Unit | -                       | 单选按钮布局                                      |
| choiceText        | @Composable (index: Int, item: T, enabled: Boolean) -> Unit | -                       | 单选文本布局                                |
| onChoiceChange    | (index: Int, item: T) -> Unit                              | -                       | 选择回调                                          |

{% endtab %}

{% endtabs %}



## 多选列表

Dialog中创建多选列表。

{: .important }

多选列表与单选类似，有两个快速使用的组件`MultiChoiceString`和`MultiChoice`，分别是`字符串集合`和`泛型集合`的多选列表。

### String集合多选

{: .important }

> 字符串实际上调用的是`MultiChoice`，只是内部添加了默认的`CheckBox`和`Text`。

{% tabs multiS %}

{% tab multiS 使用方式 %}

```kotlin
VerveDialog(...) {
    MultiChoiceString(list = list) { indices, items ->
        Log.d(TAG, "选择索引：$indices, 选择内容：$items")
    }
}
```

{% endtab %}

{% tab multiS 代码%}

```kotlin
/**
 * Dialog 字符串多选
 */
@Composable
fun DialogScope.MultiChoiceString(
    list: List<String>, // 集合数据
    state: LazyListState = rememberLazyListState(), // 列表状态
    checkBoxColors: CheckboxColors = CheckboxDefaults.colors(), // 多选框颜色
    checkedTextStyle: TextStyle = MaterialTheme.typography.bodyMedium, // 多选文本样式
    isEnabled: (index: Int, item: String) -> Boolean = { _, _ -> true }, // 指定Item是否可点击
    initialSelection: Set<Int> = setOf(), // 初始化选择的Item索引集合
    waitForPositiveButton: Boolean = true, // 是否点击确定按钮后返回选择结果
    onChoiceChange: (indices: Set<Int>, items: Set<String>) -> Unit // 选择回调
) {
    MultiChoice(
        list = list,
        state = state,
        isEnabled = isEnabled,
        initialSelection = initialSelection,
        waitForPositiveButton = waitForPositiveButton,
        checkBox = { index, item, selected, enabled, onCheckedChange ->
            Checkbox(
                checked = selected,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
                colors = checkBoxColors
            )
        },
        checkedText = { _, item, enabled ->
            Text(
                item,
                color = if (enabled) {
                    checkedTextStyle.color
                } else {
                    checkedTextStyle.color.copy(0.5f)
                },
                style = checkedTextStyle
            )
        },
        onChoiceChange = onChoiceChange,
    )
}
```

{% endtab %}

{% tab multiS 参数详情 %}

| 参数名                | 类型                                                          | 默认值                         | 描述                                               |
|-------------------|-------------------------------------------------------------|-----------------------------|--------------------------------------------------|
| list              | List<String>                                               | -                           | 列表                                               |
| state             | LazyListState                                              | rememberLazyListState()     | 列表状态                                          |
| checkBoxColors    | CheckboxColors                                             | CheckboxDefaults.colors()    | 多选框颜色                                        |
| checkedTextStyle  | TextStyle                                                  | MaterialTheme.typography.bodyMedium | 选择文本样式                                  |
| isEnabled         | (index: Int, item: String) -> Boolean                     | { _, _ -> true }           | 指定Item是否可点击                                |
| initialSelection   | Set<Int>                                                  | setOf()                    | 初始化选择的Item索引集合                         |
| waitForPositiveButton | Boolean                                                   | true                        | 是否点击确定按钮后返回选择结果                  |
| onChoiceChange    | (indices: Set<Int>, items: Set<String>) -> Unit           | -                           | 选择回调                                          |

{% endtab %}

{% endtabs %}

### 泛型集合单选

{: .warning }

> 传入自己的数据集合，选择结果回调会返回所传入的泛型类型。
>
> 泛型集合多选需要你自己实现单选按钮（radioButton）和单选文本（choiceText）。

{% tabs multiT %}

{% tab multiT 使用方式 %}

```kotlin
VerveDialog(...) {
    MultiChoice(
        list = list,
        checkBox = { index, item, selected, enabled, onCheckedChange ->
            Checkbox(
                checked = selected,
                onCheckedChange = onCheckedChange,
                enabled = enabled,
            )
        },
        checkedText = { index, item, enabled ->
            Text(
                item,
                color = if (enabled) {
                    MaterialTheme.colorScheme.onSurface
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(0.5f)
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }
    ) { index, item ->
        Log.d(TAG, "选择索引：$index, 选择内容：$item")
    }
}
```

{% endtab %}

{% tab multiT 代码%}

```kotlin
/**
 * Dialog 泛型类型多选
 */
@Composable
fun <T> DialogScope.MultiChoice(
    list: List<T>, // 集合列表
    state: LazyListState = rememberLazyListState(), // 列表状态
    isEnabled: (index: Int, item: T) -> Boolean = { _, _ -> true }, // 指定Item是否可以点击
    initialSelection: Set<Int> = setOf(), // 初始化选择的Item索引集合
    waitForPositiveButton: Boolean = true, // 是否点击确定按钮后返回选择结果
    checkBox: @Composable (index: Int, item: T, selected: Boolean, enabled: Boolean, onCheckedChange: ((Boolean) -> Unit)?) -> Unit, // 多选框布局
    checkedText: @Composable (index: Int, item: T, enabled: Boolean) -> Unit, // 选择文本布局
    onChoiceChange: (indices: Set<Int>, items: Set<T>) -> Unit // 选择回调
) {
  // 当前已选择的索引数据
    var selectedItems by remember { mutableStateOf(initialSelection) }
  // waitForPositiveButton = true，触发PositiveButton时回调。
    if (waitForPositiveButton) {
        Callback { onChoiceChange(selectedItems, selectedItems.map { list[it] }.toSet()) }
    }
  // // Item选择事件
    val onChecked = { index: Int ->
       // 判断当前Item是否为Enable状态
        if (isEnabled(index, list[index])) {
          // 获取已选择的索引数据
            val newSelectedItems = selectedItems.toMutableSet()
          // 处理选择逻辑
            if (index in selectedItems) {
                newSelectedItems.remove(index)
            } else {
                newSelectedItems.add(index)
            }
            selectedItems = newSelectedItems.toSet()
     		  // waitForPositiveButton = false，每次选择都会回调。
            if (!waitForPositiveButton) {
                onChoiceChange(selectedItems, selectedItems.map { list[it] }.toSet())
            }
        }
    }
	 // 默认的列表控件
    DialogListItems(
        list = list,
        state = state,
        onClick = { index, _ -> onChecked(index) },
        isEnabled = isEnabled,
        closeOnClick = false
    ) { index, item ->
        val enabled = isEnabled(index, list[index])
        val selected = remember(selectedItems) { index in selectedItems }

        MultiChoiceItem(
            item = item,
            index = index,
            selected = selected,
            enabled = enabled,
            onChecked = onChecked,
            checkBox = checkBox,
            checkedText = checkedText
        )
    }
}
```

{% endtab %}

{% tab multiT 参数详情 %}

| 参数名                | 类型                                                         | 默认值                  | 描述                           |
| --------------------- | ------------------------------------------------------------ | ----------------------- | ------------------------------ |
| list                  | List<T>                                                      | -                       | 列表                           |
| state                 | LazyListState                                                | rememberLazyListState() | 列表状态                       |
| isEnabled             | (index: Int, item: T) -> Boolean                             | { _, _ -> true }        | 指定Item是否可点击             |
| initialSelection      | Set<Int>                                                     | setOf()                 | 初始化选择的Item索引集合       |
| waitForPositiveButton | Boolean                                                      | true                    | 是否点击确定按钮后返回选择结果 |
| checkBox              | @Composable (index: Int, item: T, selected: Boolean, enabled: Boolean, onCheckedChange: ((Boolean) -> Unit)?) -> Unit | -                       | 多选框布局                     |
| checkedText           | @Composable (index: Int, item: T, enabled: Boolean) -> Unit  | -                       | 选择文本布局                   |
| onChoiceChange        | (indices: Set<Int>, items: Set<T>) -> Unit                   | -                       | 选择回调                       |

{% endtab %}

{% endtabs %}

