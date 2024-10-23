package com.griffin.dialog.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.griffin.dialog.R
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
    val focusRequester = remember {
        FocusRequester()
    }
    VerveDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示输入框的Dialog，并且弹出软键盘输入。")
        Input(placeholder = "请输入内容", focusRequester = focusRequester, focusOnShow = true) {
            Toast.makeText(context, "输入的内容是：$it", Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * 字符串单选弹窗
 */
@Composable
fun StringSingleChoiceDialog(state: DialogState) {
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
        Message(text = "显示单选Dialog，传递String类型的数据。")
        Spacer(modifier = Modifier.height(15.dp))
        SingleChoiceString(list = list) { _, item ->
            Toast.makeText(context, "选择的内容是：$item", Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * 实体单选类弹窗
 */
@Composable
fun EntitySingleChoiceDialog(state: DialogState) {
    val context = LocalContext.current
    val list = listOf(
        ChoiceEntity(1, "选项1"),
        ChoiceEntity(2, "选项2"),
        ChoiceEntity(3, "选项3"),
        ChoiceEntity(4, "选项4")
    )
    VerveDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示单选Dialog，传递泛型类型的数据。")
        Spacer(modifier = Modifier.height(15.dp))
        SingleChoice(list = list, onChoiceChange = { _, item ->
            Toast.makeText(context, "选择的内容是：$item", Toast.LENGTH_SHORT).show()
        }) { _, item, _ ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ic_dialog_icon), contentDescription = "Icon")
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = item.name)
            }
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
        Message(text = "显示单选Dialog，传递String类型的数据。")
        Spacer(modifier = Modifier.height(15.dp))
        MultiChoiceString(list = list, isEnabled = {index, item -> index % 2 == 0}) { _, items ->
            Toast.makeText(context, "选择的内容是：$items", Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * 实体单选类弹窗
 */
@Composable
fun EntityMultiChoiceDialog(state: DialogState) {
    val context = LocalContext.current
    val list = listOf(
        ChoiceEntity(1, "选项1"),
        ChoiceEntity(2, "选项2"),
        ChoiceEntity(3, "选项3"),
        ChoiceEntity(4, "选项4")
    )
    VerveDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示多选Dialog，传递泛型类型的数据。")
        Spacer(modifier = Modifier.height(15.dp))
        MultiChoice(list = list, isEnabled = {index, item -> index % 2 == 0}, onCheckedChange = { _, items ->
            Toast.makeText(context, "选择的内容是：$items", Toast.LENGTH_SHORT).show()
        }) { _, item, _ ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ic_dialog_icon), contentDescription = "Icon")
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = item.name)
            }
        }
    }
}

/**
 * 普通弹窗
 */
@Composable
fun BottomTitleDialog(state: DialogState) {
    VerveBottomDialog(
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
fun BottomIconTitleDialog(state: DialogState) {
    VerveBottomDialog(
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
fun BottomEnablePositiveDialog(state: DialogState) {
    VerveBottomDialog(
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
fun BottomMoreButtonDialog(state: DialogState) {
    VerveBottomDialog(
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
fun BottomNotKeyboard(state: DialogState) {
    val context = LocalContext.current
    VerveBottomDialog(
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
fun BottomShowKeyboard(state: DialogState) {
    val context = LocalContext.current
    val focusRequester = remember {
        FocusRequester()
    }
    VerveBottomDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示输入框的Dialog，并且弹出软键盘输入。")
        Input(placeholder = "请输入内容", focusRequester = focusRequester, focusOnShow = true) {
            Toast.makeText(context, "输入的内容是：$it", Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * 字符串单选弹窗
 */
@Composable
fun BottomStringSingleChoiceDialog(state: DialogState) {
    val context = LocalContext.current
    val list = listOf("选项1", "选项2", "选项3", "选项4")
    VerveBottomDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示单选Dialog，传递String类型的数据。")
        Spacer(modifier = Modifier.height(15.dp))
        SingleChoiceString(list = list) { _, item ->
            Toast.makeText(context, "选择的内容是：$item", Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * 实体单选类弹窗
 */
@Composable
fun BottomEntitySingleChoiceDialog(state: DialogState) {
    val context = LocalContext.current
    val list = listOf(
        ChoiceEntity(1, "选项1"),
        ChoiceEntity(2, "选项2"),
        ChoiceEntity(3, "选项3"),
        ChoiceEntity(4, "选项4")
    )
    VerveBottomDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示单选Dialog，传递泛型类型的数据。")
        Spacer(modifier = Modifier.height(15.dp))
        SingleChoice(list = list, onChoiceChange = { _, item ->
            Toast.makeText(context, "选择的内容是：$item", Toast.LENGTH_SHORT).show()
        }) { _, item, _ ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ic_dialog_icon), contentDescription = "Icon")
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = item.name)
            }
        }
    }
}

/**
 * 字符串多选弹窗
 */
@Composable
fun BottomStringMultiChoiceDialog(state: DialogState) {
    val context = LocalContext.current
    val list = listOf("选项1", "选项2", "选项3", "选项4")
    VerveBottomDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示单选Dialog，传递String类型的数据。")
        Spacer(modifier = Modifier.height(15.dp))
        MultiChoiceString(list = list, isEnabled = {index, item -> index % 2 == 0}) { _, items ->
            Toast.makeText(context, "选择的内容是：$items", Toast.LENGTH_SHORT).show()
        }
    }
}

/**
 * 实体单选类弹窗
 */
@Composable
fun BottomEntityMultiChoiceDialog(state: DialogState) {
    val context = LocalContext.current
    val list = listOf(
        ChoiceEntity(1, "选项1"),
        ChoiceEntity(2, "选项2"),
        ChoiceEntity(3, "选项3"),
        ChoiceEntity(4, "选项4")
    )
    VerveBottomDialog(
        dialogState = state,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "提示")
        Message(text = "显示多选Dialog，传递泛型类型的数据。")
        Spacer(modifier = Modifier.height(15.dp))
        MultiChoice(list = list, isEnabled = {index, item -> index % 2 == 0}, onCheckedChange = { _, items ->
            Toast.makeText(context, "选择的内容是：$items", Toast.LENGTH_SHORT).show()
        }) { _, item, _ ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.ic_dialog_icon), contentDescription = "Icon")
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = item.name)
            }
        }
    }
}

data class ChoiceEntity(
    val id: Int,
    val name: String
)