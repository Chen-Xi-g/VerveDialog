package com.verve.dialog

import androidx.annotation.StringRes
import androidx.compose.animation.core.AnimationConstants
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Dialog 标题
 * @param text 文本（字符串）
 * @param res 文本（字符串资源）
 * @param color 文本颜色
 * @param style 文本样式
 * @param padding 标题间距
 * @param alignment 对齐方式
 */
@Composable
fun DialogScope.Title(
    text: String? = null,
    @StringRes res: Int? = null,
    color: Color = DialogGlobalConfig.dialogTitleConfig.color,
    style: TextStyle = DialogGlobalConfig.dialogTitleConfig.style,
    padding: PaddingValues = DialogGlobalConfig.dialogTitleConfig.padding,
    alignment: Alignment.Horizontal = DialogGlobalConfig.dialogTitleConfig.alignment
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

/**
 * Dialog 带Icon标题
 * @param text 文本（字符串）
 * @param res 文本（字符串资源）
 * @param color 文本颜色
 * @param style 文本样式
 * @param padding 标题间距
 * @param arrangement 对齐方式
 * @param icon 标题前的图标
 */
@Composable
fun DialogScope.IconTitle(
    text: String? = null,
    @StringRes res: Int? = null,
    color: Color = DialogGlobalConfig.dialogTitleConfig.color,
    style: TextStyle = DialogGlobalConfig.dialogTitleConfig.style,
    padding: PaddingValues = DialogGlobalConfig.dialogTitleConfig.padding,
    arrangement: Arrangement.Horizontal = DialogGlobalConfig.dialogTitleConfig.iconArrangement,
    icon: @Composable () -> Unit = {}
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

/**
 * Dialog 消息
 * @param text 文本（来自字符串字面量）
 * @param res 文本（来自字符串资源）
 * @param color 文本颜色
 * @param style 文本样式
 * @param padding 消息间距
 * @param alignment 对齐方式
 */
@Composable
fun DialogScope.Message(
    text: String? = null,
    @StringRes res: Int? = null,
    color: Color = DialogGlobalConfig.dialogMessageConfig.color,
    style: TextStyle = DialogGlobalConfig.dialogMessageConfig.style,
    padding: PaddingValues = DialogGlobalConfig.dialogMessageConfig.padding,
    alignment: Alignment.Horizontal = DialogGlobalConfig.dialogMessageConfig.alignment
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

/**
 * Dialog 输入框
 *
 * @param placeholder 占位符
 * @param prefill 预填充文本
 * @param border 边框
 * @param padding 与Dialog的间距
 * @param contentPadding 输入框内部间距
 * @param waitForPositiveButton 等待点击确定按钮后返回输入文本
 * @param focusRequester 输入框焦点请求
 * @param focusOnShow 是否在显示时显示软键盘
 * @param alignment 对齐方式
 * @param isTextValid 输入文本校验，校验未通过时禁用确定按钮
 * @param onInput 输入回调
 */
@Composable
fun DialogScope.Input(
    placeholder: String = "",
    prefill: String = "",
    textStyle: TextStyle = DialogGlobalConfig.dialogInputConfig.textStyle,
    placeholderStyle: TextStyle = DialogGlobalConfig.dialogInputConfig.placeholderStyle,
    border: BorderStroke = DialogGlobalConfig.dialogInputConfig.border,
    shape: RoundedCornerShape = DialogGlobalConfig.dialogInputConfig.shape,
    padding: PaddingValues = DialogGlobalConfig.dialogInputConfig.padding,
    contentPadding: PaddingValues = DialogGlobalConfig.dialogInputConfig.contentPadding,
    waitForPositiveButton: Boolean = true,
    focusRequester: FocusRequester = FocusRequester(),
    focusOnShow: Boolean = DialogGlobalConfig.dialogInputConfig.focusOnShow,
    alignment: Alignment = DialogGlobalConfig.dialogInputConfig.alignment,
    isTextValid: (String) -> Boolean = { true },
    onInput: (String) -> Unit = {}
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

    // 自定义输入框
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

/**
 * Dialog 列表
 *
 * @param modifier 修饰符
 * @param state 列表状态
 * @param list 列表
 * @param closeOnClick 是否点击后关闭
 * @param onClick 点击回调
 * @param isEnabled 判断指定Item是否可以点击
 * @param item 列表项
 */
@Composable
internal fun <T> DialogScope.DialogListItems(
    modifier: Modifier = Modifier,
    list: List<T>,
    state: LazyListState = rememberLazyListState(),
    closeOnClick: Boolean = true,
    onClick: (index: Int, item: T) -> Unit = { _, _ -> },
    isEnabled: (index: Int, item: T) -> Boolean = { _, _ -> true },
    item: @Composable (index: Int, T) -> Unit
) {
    BoxWithConstraints {
        LazyColumn(
            modifier = modifier,
            state = state
        ) {
            itemsIndexed(list) { index, it ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = {
                                if (closeOnClick) {
                                    this@DialogListItems.state.dismiss()
                                }
                                onClick(index, it)
                            },
                            enabled = isEnabled(index, it)
                        )
                ) {
                    item(index, it)
                }
            }
        }
    }
}

/**
 * Dialog 字符串类型单选
 *
 * @param list 列表
 * @param state 列表状态
 * @param radioButtonColors 单选按钮颜色
 * @param choiceTextStyle 选择文本样式
 * @param isEnabled 指定Item是否可点击
 * @param initialSelection 初始化选择的Item
 * @param waitForPositiveButton 是否点击确定按钮后返回选择结果
 * @param onChoiceChange 选择回调
 */
@Composable
fun DialogScope.SingleChoiceString(
    list: List<String>,
    state: LazyListState = rememberLazyListState(),
    radioButtonColors: RadioButtonColors = RadioButtonDefaults.colors(),
    choiceTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    isEnabled: (index: Int, item: String) -> Boolean = { _, _ -> true },
    initialSelection: Int = -1,
    waitForPositiveButton: Boolean = true,
    onChoiceChange: (index: Int, item: String) -> Unit
) {
    SingleChoice(
        list = list,
        state = state,
        isEnabled = isEnabled,
        initialSelection = initialSelection,
        waitForPositiveButton = waitForPositiveButton,
        radioButton = { _, _, selected, enabled, onClick ->
            RadioButton(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                colors = radioButtonColors
            )
        },
        choiceText = { _, item, enabled ->
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

/**
 * Dialog 泛型类型单选
 *
 * @param list 列表
 * @param state 列表状态
 * @param isEnabled 指定Item是否可点击
 * @param initialSelection 初始化选择的Item
 * @param waitForPositiveButton 是否点击确定按钮后返回选择结果
 * @param radioButton 单选按钮布局
 * @param choiceText 选择文本布局
 * @param onChoiceChange 选择回调
 */
@Composable
fun <T> DialogScope.SingleChoice(
    list: List<T>,
    state: LazyListState = rememberLazyListState(),
    isEnabled: (index: Int, item: T) -> Boolean = { _, _ -> true },
    initialSelection: Int = -1,
    waitForPositiveButton: Boolean = true,
    radioButton: @Composable (index: Int, item: T, selected: Boolean, enabled: Boolean, onClick: () -> Unit) -> Unit,
    choiceText: @Composable (index: Int, item: T, enabled: Boolean) -> Unit,
    onChoiceChange: (index: Int, item: T) -> Unit
) {
    var selectedItem by remember { mutableIntStateOf(initialSelection) }
    PositiveButtonState(isValid = selectedItem != -1)

    if (waitForPositiveButton) {
        Callback {
            if (selectedItem != -1) {
                onChoiceChange(selectedItem, list[selectedItem])
            }
        }
    }

    val onSelect = { index: Int ->
        if (isEnabled(index, list[index])) {
            selectedItem = index

            if (!waitForPositiveButton) {
                onChoiceChange(selectedItem, list[selectedItem])
            }
        }
    }

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

/**
 * Dialog 单选项布局
 *
 * @param item 单选项
 * @param index 索引
 * @param selected 是否选中
 * @param enabled 是否可点击
 * @param onSelect 选择回调
 * @param choiceText 选择文本布局
 */
@Composable
internal fun <T> SingleChoiceItem(
    item: T,
    index: Int,
    selected: Boolean,
    enabled: Boolean,
    onSelect: (index: Int) -> Unit,
    radioButton: @Composable (index: Int, item: T, selected: Boolean, enabled: Boolean, onClick: () -> Unit) -> Unit,
    choiceText: @Composable (index: Int, item: T, enabled: Boolean) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        radioButton(index, item, selected, enabled) { onSelect(index) }
        choiceText(index, item, enabled)
    }
}

/**
 * Dialog 字符串多选
 *
 * @param list 列表
 * @param state 列表状态
 * @param checkBoxColors 多选框颜色
 * @param checkedTextStyle 选择文本样式
 * @param isEnabled 指定Item是否可点击
 * @param initialSelection 初始化选择的Item索引集合
 * @param waitForPositiveButton 是否点击确定按钮后返回选择结果
 * @param onChoiceChange 选择回调
 */
@Composable
fun DialogScope.MultiChoiceString(
    list: List<String>,
    state: LazyListState = rememberLazyListState(),
    checkBoxColors: CheckboxColors = CheckboxDefaults.colors(),
    checkedTextStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    isEnabled: (index: Int, item: String) -> Boolean = { _, _ -> true },
    initialSelection: Set<Int> = setOf(),
    waitForPositiveButton: Boolean = true,
    onChoiceChange: (indices: Set<Int>, items: Set<String>) -> Unit
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

/**
 * Dialog 泛型类型多选
 *
 * @param list 列表
 * @param state 列表状态
 * @param isEnabled 指定Item是否可点击
 * @param initialSelection 初始化选择的Item索引集合
 * @param waitForPositiveButton 是否点击确定按钮后返回选择结果
 * @param checkBox 多选框布局
 * @param checkedText 选择文本布局
 * @param onChoiceChange 选择回调
 */
@Composable
fun <T> DialogScope.MultiChoice(
    list: List<T>,
    state: LazyListState = rememberLazyListState(),
    isEnabled: (index: Int, item: T) -> Boolean = { _, _ -> true },
    initialSelection: Set<Int> = setOf(),
    waitForPositiveButton: Boolean = true,
    checkBox: @Composable (index: Int, item: T, selected: Boolean, enabled: Boolean, onCheckedChange: ((Boolean) -> Unit)?) -> Unit,
    checkedText: @Composable (index: Int, item: T, enabled: Boolean) -> Unit,
    onChoiceChange: (indices: Set<Int>, items: Set<T>) -> Unit
) {
    var selectedItems by remember { mutableStateOf(initialSelection) }

    if (waitForPositiveButton) {
        Callback { onChoiceChange(selectedItems, selectedItems.map { list[it] }.toSet()) }
    }

    val onChecked = { index: Int ->
        if (isEnabled(index, list[index])) {
            val newSelectedItems = selectedItems.toMutableSet()
            if (index in selectedItems) {
                newSelectedItems.remove(index)
            } else {
                newSelectedItems.add(index)
            }
            selectedItems = newSelectedItems.toSet()

            if (!waitForPositiveButton) {
                onChoiceChange(selectedItems, selectedItems.map { list[it] }.toSet())
            }
        }
    }

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

/**
 * Dialog 多选项布局
 *
 * @param item 多选项
 * @param index 索引
 * @param selected 是否选中
 * @param enabled 是否可点击
 * @param onChecked 选择回调
 * @param checkedText 选择文本布局
 */
@Composable
internal fun <T> MultiChoiceItem(
    item: T,
    index: Int,
    selected: Boolean,
    enabled: Boolean,
    onChecked: (index: Int) -> Unit,
    checkBox: @Composable (index: Int, item: T, selected: Boolean, enabled: Boolean, onCheckedChange: ((Boolean) -> Unit)?) -> Unit,
    checkedText: @Composable (index: Int, item: T, enabled: Boolean) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        checkBox(index, item, selected, enabled) { onChecked(index) }
        checkedText(index, item, enabled)
    }
}


/**
 * Dialog 自定义布局
 */
@Composable
fun DialogScope.CustomView(content: @Composable () -> Unit) {
    content()
}