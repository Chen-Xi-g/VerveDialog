---
title: 全局样式设置
layout: home
usetocbot: true
---

<div style="text-align: center; margin: 20px 0;">
  <h1>全局样式设置</h1>
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

{: .highlight }
全局配置根据项目需求在`Application.onCreate`或合适的`@Composable`中配置。

## 基础 Dialog

{% tabs config %}



{% tab config 配置%}

创建 `DialogConfig` 对象，或调用 `defaultDialogConfig().copy()` 生成配置副本。

```kotlin
DialogGlobalConfig.updateDialogConfig(config: DialogConfig)
```

{% endtab %}



{% tab config 参数说明%}

| 参数名                    | 类型                     | 默认值                 | 描述                                     |
|---------------------------|--------------------------|------------------------|------------------------------------------|
| properties                | DialogProperties         | DialogProperties()     | Dialog 的属性设置                          |
| backgroundColor           | Color                    | Color.White            | Dialog 背景颜色                            |
| shape                     | RoundedCornerShape       | RoundedCornerShape(8.dp) | Dialog 形状                        |
| border                    | BorderStroke?            | null                   | Dialog 边框                               |
| padding                   | PaddingValues            | PaddingValues(0.dp)    | Dialog 与屏幕边缘的间距                   |
| contentPadding            | PaddingValues            | PaddingValues(5.dp)    | Dialog 内容区域的内边距                   |
| shadowElevation           | Dp                       | 0.dp                   | 阴影效果                                  |
| tonalElevation            | Dp                       | 0.dp                   | 颜色调整                                  |
| noContentPadding          | Boolean                  | false                  | 是否取消内容区域的内边距                  |
| autoDismissPositiveButton | Boolean                  | true                   | 点击 PositiveButton 是否自动关闭 Dialog  |


{% endtab %}



{% endtabs %}



## 底部 Dialog

{% tabs bottom %}



{% tab bottom 配置%}

创建 `BottomDialogConfig` 对象，或调用 `defaultBottomDialogConfig().copy()` 生成配置副本。

```kotlin
DialogGlobalConfig.updateBottomDialogConfig(config: BottomDialogConfig)
```

{% endtab %}



{% tab bottom 参数说明%}

| 参数名             | 类型              | 默认值                   | 描述                               |
|--------------------|-------------------|--------------------------|------------------------------------|
| properties         | DialogProperties  | DialogProperties()       | Dialog 的属性设置                    |
| backgroundColor    | Color             | Color.White              | Dialog 背景颜色                      |
| shape              | RoundedCornerShape| RoundedCornerShape(8.dp) | Dialog 形状                         |
| padding            | PaddingValues     | PaddingValues(5.dp)      | Dialog 内容区域的内边距               |
| tonalElevation     | Dp                | 0.dp                     | 颜色调整                             |
| noContentPadding   | Boolean           | false                    | 是否取消内容区域的内边距              |
| autoDismiss        | Boolean           | true                     | 触发事件后是否自动关闭                 |
| bottomHideDrag     | Boolean           | true                     | 底部隐藏拖拽                         |
| enableDrag         | Boolean           | true                     | 是否启用拖拽                         |



{% endtab %}



{% endtabs %}



## Dialog 标题

{% tabs title %}



{% tab title 配置%}

创建 `DialogTitleConfig` 对象，或调用 `defaultDialogTitleConfig().copy()` 生成配置副本。

```kotlin
DialogGlobalConfig.updateTitleConfig(config: DialogTitleConfig)
```

{% endtab %}



{% tab title 参数说明%}

| 参数名           | 类型                      | 默认值                                                          | 描述                       |
|------------------|---------------------------|-----------------------------------------------------------------|----------------------------|
| color            | Color                     | Color.Black                                                     | 标题颜色                   |
| style            | TextStyle                 | TextStyle.Default.copy(...)                                     | 标题样式                   |
| padding          | PaddingValues             | PaddingValues(horizontal = 15.dp, vertical = 15.dp)             | 标题的内边距               |
| alignment        | Alignment.Horizontal      | Alignment.Start                                                 | 文本对齐方式               |
| iconArrangement  | Arrangement.Horizontal    | Arrangement.Start                                               | 图标和标题布局             |
| icon             | @Composable (() -> Unit)  | {}                                                              | 标题图标                   |



{% endtab %}



{% endtabs %}



## Dialog 内容

{% tabs message %}



{% tab message 配置%}

创建 `DialogMessageConfig` 对象，或调用 `defaultDialogMessageConfig().copy()` 生成配置副本。

```kotlin
DialogGlobalConfig.updateMessageConfig(config: DialogMessageConfig)
```

{% endtab %}



{% tab message 参数说明%}

| 参数名      | 类型                     | 默认值                                                         | 描述          |
|-------------|--------------------------|----------------------------------------------------------------|---------------|
| color       | Color                    | Color.Black                                                    | 消息颜色      |
| style       | TextStyle                | TextStyle.Default.copy(...)                                    | 消息样式      |
| padding     | PaddingValues            | PaddingValues(horizontal = 15.dp)                              | 消息的内边距  |
| alignment   | Alignment.Horizontal     | Alignment.Start                                                | 文本对齐方式  |


{% endtab %}



{% endtabs %}

## Dialog 输入框

{% tabs input %}

{% tab input 配置%}

创建 `DialogInputConfig` 对象，或调用 `defaultDialogInputConfig().copy()` 生成配置副本。

```kotlin
DialogGlobalConfig.updateInputConfig(config: DialogInputConfig)
```

{% endtab %}



{% tab input 参数说明%}

| 参数名         | 类型                     | 默认值                                                        | 描述                  |
|----------------|--------------------------|---------------------------------------------------------------|-----------------------|
| border         | BorderStroke             | BorderStroke(1.dp, Color.Gray)                               | 输入框边框            |
| shape | RoundedCornerShape | RoundedCornerShape(8.dp) | 输入框形状 |
| textStyle     | TextStyle                | TextStyle.Default.copy(...)                                   | 输入框文本样式      |
| placeholderStyle | TextStyle | TextStyle.Default.copy(...) | 输入框占位符文本样式 |
| padding        | PaddingValues            | PaddingValues(start = 15.dp, end = 15.dp, top = 10.dp)      | 输入框的内边距        |
| contentPadding | PaddingValues            | PaddingValues(10.dp)                                         | 输入内容的内边距      |
| focusOnShow    | Boolean                  | true                                                          | 显示时是否自动获取焦点 |
| alignment      | Alignment                | Alignment.CenterStart                                         | 文本对齐方式          |


{% endtab %}



{% endtabs %}