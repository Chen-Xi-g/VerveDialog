package com.griffin.dialog.ui

import androidx.compose.runtime.Composable
import com.verve.dialog.DialogState
import com.verve.dialog.Title
import com.verve.dialog.VerveDialog

@Composable
fun TextPicker(state: DialogState) {

    val type = listOf("类型1", "类型2", "类型3", "类型4", "类型5","类型1", "类型2", "类型3", "类型4", "类型5","类型1", "类型2", "类型3", "类型4", "类型5","类型1", "类型2", "类型3", "类型4", "类型5","类型1", "类型2", "类型3", "类型4", "类型5","类型1", "类型2", "类型3", "类型4", "类型5","类型1", "类型2", "类型3", "类型4", "类型5","类型1", "类型2", "类型3", "类型4", "类型5","类型1", "类型2", "类型3", "类型4", "类型5")
    val style = listOf("风格1", "风格2", "风格3", "风格4", "风格5")

    VerveDialog(
        dialogState = state,
        autoDismiss = false,
        buttons = {
            PositiveButton("确定")
            NegativeButton("取消")
        }
    ) {
        Title(text = "请选择类型")
    }
}