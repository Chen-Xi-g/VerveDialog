package com.griffin.dialog.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.griffin.dialog.R
import com.verve.dialog.BottomDialogState
import com.verve.dialog.NegativeButton
import com.verve.dialog.PositiveButton
import com.verve.dialog.DialogState
import com.verve.dialog.IconTitle
import com.verve.dialog.Input
import com.verve.dialog.Message
import com.verve.dialog.MultiChoice
import com.verve.dialog.MultiChoiceString
import com.verve.dialog.SingleChoice
import com.verve.dialog.SingleChoiceString
import com.verve.dialog.Title
import com.verve.dialog.VerveBottomDialog
import com.verve.dialog.VerveDialog

private const val TAG = "VerveDialog"

/**
 * 普通弹窗
 */
@Composable
fun TitleDialog(state: DialogState) {
    VerveDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "这是一个普通的Dialog弹窗")
    }
}

/**
 * 标题带Icon弹窗
 */
@Composable
fun IconTitleDialog(state: DialogState) {
    VerveDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        IconTitle(text = "提示") {
            Image(
                painter = painterResource(id = R.drawable.ic_dialog_icon),
                modifier = Modifier.size(38.dp),
                contentDescription = "Tint"
            )
        }
        Message(text = "这是一个普通的Dialog弹窗，用于提示简单的文本内容。")
    }
}

/**
 * PositiveButton不可点击弹窗
 */
@Composable
fun EnablePositiveDialog(state: DialogState) {
    VerveDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        PositiveButtonState(isValid = false)
        Title(text = "提示")
        Message(text = "调用`PositiveButtonState`设置`PositiveButton`是否可以点击")
    }
}

/**
 * 多Button弹窗
 */
@Composable
fun MoreButtonDialog(state: DialogState) {
    VerveDialog(
        dialogState = state,
        buttons = {
            PositiveButton(
                "上传",
                textStyle = TextStyle.Default.copy(color = MaterialTheme.colorScheme.error)
            )
            NegativeButton(
                "不同意",
                textStyle = TextStyle.Default.copy(color = MaterialTheme.colorScheme.inversePrimary)
            )
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "设置多个按钮")
    }
}

/**
 * 输入框弹窗
 */
@Composable
fun NotKeyboard(state: DialogState) {
    val context = LocalContext.current
    VerveDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示输入框的Dialog，不弹出软键盘。")
        Input(placeholder = "请输入内容", focusOnShow = false) {
            Toast.makeText(context, "输入的内容是：$it", Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * 输入框弹窗
 */
@Composable
fun ShowKeyboard(state: DialogState) {
    val context = LocalContext.current
    VerveDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示输入框的Dialog，并且弹出软键盘输入。")
        Input(placeholder = "请输入内容") {
            Toast.makeText(context, "输入的内容是：$it", Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * 字符串单选弹窗
 */
@Composable
fun StringSingleChoiceDialog(state: DialogState) {
    val list = listOf("选项1", "选项2", "选项3", "选项4")
    VerveDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示单选Dialog，未选择时Positive不可点击。")
        Spacer(modifier = Modifier.height(15.dp))
        // String 集合单选
        SingleChoiceString(list = list) { index, item ->
            Log.d(TAG, "选择索引：$index, 选择内容：$item")
        }
    }
}

/**
 * 字符串多选弹窗
 */
@Composable
fun StringMultiChoiceDialog(state: DialogState) {
    val context = LocalContext.current
    val list = listOf("选项1", "选项2", "选项3", "选项4")
    VerveDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示多选Dialog，设置不可选中规则。")
        Spacer(modifier = Modifier.height(15.dp))
        MultiChoiceString(list = list, isEnabled = { index, item -> index % 2 == 0 }) { _, items ->
            Toast.makeText(context, "选择的内容是：$items", Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * 普通弹窗
 */
@Composable
fun BottomDefaultDialog(state: BottomDialogState) {
    VerveBottomDialog(
        dialogState = state
    ) {
        Title(text = "隐私政策")
        Message(res = R.string.privacy_policy_content)
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
}

/**
 * 标题带Icon弹窗
 */
@Composable
fun BottomIconTitleDialog(state: BottomDialogState) {
    VerveBottomDialog(
        dialogState = state
    ) {
        IconTitle(text = "隐私政策") {
            Image(
                painter = painterResource(id = R.drawable.ic_dialog_icon),
                modifier = Modifier.size(38.dp),
                contentDescription = "Tint"
            )
        }
        Message(res = R.string.privacy_policy_content)
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
}

/**
 * 输入框弹窗
 */
@Composable
fun BottomNotKeyboard(state: BottomDialogState) {
    val context = LocalContext.current
    VerveBottomDialog(
        dialogState = state
    ) {
        Title(text = "提示")
        Message(text = "显示输入框的Dialog，不弹出软键盘。")
        Input(placeholder = "请输入内容", focusOnShow = false) {
            Toast.makeText(context, "输入的内容是：$it", Toast.LENGTH_SHORT).show()
        }
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
}

/**
 * 输入框弹窗
 */
@Composable
fun BottomShowKeyboard(state: BottomDialogState) {
    val context = LocalContext.current
    VerveBottomDialog(
        dialogState = state
    ) {
        Title(text = "提示")
        Message(text = "显示输入框的Dialog，并且弹出软键盘输入。")
        Input(placeholder = "请输入内容") {
            Toast.makeText(context, "输入的内容是：$it", Toast.LENGTH_SHORT).show()
        }
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
}

/**
 * 字符串单选弹窗
 */
@Composable
fun BottomStringSingleChoiceDialog(state: BottomDialogState) {
    val context = LocalContext.current
    val list = listOf("选项1", "选项2", "选项3", "选项4")
    VerveBottomDialog(
        dialogState = state
    ) {
        Title(text = "提示")
        Message(text = "显示单选Dialog，未选择时Positive不可点击。")
        Spacer(modifier = Modifier.height(15.dp))
        SingleChoiceString(list = list) { _, item ->
            Toast.makeText(context, "选择的内容是：$item", Toast.LENGTH_SHORT).show()
        }
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
}

/**
 * 字符串多选弹窗
 */
@Composable
fun BottomStringMultiChoiceDialog(state: BottomDialogState) {
    val context = LocalContext.current
    val list = listOf("选项1", "选项2", "选项3", "选项4")
    VerveBottomDialog(
        dialogState = state
    ) {
        Title(text = "提示")
        Message(text = "显示多选Dialog，设置不可选中规则。")
        Spacer(modifier = Modifier.height(15.dp))
        MultiChoiceString(list = list, isEnabled = { index, item -> index % 2 == 0 }) { indices, items ->
            Log.d(TAG,"选择索引：$indices, 选择内容：$items")
        }
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
}