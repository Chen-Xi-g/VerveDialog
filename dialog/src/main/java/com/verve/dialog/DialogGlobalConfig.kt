package com.verve.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

/**
 * Dialog配置
 *
 * @param properties Dialog属性
 * @param backgroundColor Dialog背景颜色
 * @param shape Dialog形状
 * @param border Dialog边框
 * @param padding Dialog距离屏幕边缘的padding
 * @param contentPadding Dialog内容区域的padding
 * @param shadowElevation 阴影效果
 * @param tonalElevation 颜色调整
 * @param noContentPadding 是否取消内容区域的padding
 * @param autoDismissPositiveButton 点击PositiveButton是否自动关闭Dialog
 */
@Stable
class DialogConfig(
    var properties: DialogProperties,
    var backgroundColor: Color,
    var shape: RoundedCornerShape,
    var border: BorderStroke?,
    var padding: PaddingValues,
    var contentPadding: PaddingValues,
    var shadowElevation: Dp,
    var tonalElevation: Dp,
    var noContentPadding: Boolean,
    var autoDismissPositiveButton: Boolean
) {
    fun copy(
        properties: DialogProperties = this.properties,
        backgroundColor: Color = this.backgroundColor,
        shape: RoundedCornerShape = this.shape,
        border: BorderStroke? = this.border,
        padding: PaddingValues = this.padding,
        contentPadding: PaddingValues = this.contentPadding,
        shadowElevation: Dp = this.shadowElevation,
        tonalElevation: Dp = this.tonalElevation,
        noContentPadding: Boolean = this.noContentPadding,
        autoDismissPositiveButton: Boolean = this.autoDismissPositiveButton
    ): DialogConfig {
        return DialogConfig(
            properties = properties,
            backgroundColor = backgroundColor,
            shape = shape,
            border = border,
            padding = padding,
            contentPadding = contentPadding,
            shadowElevation = shadowElevation,
            tonalElevation = tonalElevation,
            noContentPadding = noContentPadding,
            autoDismissPositiveButton = autoDismissPositiveButton
        )
    }

    override fun toString(): String {
        return "DialogConfig(" +
                "properties=$properties, " +
                "backgroundColor=$backgroundColor, " +
                "shape=$shape, " +
                "border=$border, " +
                "padding=$padding, " +
                "contentPadding=$contentPadding, " +
                "shadowElevation=$shadowElevation, " +
                "tonalElevation=$tonalElevation, " +
                "noContentPadding=$noContentPadding, " +
                "autoDismissPositiveButton=$autoDismissPositiveButton" +
                ")"
    }
}

/**
 * 默认的Dialog配置
 *
 * @param properties Dialog属性
 * @param backgroundColor Dialog背景颜色
 * @param shape Dialog形状
 * @param border Dialog边框
 * @param padding Dialog距离屏幕边缘的padding
 * @param contentPadding Dialog内容区域的padding
 * @param shadowElevation 阴影效果
 * @param tonalElevation 颜色调整
 * @param noContentPadding 是否取消内容区域的padding
 * @param autoDismissPositiveButton 点击PositiveButton是否自动关闭Dialog
 */
fun defaultDialogConfig(
    properties: DialogProperties = DialogProperties(),
    backgroundColor: Color = Color.White,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    border: BorderStroke? = null,
    padding: PaddingValues = PaddingValues(0.dp),
    contentPadding: PaddingValues = PaddingValues(5.dp),
    shadowElevation: Dp = 0.dp,
    tonalElevation: Dp = 0.dp,
    noContentPadding: Boolean = false,
    autoDismissPositiveButton: Boolean = true
): DialogConfig = DialogConfig(
    properties = properties,
    backgroundColor = backgroundColor,
    shape = shape,
    border = border,
    padding = padding,
    contentPadding = contentPadding,
    shadowElevation = shadowElevation,
    tonalElevation = tonalElevation,
    noContentPadding = noContentPadding,
    autoDismissPositiveButton = autoDismissPositiveButton
)

