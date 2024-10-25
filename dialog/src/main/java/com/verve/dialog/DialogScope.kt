package com.verve.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.focus.FocusManager
import java.util.concurrent.atomic.AtomicInteger

/**
 * 创建DialogScope
 *
 * @param visible Dialog是否显示
 */
@Composable
fun rememberDialogState(visible: Boolean = false): DialogState {
    return rememberSaveable(saver = DialogState.Saver()) {
        DialogState(visible)
    }
}

/**
 * 创建BottomDialogScope
 *
 * @param visible Dialog是否显示
 */
@Composable
fun rememberBottomDialogState(visible: Boolean = false): BottomDialogState {
    return rememberSaveable(saver = BottomDialogState.Saver()) {
        BottomDialogState(visible)
    }
}


/**
 * 定义Dialog内容参数的值和函数的接口
 */
interface DialogScope {
    val state: DialogStateCommon
    val buttonLayout: DialogButtonLayout

    /**
     * 对话框关闭回调，使用DisposableEffect
     */
    val dismissCallback: SnapshotStateMap<Int, () -> Unit>

    /**
     * 自定义功能回调
     */
    val customCallback: SnapshotStateMap<Int, () -> Unit>

    /**
     * Positive按钮是否可以点击
     */
    val isPositiveButtonEnabled: SnapshotStateMap<Int, Boolean>

    /**
     * 自动关闭Dialog
     */
    val autoClose: Boolean

    /**
     * 隐藏Dialog，并调用Dialog中组件的回调函数
     */
    fun dismiss()

    /**
     * 重置Dialog的状态
     */
    fun reset()

    /**
     * 设置PositiveButton的Enable状态
     *
     * @param isValid PositiveButton的Enable状态，当为true时，PositiveButton可点击。
     */
    @Composable
    fun PositiveButtonState(isValid: Boolean)

    /**
     * Dialog回调，使用DisposableEffect来实现
     *
     * @param onCallback Dialog触发回调的函数
     */
    @Composable
    fun Callback(onCallback: () -> Unit)

    /**
     * 自定义功能回调
     *
     * @param onCallback Dialog触发回调的函数
     */
    @Composable
    fun CustomCallback(onCallback: () -> Unit)
}

interface DialogStateCommon {
    var isVisible: Boolean
    val isBottomSheet: Boolean

    /**
     * 显示Dialog
     */
    fun show()

    /**
     * 隐藏Dialog
     */
    fun dismiss(focusManager: FocusManager? = null)
}

/**
 * 存储Dialog的状态
 *
 * @param visible Dialog的初始显示状态
 */
class DialogState(visible: Boolean = false) : DialogStateCommon {
    override var isVisible by mutableStateOf(visible)
    override val isBottomSheet: Boolean = false
    override fun show() {
        isVisible = true
    }

    /**
     * 清除FocusManager焦点，然后隐藏Dialog
     *
     * @param focusManager 需要清除的焦点
     */
    override fun dismiss(focusManager: FocusManager?) {
        focusManager?.clearFocus()
        isVisible = false
    }

    companion object {
        fun Saver(): Saver<DialogState, *> = Saver(
            save = { it.isVisible },
            restore = { DialogState(it) }
        )
    }
}

/**
 * 存储底部Dialog的状态
 */
class BottomDialogState(visible: Boolean = false) : DialogStateCommon {
    override var isVisible by mutableStateOf(visible)
    override val isBottomSheet: Boolean = true

    /**
     * 显示Dialog
     */
    override fun show() {
        isVisible = true
    }

    /**
     * 清除FocusManager焦点，然后隐藏Dialog.
     *
     * Bottom隐藏Dialog时需要通过Callback处理，否则没有收起动画。
     *
     * @param focusManager 需要清除的焦点
     */
    override fun dismiss(focusManager: FocusManager?) {
        focusManager?.clearFocus()
    }

    companion object {
        fun Saver(): Saver<BottomDialogState, *> = Saver(
            save = { it.isVisible },
            restore = { BottomDialogState(it) }
        )
    }
}

internal class DialogScopeImpl(
    override val state: DialogStateCommon,
    override val autoClose: Boolean = true
) : DialogScope {
    override val buttonLayout = DialogButtonLayout(this)

    override val dismissCallback = mutableStateMapOf<Int, () -> Unit>()
    private val callbackCounter = AtomicInteger(0)

    override val customCallback = mutableStateMapOf<Int, () -> Unit>()
    private val callbackNotEffectCounter = AtomicInteger(0)

    override val isPositiveButtonEnabled = mutableStateMapOf<Int, Boolean>()
    private val positiveEnabledCounter = AtomicInteger(0)

    /**
     * 隐藏Dialog并调用Dialog中组件的所有回调
     */
    override fun dismiss() {
        state.dismiss()
        dismissCallback.values.forEach { it() }
        customCallback.values.forEach { it() }
    }

    /**
     * 清除Dialog的回调和正按钮启用值及其对应的计数器
     */
    override fun reset() {
        isPositiveButtonEnabled.clear()
        dismissCallback.clear()
        customCallback.clear()
        positiveEnabledCounter.set(0)
        callbackCounter.set(0)
        callbackNotEffectCounter.set(0)
    }

    /**
     * 设置PositiveButton的Enable状态
     *
     * @param isValid 用于初始化列表中索引的布尔值
     */
    @Composable
    override fun PositiveButtonState(isValid: Boolean) {
        val enabledIndex = remember { positiveEnabledCounter.getAndIncrement() }

        DisposableEffect(isValid) {
            isPositiveButtonEnabled[enabledIndex] = isValid
            onDispose {
                isPositiveButtonEnabled[enabledIndex] = true
            }
        }
    }

    /**
     * Dialog回调，使用DisposableEffect
     *
     * @param onCallback 自定义的回调
     */
    @Composable
    override fun Callback(onCallback: () -> Unit) {
        val callbackIndex = rememberSaveable { callbackCounter.getAndIncrement() }

        DisposableEffect(Unit) {
            dismissCallback[callbackIndex] = onCallback
            onDispose {
                dismissCallback[callbackIndex] = {}
            }
        }
    }

    /**
     * 自定义功能回调
     *
     * @param onCallback 自定义的回调
     */
    @Composable
    override fun CustomCallback(onCallback: () -> Unit) {
        val callbackIndex = rememberSaveable { callbackNotEffectCounter.getAndIncrement() }

        customCallback[callbackIndex] = onCallback
    }
}
