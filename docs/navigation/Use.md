---
title: 使用
layout: home
usetocbot: true
---



<div style="text-align: center; margin: 20px 0;">
  <h1>使用</h1>
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

## 全局样式设置

您可以在应用启动时初始化全局配置：

```kotlin
// 设置Dialog配置
DialogGlobalConfig.updateDialogConfig(defaultDialogConfig())
// 设置Bottom Sheet Dialog配置
DialogGlobalConfig.updateBottomDialogConfig(defaultBottomDialogConfig())
// 设置标题配置
DialogGlobalConfig.updateTitleConfig(defaultDialogTitleConfig())
// 设置内容配置
DialogGlobalConfig.updateMessageConfig(defaultDialogMessageConfig())
// 设置输入框配置
DialogGlobalConfig.updateInputConfig(defaultDialogInputConfig())
```

[查看全局样式设置文档]({{ '/navigation/GlobalConfig.html' | relative_url }}){: .btn .mr-4}

## 内置组件

`VerveDialog`提供基础的内置组件来实现不同功能，详细说明请查看内置组件文档。

[查看内置组件文档]({{ '/navigation/Component.html' | relative_url }}){: .btn .mr-4}

## 基础

`VerveDialog`默认有两种样式：

* **默认：**调用`VerveDialog()`创建默认对话框。
* **BottomSheet：**调用`VerveBottomDialog()`创建底部对话框。

{% tabs default%}



{% tab default 默认弹窗%}