/**
 * BottomDialog配置
 *
 * @param backgroundColor Dialog背景颜色
 * @param shape Dialog形状
 * @param padding Dialog内容区域的padding
 * @param tonalElevation 颜色调整
 * @param noContentPadding 是否取消内容区域的padding
 * @param autoDismiss 触发事件后是否自动关闭
 */
@Stable
class BottomDialogConfig(
    var properties: DialogProperties,
    var backgroundColor: Color,
    var shape: RoundedCornerShape,
    var padding: PaddingValues,
    var tonalElevation: Dp,
    var noContentPadding: Boolean,
    var autoDismiss: Boolean,
    var bottomHideDrag: Boolean,
    var enableDrag: Boolean
) {
    fun copy(
        properties: DialogProperties = this.properties,
        backgroundColor: Color = this.backgroundColor,
        shape: RoundedCornerShape = this.shape,
        padding: PaddingValues = this.padding,
        tonalElevation: Dp = this.tonalElevation,
        noContentPadding: Boolean = this.noContentPadding,
        autoDismiss: Boolean = this.autoDismiss,
        bottomHideDrag: Boolean = this.bottomHideDrag,
        enableDrag: Boolean = this.enableDrag
    ): BottomDialogConfig {
        return BottomDialogConfig(
            properties = properties,
            backgroundColor = backgroundColor,
            shape = shape,
            padding = padding,
            tonalElevation = tonalElevation,
            noContentPadding = noContentPadding,
            autoDismiss = autoDismiss,
            bottomHideDrag = bottomHideDrag,
            enableDrag = enableDrag
        )
    }

    override fun toString(): String {
        return "BottomDialogConfig(" +
                "properties=$properties, " +
                "backgroundColor=$backgroundColor, " +
                "shape=$shape, " +
                "padding=$padding, " +
                "tonalElevation=$tonalElevation, " +
                "noContentPadding=$noContentPadding, " +
                "autoDismiss=$autoDismiss, " +
                "bottomHideDrag=$bottomHideDrag, " +
                "enableDrag=$enableDrag" +
                ")"
    }
}

/**
 * 默认的BottomDialog配置
 *
 * @param backgroundColor Dialog背景颜色
 * @param shape Dialog形状
 * @param padding Dialog内容区域的padding
 * @param tonalElevation 颜色调整
 * @param noContentPadding 是否取消内容区域的padding
 * @param autoDismiss 触发事件后是否自动关闭
 * @param bottomHideDrag 底部隐藏拖拽
 * @param enableDrag 是否启用拖拽
 */
fun defaultBottomDialogConfig(
    properties: DialogProperties = DialogProperties(),
    backgroundColor: Color = Color.White,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    padding: PaddingValues = PaddingValues(5.dp),
    tonalElevation: Dp = 0.dp,
    noContentPadding: Boolean = false,
    autoDismiss: Boolean = true,
    bottomHideDrag: Boolean = true,
    enableDrag: Boolean = true
): BottomDialogConfig = BottomDialogConfig(
    properties = properties,
    backgroundColor = backgroundColor,
    shape = shape,
    padding = padding,
    tonalElevation = tonalElevation,
    noContentPadding = noContentPadding,
    autoDismiss = autoDismiss,
    bottomHideDrag = bottomHideDrag,
    enableDrag = enableDrag
)

/**
 * Dialog标题配置
 *
 * @param color 标题颜色
 * @param style 标题样式
 * @param padding 标题padding
 * @param alignment 文本位置
 * @param iconArrangement 标题布局Icon+Title位置
 * @param icon 标题图标
 */
@Stable
class DialogTitleConfig(
    var color: Color,
    var style: TextStyle,
    var padding: PaddingValues,
    var alignment: Alignment.Horizontal,
    var iconArrangement: Arrangement.Horizontal,
    var icon: @Composable (() -> Unit) = {}
) {

    fun copy(
        color: Color = this.color,
        style: TextStyle = this.style,
        padding: PaddingValues = this.padding,
        alignment: Alignment.Horizontal = this.alignment,
        arrangement: Arrangement.Horizontal = this.iconArrangement,
        icon: @Composable (() -> Unit) = this.icon
    ): DialogTitleConfig {
        return DialogTitleConfig(
            color = color,
            style = style,
            padding = padding,
            alignment = alignment,
            iconArrangement = arrangement,
            icon = icon
        )
    }

    override fun toString(): String {
        return "DialogTitleConfig(" +
                "color=$color, " +
                "style=$style, " +
                "padding=$padding, " +
                "isCentered=$alignment, " +
                "icon=$icon," +
                ")"
    }
}

