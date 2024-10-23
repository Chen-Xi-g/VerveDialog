package com.verve.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch
import kotlin.math.min

/**
 * 构建一个Dialog并显示指定内容
 *
 * @param dialogState Dialog状态
 * @param properties Dialog [DialogProperties]Dialog属性设置
 * @param backgroundColor 背景颜色
 * @param shape [Shape]
 * @param border 边框样式
 * @param padding Dialog与屏幕的额外间距
 * @param contentPadding Dialog内容的内边距
 * @param shadowElevation 阴影效果
 * @param tonalElevation 颜色调整
 * @param notContentPadding 取消Dialog内容间距
 * @param autoDismiss 点击PositiveButton时是否自动关闭Dialog
 * @param onDismiss Dialog关闭时的回调
 * @param buttons Dialog的按钮布局
 * @param content Dialog的主体内容
 */
@Composable
fun VerveDialog(
    dialogState: DialogState = rememberDialogState(),
    properties: DialogProperties = DialogGlobalConfig.dialogConfig.properties,
    backgroundColor: Color = DialogGlobalConfig.dialogConfig.backgroundColor,
    shape: Shape = DialogGlobalConfig.dialogConfig.shape,
    border: BorderStroke? = DialogGlobalConfig.dialogConfig.border,
    padding: PaddingValues = DialogGlobalConfig.dialogConfig.padding,
    contentPadding: PaddingValues = DialogGlobalConfig.dialogConfig.contentPadding,
    shadowElevation: Dp = DialogGlobalConfig.dialogConfig.shadowElevation,
    tonalElevation: Dp = DialogGlobalConfig.dialogConfig.tonalElevation,
    notContentPadding: Boolean = DialogGlobalConfig.dialogConfig.noContentPadding,
    autoDismiss: Boolean = DialogGlobalConfig.dialogConfig.autoDismissPositiveButton,
    onDismiss: (DialogState) -> Unit = { it.dismiss() },
    buttons: @Composable DialogButtonLayout.() -> Unit = {},
    content: @Composable DialogScope.() -> Unit
) {
    val dialogScope = remember { DialogScopeImpl(dialogState, autoDismiss) }
    DisposableEffect(dialogState.isVisible) {
        if (!dialogState.isVisible) dialogScope.reset()
        onDispose { }
    }

    BoxWithConstraints {
        val maxHeight = LocalConfiguration.current.screenHeightDp.dp
        val maxHeightPx = with(LocalDensity.current) { maxHeight.toPx().toInt() }
        val maxWidth = LocalConfiguration.current.screenWidthDp.dp

        if (dialogState.isVisible) {
            Dialog(
                properties = properties,
                onDismissRequest = { onDismiss(dialogState) }
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sizeIn(maxHeight = maxHeight, maxWidth = maxWidth)
                        .padding(padding)
                        .wrapContentHeight(),
                    shape = shape,
                    color = backgroundColor,
                    border = border,
                    shadowElevation = shadowElevation,
                    tonalElevation = tonalElevation
                ) {
                    Layout(
                        modifier = Modifier.padding(if (notContentPadding) PaddingValues() else contentPadding),
                        content = {
                            dialogScope.ButtonsLayout(
                                modifier = Modifier.layoutId("buttons"),
                                content = buttons
                            )
                            Column(
                                Modifier.layoutId("content")
                            ) { content(dialogScope) }
                        }
                    ) { measure, constraints ->
                        val buttonsHeight =
                            measure[0].minIntrinsicHeight(constraints.maxWidth)
                        val buttonsPlaceable = measure[0].measure(
                            constraints.copy(maxHeight = buttonsHeight, minHeight = 0)
                        )

                        val contentPlaceable = measure[1].measure(
                            constraints.copy(
                                maxHeight = maxHeightPx - buttonsPlaceable.height,
                                minHeight = 0
                            )
                        )

                        val height =
                            min(maxHeightPx, buttonsPlaceable.height + contentPlaceable.height)

                        return@Layout layout(constraints.maxWidth, height) {
                            contentPlaceable.place(0, 0)
                            buttonsPlaceable.place(0, height - buttonsPlaceable.height)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerveBottomDialog(
    dialogState: DialogState = rememberDialogState(),
    backgroundColor: Color = DialogGlobalConfig.dialogConfig.backgroundColor,
    shape: Shape = DialogGlobalConfig.dialogConfig.shape,
    border: BorderStroke? = DialogGlobalConfig.dialogConfig.border,
    padding: PaddingValues = DialogGlobalConfig.dialogConfig.padding,
    contentPadding: PaddingValues = DialogGlobalConfig.dialogConfig.contentPadding,
    shadowElevation: Dp = DialogGlobalConfig.dialogConfig.shadowElevation,
    tonalElevation: Dp = DialogGlobalConfig.dialogConfig.tonalElevation,
    autoDismiss: Boolean = DialogGlobalConfig.dialogConfig.autoDismissPositiveButton,
    hideDrag: Boolean = DialogGlobalConfig.dialogConfig.bottomHideDrag,
    enableDrag: Boolean = DialogGlobalConfig.dialogConfig.enableDrag,
    onDismiss: (DialogState) -> Unit = { it.dismiss() },
    buttons: @Composable DialogButtonLayout.() -> Unit = {},
    content: @Composable DialogScope.() -> Unit
) {
    val dialogScope = remember { DialogScopeImpl(dialogState, autoDismiss) }
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = dialogState.isVisible,
        confirmValueChange = { newValue ->
            if (enableDrag) true else newValue != SheetValue.Hidden
        })
    val maxHeight = LocalConfiguration.current.screenHeightDp.dp - (padding.calculateTopPadding() * 2)
    val maxHeightPx = with(LocalDensity.current) { maxHeight.toPx().toInt() }

    DisposableEffect(dialogState.isVisible) {
        if (!dialogState.isVisible) dialogScope.reset()
        onDispose { }
    }

    LaunchedEffect(dialogState.isVisible) {
        if (dialogState.isVisible) {
            coroutineScope.launch { sheetState.show() }
        } else {
            dialogScope.reset()
            coroutineScope.launch { sheetState.hide() }
        }
    }

    if (dialogState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            dragHandle = if (hideDrag) {
                null
            } else {
                { BottomSheetDefaults.DragHandle() }
            },
            onDismissRequest = {
                onDismiss(dialogState)
                dialogState.isVisible = false
            },
            content = {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(contentPadding)
                        .wrapContentHeight(),
                    shape = shape,
                    color = backgroundColor,
                    border = border,
                    shadowElevation = shadowElevation,
                    tonalElevation = tonalElevation
                ) {
                    Layout(
                        content = {
                            dialogScope.ButtonsLayout(
                                modifier = Modifier.layoutId("buttons"),
                                content = buttons
                            )
                            Column(Modifier.layoutId("content")) { content(dialogScope) }
                        }
                    ) { measure, constraints ->
                        val buttonsHeight =
                            measure[0].minIntrinsicHeight(constraints.maxWidth)
                        val buttonsPlaceable = measure[0].measure(
                            constraints.copy(maxHeight = buttonsHeight, minHeight = 0)
                        )

                        val contentPlaceable = measure[1].measure(
                            constraints.copy(
                                maxHeight = maxHeightPx - buttonsPlaceable.height,
                                minHeight = 0
                            )
                        )

                        val height =
                            min(maxHeightPx, buttonsPlaceable.height + contentPlaceable.height)

                        return@Layout layout(constraints.maxWidth, height) {
                            contentPlaceable.place(0, 0)
                            buttonsPlaceable.place(0, height - buttonsPlaceable.height)
                        }
                    }
                }
            }
        )
    }
}