<img src="/assets/images/dialog-default.png" alt="DefaultDialog" style="max-width: 40%; height: auto;">

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveDialog(
    dialogState = dialogState,
    buttons = {// ...}
) {
  // 设置标题
    Title(text = "提示")
  // 设置内容
    Message(text = "这是一个普通Dialog弹窗")
}
```

{% endtab %}



{% tab default 底部弹窗%}

<img src="/assets/images/dialog-bottom.png" alt="BottomDefaultDialog" style="max-width: 40%; height: auto;">

{: .important }

> Bottom Dialog 没有接收 Button 按钮的参数。
>
> 你可以通过内部封装的`NegativeButton`和`PositiveButton`添加到内容当中。

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveBottomDialog(
    dialogState = dialogState
) {
  // 设置标题
    Title(text = "隐私政策")
  // 设置内容
    Message(res = R.string.privacy_policy_content)
  // ...
}
```

{% endtab %}



{% endtabs %}



## 按钮

默认对话框和底部对话框创建按钮方式不同：

* **默认对话框：**调用`VerveDialog()`后传入`buttons`参数，如`NegativeButton`和`PositiveButton`。
* **底部对话框：**调用`VerveBottomDialog()`后在`content`中创建按钮，如`NegativeButton`和`PositiveButton`。

{% tabs button%}



{% tab button 默认弹窗%}

<img src="/assets/images/dialog-default.png" alt="DefaultDialog" style="max-width: 40%; height: auto;">

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveDialog(
    dialogState = dialogState,
    buttons = {
      // 添加按钮
        PositiveButton("确定")
        NegativeButton("取消")
    }
) {
  // ...
}
```

{% endtab %}



{% tab button 底部弹窗%}

<img src="/assets/images/dialog-bottom.png" alt="BottomDefaultDialog" style="max-width: 40%; height: auto;">

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveBottomDialog(
    dialogState = dialogState
) {
  // ...
  // 添加按钮
    Row(
      modifier = Modifier
          .fillMaxWidth()
          .padding(top = 15.dp),
      horizontalArrangement = Arrangement.SpaceAround,
    ) {
        NegativeButton("取消")
        PositiveButton("确认")
    }
}
```

{% endtab %}



{% endtabs %}



## Icon+标题

{% tabs icon%}



{% tab icon 默认弹窗%}

<img src="/assets/images/dialog-default-icon.png" alt="DefaultDialog" style="max-width: 40%; height: auto;">

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveDialog(
    dialogState = dialogState,
    buttons = {// ...}
) {
  // 设置Icon+标题
    IconTitle(text = "提示") {
        Image(
            painter = painterResource(id = R.drawable.ic_dialog_icon),
            modifier = Modifier.size(38.dp),
            contentDescription = "Icon"
        )
    }
  // ...
}
```

{% endtab %}



{% tab icon 底部弹窗%}

<img src="/assets/images/dialog-bottom-icon.png" alt="BottomDefaultDialog" style="max-width: 40%; height: auto;">

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveBottomDialog(
    dialogState = dialogState
) {
  // 设置Icon+标题
    IconTitle(text = "隐私政策") {
        Image(
            painter = painterResource(id = R.drawable.ic_dialog_icon),
            modifier = Modifier.size(38.dp),
            contentDescription = "Tint"
        )
    }
  // ...
}
```

{% endtab %}



{% endtabs %}



## 输入框

Dialog显示时默认弹出软键盘，设置`focusOnShow=false`则关闭。



{% tabs input %}



{% tab input 默认弹窗%}

<img src="/assets/images/dialog-default-input-show.png" alt="DefaultDialog" style="max-width: 40%; height: auto;">

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveDialog(
    dialogState = dialogState,
    buttons = {// ...}
) {
  // ...
  // 设置输入框
    Input(placeholder = "请输入内容", focusOnShow = false) {
        Log.d(TAG,"输入的内容：$it")
    }
}
```

{% endtab %}



{% tab input 底部弹窗%}

<img src="/assets/images/dialog-bottom-input-show.png" alt="BottomDefaultDialog" style="max-width: 40%; height: auto;">

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveBottomDialog(
    dialogState = dialogState
) {
  // ...
  // 设置输入框
    Input(placeholder = "请输入内容") {
        Log.d(TAG,"输入的内容：$it")
    }
}
```

{% endtab %}



{% endtabs %}



## 单选列表

单选默认提供两种参数传递：

* **String集合：**调用`SingleChoiceString()`传入`List<String>`数据。
* **泛型集合：**调用`SingleChoice()`传入`List<T>`数据，`onCheckedChange()`获取选择数据，`checkedText()`实现文本布局。



{% tabs single %}



{% tab single 默认弹窗%}

<img src="/assets/images/dialog-default-single.png" alt="DefaultDialog" style="max-width: 40%; height: auto;">

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()
// String 集合
val list = listOf("选项1", "选项2", "选项3", "选项4")
// 泛型集合
val list2 = listOf(
  ChoiceEntity(1,"选项1"),
  ChoiceEntity(2,"选项2"),
  ChoiceEntity(3,"选项3"),
  ChoiceEntity(4,"选项4")
)

VerveDialog(
    dialogState = state,
    buttons = {//...}
) {
	// ...
  // String 集合单选
    SingleChoiceString(list = list) { index, item ->
        Log.d(TAG,"选择索引：$index, 选择内容：$item")
    }
  
  // 泛型集合单选
    SingleChoice(
        list = list,
        onChoiceChange = { index, item ->
            Log.d(TAG, "选择索引：$index, 选择内容：${item.name}")
        }
    ) { index, item, enabled ->
        Text(
            text = item.name,
            color = if (enabled) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurface.copy(0.5f)
            }
        )
    }
}
```

{% endtab %}



{% tab single 底部弹窗%}

<img src="/assets/images/dialog-bottom-single.png" alt="BottomDefaultDialog" style="max-width: 40%; height: auto;">

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()
// String 集合
val list = listOf("选项1", "选项2", "选项3", "选项4")
// 泛型集合
val list2 = listOf(
  ChoiceEntity(1,"选项1"),
  ChoiceEntity(2,"选项2"),
  ChoiceEntity(3,"选项3"),
  ChoiceEntity(4,"选项4")
)

VerveBottomDialog(
    dialogState = state
) {
  // ...
  // String 集合单选
    SingleChoiceString(list = list) { index, item ->
        Log.d(TAG,"选择索引：$index, 选择内容：$item")
    }
  
  // 泛型集合单选
    SingleChoice(
        list = list2,
        onChoiceChange = { index, item ->
            Log.d(TAG, "选择索引：$index, 选择内容：${item.name}")
        }
    ) { index, item, enabled ->
        Text(
            text = item.name,
            color = if (enabled) { // 根据Enable状态设置
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurface.copy(0.5f)
            }
        )
    }
}
```

{% endtab %}



{% endtabs %}



## 多选列表

多选默认提供两种参数传递：

* **String集合：**调用`MultiChoiceString()`传入`List<String>`数据。
* **泛型集合：**调用`MultiChoice()`传入`List<T>`数据，`onCheckedChange()`获取选择数据，`checkedText()`实现文本布局。



{% tabs multi %}



{% tab multi 默认弹窗%}

<img src="/assets/images/dialog-default-multi.png" alt="DefaultDialog" style="max-width: 40%; height: auto;">

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()
// String 集合
val list = listOf("选项1", "选项2", "选项3", "选项4")
// 泛型集合
val list2 = listOf(
  ChoiceEntity(1,"选项1"),
  ChoiceEntity(2,"选项2"),
  ChoiceEntity(3,"选项3"),
  ChoiceEntity(4,"选项4")
)

VerveDialog(
    dialogState = state,
    buttons = {//...}
) {
	// ...
  // String 集合多选，indices：当前已选择的所有索引，items：当前已选择的所有数据
    MultiChoiceString(list = list) { indices, items ->
        Log.d(TAG,"选择索引：$indices, 选择内容：$items")
    }
  
  // 泛型集合多选，indices：当前已选择的所有索引，items：当前已选择的所有数据
    MultiChoice(
        list = list2,
        onChoiceChange = { indices, items ->
            Log.d(TAG, "选择索引：$indices 选择内容：$items")
        }
    ) { index, item, enabled ->
        Text(
            text = item.name,
            color = if (enabled) {
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurface.copy(0.5f)
            }
        )
    }
}
```

{% endtab %}



{% tab multi 底部弹窗%}

<img src="/assets/images/dialog-bottom-multi.png" alt="BottomDefaultDialog" style="max-width: 40%; height: auto;">

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()
// String 集合
val list = listOf("选项1", "选项2", "选项3", "选项4")
// 泛型集合
val list2 = listOf(
  ChoiceEntity(1,"选项1"),
  ChoiceEntity(2,"选项2"),
  ChoiceEntity(3,"选项3"),
  ChoiceEntity(4,"选项4")
)

VerveBottomDialog(
    dialogState = state
) {
	// ...
  // String 集合多选，indices：当前已选择的所有索引，items：当前已选择的所有数据
    MultiChoiceString(list = list) { index, item ->
        Log.d(TAG,"选择索引：$index, 选择内容：$item")
    }
  
  // 泛型集合多选，indices：当前已选择的所有索引，items：当前已选择的所有数据
    MultiChoice(
        list = list2,
        onChoiceChange = { index, item ->
            Log.d(TAG, "选择索引：$index, 选择内容：${item.name}")
        }
    ) { index, item, enabled ->
        Text(
            text = item.name,
            color = if (enabled) { // 根据Enable状态设置
                MaterialTheme.colorScheme.onSurface
            } else {
                MaterialTheme.colorScheme.onSurface.copy(0.5f)
            }
        )
    }
}
```

{% endtab %}



{% endtabs %}
