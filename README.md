<div align=center>    
    <img src="./images/DialogIcon.png">
</div>
<p align="center">
	<strong>适用于Compose的Dialog</strong>
</p>
<p align="center">
	<a href="https://chen-xi-g.github.io/">使用文档</a>
	| 
	<a href="app/release/verve-dialog-sample.apk">下载Demo</a>
</p>

<p align="center">
  <a href="https://jitpack.io/#Chen-Xi-g/VerveDialog"><img src="https://jitpack.io/v/Chen-Xi-g/VerveDialog.svg"/></a>
  <a href="https://developer.android.com/compose"><img src="https://img.shields.io/badge/Jetpack%20Compose%20-63C487?logo=jetpackcompose&logoColor=white"/></a>
  <a href="https://developer.android.com/kotlin?hl=zh-cn"><img src="https://img.shields.io/badge/Language-Kotlin-2376bc?labelColor=5384EC&color=7F32DA"/></a>
  <a href="https://github.com/Chen-Xi-g/VerveDialog/blob/main/LICENSE"><img src="https://img.shields.io/badge/License-MIT-38519B?labelColor=272c3c"/></a>
</p>

一个基于 Jetpack Compose 的高度可定制化 Dialog 库，适用于 Android 应用。它支持普通对话框和底部对话框（BottomSheet），并提供了多种 UI 组件，用于创建交互式对话框，包括标题、图标标题、消息、输入框、单选、多选列表等。通过全局配置和灵活的 API，您可以轻松调整对话框的样式和功能，以适应您的应用设计需求。

## 特性

- **自定义样式**：支持背景颜色、形状、边框等自定义设置。
- **可配置按钮**：可自定义对话框中的按钮布局。
- **自动关闭**：支持在点击按钮后自动关闭对话框。
- **底部对话框**：支持底部对话框样式，并可自定义拖拽手柄的显示。
- **高扩展性：**支持用户根据不同需求进行扩展使用。

## 安装

确保你在项目的 `build.gradle` 文件中添加了 Compose 依赖。

```kotlin
dependencies {
		implementation("com.github.Chen-Xi-g:VerveDialog:Tag")
}
```

### 初始化配置

您可以在应用启动时初始化全局配置，不设置则使用默认：

```kotlin
DialogGlobalConfig.updateDialogConfig(defaultDialogConfig())
DialogGlobalConfig.updateTitleConfig(defaultDialogTitleConfig())
DialogGlobalConfig.updateMessageConfig(defaultDialogMessageConfig())
DialogGlobalConfig.updateInputConfig(defaultDialogInputConfig())
```

## 使用

### 普通对话框

使用 `VerveDialog` 函数来创建和显示普通对话框。

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveDialog(
    dialogState = dialogState,
    buttons = {
        PositiveButton("确定")
        NegativeButton("取消")
    }
) {
    Title(text = "提示")
    Message(text = "这是一个普通Dialog弹窗")
}
```

### 底部对话框

使用 `VerveBottomDialog` 函数来创建和显示底部对话框。

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveBottomDialog(
    dialogState = dialogState
) {
    Title(text = "提示")
    Message(text = "这是一个底部Dialog弹窗")
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

## 内置样式

在`VerveDialog`或`VerveBottomDialog`的`content: @Composable DialogScope.() -> Unit`中调用基础的UI样式

### 标题

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveBottomDialog(
    dialogState = dialogState
) {
  	// 调用DialogScope.Title设置标题
    Title(text = "Dialog标题")
}
```

### 带Icon的标题

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveBottomDialog(
    dialogState = dialogState
) {
  	// 调用DialogScope.IconTitle设置带Icon的标题
    IconTitle(text = "Icon标题") {
        Image(
            painter = painterResource(id = R.drawable.ic_dialog_icon),
            modifier = Modifier.size(38.dp),
            contentDescription = "Title"
        )
    }
}
```

### 消息

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveBottomDialog(
    dialogState = dialogState
) {
  	// 调用DialogScope.Message设置消息
		Message(text = "显示Dialog中提示的消息内容")
}
```

### 输入框

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

VerveBottomDialog(
    dialogState = dialogState
) {
  	// 调用DialogScope.Input设置输入框
    Input(placeholder = "请输入内容", focusOnShow = false) {
        Toast.makeText(context, "输入的内容是：$it", Toast.LENGTH_SHORT).show()
    }
}
```

### 单选列表

单选列表内置了默认的样式，可以传递String类型的集合或者泛型集合。

**String集合**

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

val list = listOf("选项1", "选项2", "选项3", "选项4")
VerveDialog(
    dialogState = dialogState
) {
  	// 调用DialogScope.SingleChoiceString设置String类型的单选列表
    SingleChoiceString(list = list) { _, item ->
        Toast.makeText(context, "选择的内容是：$item", Toast.LENGTH_SHORT).show()
    }
}
```

**泛型集合**

```kotlin
data class ChoiceEntity(
    val id: Int,
    val name: String
)

// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

val list = listOf(
    ChoiceEntity(1, "选项1"),
    ChoiceEntity(2, "选项2"),
    ChoiceEntity(3, "选项3"),
    ChoiceEntity(4, "选项4")
)
VerveDialog(
    dialogState = dialogState
) {
  	// 调用DialogScope.SingleChoice设置泛型类型的单选列表
    SingleChoice(
        list = list,
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

### 多选列表

多选列表内置了默认的样式，可以传递String类型的集合或者泛型集合。

**String集合**

```kotlin
// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

val list = listOf("选项1", "选项2", "选项3", "选项4")
VerveDialog(
    dialogState = dialogState
) {
  	// 调用DialogScope.MultiChoiceString设置String类型的多选列表
    MultiChoiceString(list = list) { _, item ->
        Toast.makeText(context, "选择的内容是：$item", Toast.LENGTH_SHORT).show()
    }
}
```

**泛型集合**

```kotlin
data class ChoiceEntity(
    val id: Int,
    val name: String
)

// 控制Dialog显示隐藏状态
val dialogState = rememberDialogState()

val list = listOf(
    ChoiceEntity(1, "选项1"),
    ChoiceEntity(2, "选项2"),
    ChoiceEntity(3, "选项3"),
    ChoiceEntity(4, "选项4")
)
VerveDialog(
    dialogState = dialogState
) {
  	// 调用DialogScope.MultiChoice设置泛型类型的单选列表
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

## 贡献

欢迎为该项目贡献代码或提出建议。你可以通过 GitHub 提交 issue 或 pull request。
