package com.verve.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import java.util.Locale

/**
 * 设置Dialog按钮的布局ID
 */
internal sealed class TextButtonLayoutId {

    data object Positive : TextButtonLayoutId()
    data object Negative : TextButtonLayoutId()

}

/**
 * 在对话框底部添加按钮
 *
 * @param content 要显示在对话框中的按钮内容。
 */
@Composable
internal fun DialogScope.ButtonsLayout(
    modifier: Modifier = Modifier,
    content: @Composable DialogButtonLayout.() -> Unit
) {
    val buttonPadding = with(LocalDensity.current) { 10.dp.toPx().toInt() }
    val boxHeight = with(LocalDensity.current) { 50.dp.toPx().toInt() }
    val buttonHeight = with(LocalDensity.current) { 35.dp.toPx().toInt() }
    val vPadding = with(LocalDensity.current) { 10.dp.toPx().toInt() }

    Layout(
        { content(buttonLayout) },
        modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        { measure, constraints ->

            if (measure.isEmpty()) {
                return@Layout layout(0, 0) {}
            }

            val placeable = measure.map {
                (it.layoutId as TextButtonLayoutId) to it.measure(
                    constraints.copy(minWidth = 0, maxHeight = buttonHeight)
                )
            }
            val totalWidth = placeable.sumOf { it.second.width }
            val isColumnLayout = totalWidth > 0.9 * constraints.maxWidth

            val height =
                if (isColumnLayout) {
                    val buttonTotalHeight = placeable.sumOf { it.second.height }
                    val heightPadding = (placeable.size - 1) * buttonPadding
                    buttonTotalHeight + heightPadding + 2 * vPadding
                } else {
                    boxHeight
                }

            layout(constraints.maxWidth, height) {
                var currX = constraints.maxWidth
                var currY = vPadding

                val positiveButtons = placeable.buttons(TextButtonLayoutId.Positive)
                val negativeButtons = placeable.buttons(TextButtonLayoutId.Negative)

                val orderedButtons = positiveButtons + negativeButtons
                orderedButtons.forEach { button ->
                    if (isColumnLayout) {
                        button.place(currX - button.width, currY)
                        currY += button.height + buttonPadding
                    } else {
                        currX -= button.width
                        button.place(currX, currY)
                    }
                }
            }
        }
    )
}

/**
 * 默认的Dialog按钮布局
 */
class DialogButtonLayout(private val dialogScope: DialogScope) {

    /**
     * 向Dialog添加一个PositiveButton
     *
     * @param buttonText 按钮上显示的字符串文本
     * @param textStyle 按钮文本样式
     * @param res 按钮上显示的字符串资源文本
     * @param colors 按钮颜色配置
     * @param preventDismiss 当为 true 时，按下按钮时将阻止Dialog关闭，即使自动关闭被禁用
     * @param onClick 按钮按下时调用的回调
     */
    @Composable
    fun PositiveButton(
        buttonText: String? = null,
        textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
        @StringRes res: Int? = null,
        colors: ButtonColors = ButtonDefaults.textButtonColors(),
        preventDismiss: Boolean = false,
        onClick: () -> Unit = {}
    ) {
        val displayedText = getString(res, buttonText)
        val isButtonEnabled = dialogScope.isPositiveButtonEnabled.values.all { it }
        val focusManager = LocalFocusManager.current

        TextButton(
            onClick = {
                if (dialogScope.autoClose && !preventDismiss) {
                    dialogScope.state.dismiss(focusManager)
                }

                dialogScope.dismissCallback.values.forEach { it() }
                onClick()
            },
            modifier = Modifier.layoutId(TextButtonLayoutId.Positive),
            enabled = isButtonEnabled,
            colors = colors
        ) {
            Text(text = displayedText, style = textStyle)
        }
    }

    /**
     * 向Dialog添加一个取消按钮
     *
     * @param buttonText 按钮上显示的字符串文本
     * @param textStyle 按钮文本样式
     * @param res 按钮上显示的字符串资源文本
     * @param colors 按钮颜色配置
     * @param onClick 按钮按下时调用的回调
     */
    @Composable
    fun NegativeButton(
        buttonText: String? = null,
        textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
        @StringRes res: Int? = null,
        colors: ButtonColors = ButtonDefaults.textButtonColors(),
        onClick: () -> Unit = {}
    ) {
        val displayedText = getString(res, buttonText).uppercase(Locale.ROOT)
        val focusManager = LocalFocusManager.current

        TextButton(
            onClick = {
                if (dialogScope.autoClose) {
                    dialogScope.state.dismiss(focusManager)
                }
                onClick()
            },
            modifier = Modifier.layoutId(TextButtonLayoutId.Negative),
            colors = colors
        ) {
            Text(text = displayedText, style = textStyle)
        }
    }

}