/**
 * 默认的Dialog标题配置
 *
 * @param color 标题颜色
 * @param style 标题样式
 * @param padding 标题padding
 * @param alignment 文本位置
 * @param iconArrangement 标题布局Icon+Title位置
 * @param icon 标题图标
 */
fun defaultDialogTitleConfig(
    color: Color = Color.Black,
    style: TextStyle = TextStyle.Default.copy(
        platformStyle = PlatformTextStyle(
            includeFontPadding = true
        ),
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.2.sp
    ),
    padding: PaddingValues = PaddingValues(horizontal = 15.dp, vertical = 15.dp),
    alignment: Alignment.Horizontal = Alignment.Start,
    iconArrangement: Arrangement.Horizontal = Arrangement.Start,
    icon: @Composable (() -> Unit) = {}
): DialogTitleConfig = DialogTitleConfig(
    color = color,
    style = style,
    padding = padding,
    alignment = alignment,
    iconArrangement = iconArrangement,
    icon = icon
)

/**
 * Dialog消息配置
 *
 * @param color 消息颜色
 * @param style 消息样式
 * @param padding 消息padding
 * @param alignment 文本位置
 */
@Stable
class DialogMessageConfig(
    var color: Color,
    var style: TextStyle,
    var padding: PaddingValues,
    var alignment: Alignment.Horizontal
) {
    fun copy(
        color: Color = this.color,
        style: TextStyle = this.style,
        padding: PaddingValues = this.padding,
        alignment: Alignment.Horizontal = this.alignment
    ): DialogMessageConfig {
        return DialogMessageConfig(
            color = color,
            style = style,
            padding = padding,
            alignment = alignment
        )
    }

    override fun toString(): String {
        return "DialogTextConfig(" +
                "color=$color, " +
                "style=$style, " +
                "padding=$padding, " +
                "alignment=$alignment" +
                ")"
    }
}

/**
 * 默认的Dialog消息配置
 *
 * @param color 消息颜色
 * @param style 消息样式
 * @param padding 消息padding
 * @param alignment 文本位置
 */
fun defaultDialogMessageConfig(
    color: Color = Color.Black,
    style: TextStyle = TextStyle.Default.copy(
        platformStyle = PlatformTextStyle(
            includeFontPadding = true
        ),
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.2.sp
    ),
    padding: PaddingValues = PaddingValues(horizontal = 15.dp),
    alignment: Alignment.Horizontal = Alignment.Start
): DialogMessageConfig = DialogMessageConfig(
    color = color,
    style = style,
    padding = padding,
    alignment = alignment
)

/**
 * Dialog输入框配置
 *
 * @param border 输入框边框
 * @param shape 输入框形状
 * @param textStyle 输入框文本样式
 * @param placeholderStyle 输入框占位符文本样式
 * @param padding 输入框padding
 * @param contentPadding 输入框内容padding
 * @param focusOnShow 是否在显示时自动获取焦点
 * @param alignment 输入框文本位置
 */
