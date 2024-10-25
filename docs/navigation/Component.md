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

{% tab title 使用方式 %}

```kotlin
VerveDialog(...) {
    Title(text = "提示")
}
```

{% endtab %}

{% endtabs %}



## IconTitle

Dialog中携带Icon的标题组件

{: .warning }

参数中的`text`和`res`二者必须传递一个，否则显示为空字符串。

{% tabs icon %}

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

{% endtabs %}



## Message

Dialog中携带Icon的标题组件

{: .warning }

参数中的`text`和`res`二者必须传递一个，否则显示为空字符串。

{% tabs message %}

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

{% tab message 使用方式 %}

```kotlin
VerveDialog(...) {
    Message(text = "这是一个普通Dialog弹窗")
}
```

{% endtab %}

{% endtabs %}