@Stable
class DialogInputConfig(
    var border: BorderStroke,
    var shape: RoundedCornerShape,
    var textStyle: TextStyle,
    var placeholderStyle: TextStyle,
    var padding: PaddingValues,
    var contentPadding: PaddingValues,
    var focusOnShow: Boolean,
    var alignment: Alignment
) {
    fun copy(
        border: BorderStroke = this.border,
        shape: RoundedCornerShape = this.shape,
        textStyle: TextStyle = this.textStyle,
        placeholderStyle: TextStyle = this.placeholderStyle,
        padding: PaddingValues = this.padding,
        contentPadding: PaddingValues = this.contentPadding,
        focusOnShow: Boolean = this.focusOnShow,
        alignment: Alignment = this.alignment
    ): DialogInputConfig {
        return DialogInputConfig(
            border = border,
            shape = shape,
            textStyle = textStyle,
            placeholderStyle = placeholderStyle,
            padding = padding,
            contentPadding = contentPadding,
            focusOnShow = focusOnShow,
            alignment = alignment
        )
    }

    override fun toString(): String {
        return "DialogInputConfig(" +
                "border=$border, " +
                "shape=$shape, " +
                "textStyle=$textStyle, " +
                "placeholderStyle=$placeholderStyle, " +
                "padding=$padding, " +
                "contentPadding=$contentPadding, " +
                "focusOnShow=$focusOnShow, " +
                "alignment=$alignment" +
                ")"
    }
}

/**
 * 默认的Dialog输入框配置
 */
fun defaultDialogInputConfig(
    border: BorderStroke = BorderStroke(1.dp, Color.Gray),
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    textStyle: TextStyle = TextStyle.Default.copy(
        platformStyle = PlatformTextStyle(
            includeFontPadding = true
        ),
        color = Color.Black,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.2.sp
    ),
    placeholderStyle: TextStyle = TextStyle.Default.copy(
        platformStyle = PlatformTextStyle(
            includeFontPadding = true
        ),
        color = Color.Black.copy(alpha = 0.5f),
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.2.sp
    ),
    padding: PaddingValues = PaddingValues(start = 15.dp, end = 15.dp, top = 10.dp),
    contentPadding: PaddingValues = PaddingValues(10.dp),
    focusOnShow: Boolean = true,
    alignment: Alignment = Alignment.CenterStart
): DialogInputConfig = DialogInputConfig(
    border = border,
    shape = shape,
    textStyle = textStyle,
    placeholderStyle = placeholderStyle,
    padding = padding,
    contentPadding = contentPadding,
    focusOnShow = focusOnShow,
    alignment = alignment
)

object DialogGlobalConfig {
    var dialogConfig: DialogConfig by mutableStateOf(defaultDialogConfig())
        private set
    var bottomDialogConfig: BottomDialogConfig by mutableStateOf(defaultBottomDialogConfig())
        private set
    var dialogTitleConfig: DialogTitleConfig by mutableStateOf(defaultDialogTitleConfig())
        private set
    var dialogMessageConfig: DialogMessageConfig by mutableStateOf(defaultDialogMessageConfig())
        private set
    var dialogInputConfig: DialogInputConfig by mutableStateOf(defaultDialogInputConfig())
        private set

    /**
     * 更新Dialog默认配置
     */
    fun updateDialogConfig(config: DialogConfig) {
        dialogConfig = config
    }

    /**
     * 更新BottomDialog默认配置
     */
    fun updateBottomDialogConfig(config: BottomDialogConfig) {
        bottomDialogConfig = config
    }

    /**
     * 更新Dialog标题配置
     */
    fun updateTitleConfig(config: DialogTitleConfig) {
        dialogTitleConfig = config
    }

    /**
     * 更新Dialog消息配置
     */
    fun updateMessageConfig(config: DialogMessageConfig) {
        dialogMessageConfig = config
    }

    /**
     * 更新Dialog输入框配置
     */
    fun updateInputConfig(config: DialogInputConfig) {
        dialogInputConfig = config
    }

    fun update(
        dialogConfig: DialogConfig = this.dialogConfig,
        bottomDialogConfig: BottomDialogConfig = this.bottomDialogConfig,
        dialogTitleConfig: DialogTitleConfig = this.dialogTitleConfig,
        dialogMessageConfig: DialogMessageConfig = this.dialogMessageConfig,
        dialogInputConfig: DialogInputConfig = this.dialogInputConfig
    ) {
        this.dialogConfig = dialogConfig
        this.bottomDialogConfig = bottomDialogConfig
        this.dialogTitleConfig = dialogTitleConfig
        this.dialogMessageConfig = dialogMessageConfig
        this.dialogInputConfig = dialogInputConfig
    }
}