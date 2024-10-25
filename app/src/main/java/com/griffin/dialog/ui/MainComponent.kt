package com.griffin.dialog.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.griffin.dialog.DialogIntent
import com.griffin.dialog.MainViewModel
import com.griffin.dialog.R
import com.verve.dialog.rememberBottomDialogState
import com.verve.dialog.rememberDialogState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainComponent(viewModel: MainViewModel = MainViewModel()) {
    val titleDialogState = rememberDialogState()
    val iconTitleDialogState = rememberDialogState()
    val enablePositiveDialogState = rememberDialogState()
    val moreButtonDialogState = rememberDialogState()
    val notKeyboardDialogState = rememberDialogState()
    val showKeyboardDialogState = rememberDialogState()
    val stringSingleChoiceDialog = rememberDialogState()
    val stringMultiChoiceDialog = rememberDialogState()
    val bottomDefaultDialogState = rememberBottomDialogState()
    val bottomIconTitleDialogState = rememberBottomDialogState()
    val bottomNotKeyboardDialogState = rememberBottomDialogState()
    val bottomShowKeyboardDialogState = rememberBottomDialogState()
    val bottomStringSingleChoiceDialog = rememberBottomDialogState()
    val bottomStringMultiChoiceDialog = rememberBottomDialogState()
    LaunchedEffect(Unit) {
        viewModel.dialogIntent.collectLatest {
            when (it) {
                is DialogIntent.TitleDialog -> {
                    titleDialogState.isVisible = it.isShow
                }

                is DialogIntent.IconTitleDialog -> {
                    iconTitleDialogState.isVisible = it.isShow
                }

                is DialogIntent.EnablePositiveDialog -> {
                    enablePositiveDialogState.isVisible = it.isShow
                }

                is DialogIntent.MoreButtonDialog -> {
                    moreButtonDialogState.isVisible = it.isShow
                }

                is DialogIntent.NotKeyboardDialog -> {
                    notKeyboardDialogState.isVisible = it.isShow
                }
                is DialogIntent.ShowKeyboardDialog -> {
                    showKeyboardDialogState.isVisible = it.isShow
                }

                is DialogIntent.StringSingleChoiceDialog -> {
                    stringSingleChoiceDialog.isVisible = it.isShow
                }

                is DialogIntent.StringMultiChoiceDialog -> {
                    stringMultiChoiceDialog.isVisible = it.isShow
                }
                is DialogIntent.BottomIconTitleDialog -> {
                    bottomIconTitleDialogState.isVisible = it.isShow
                }
                is DialogIntent.BottomNotKeyboardDialog -> {
                    bottomNotKeyboardDialogState.isVisible = it.isShow
                }
                is DialogIntent.BottomShowKeyboardDialog -> {
                    bottomShowKeyboardDialogState.isVisible = it.isShow
                }
                is DialogIntent.BottomStringMultiChoiceDialog -> {
                    bottomStringMultiChoiceDialog.isVisible = it.isShow
                }
                is DialogIntent.BottomStringSingleChoiceDialog -> {
                    bottomStringSingleChoiceDialog.isVisible = it.isShow
                }
                is DialogIntent.BottomDefaultDialog -> {
                    bottomDefaultDialogState.isVisible = it.isShow
                }
            }
        }
    }
    TitleDialog(state = titleDialogState)
    IconTitleDialog(state = iconTitleDialogState)
    EnablePositiveDialog(state = enablePositiveDialogState)
    MoreButtonDialog(state = moreButtonDialogState)
    NotKeyboard(state = notKeyboardDialogState)
    ShowKeyboard(state = showKeyboardDialogState)
    StringSingleChoiceDialog(state = stringSingleChoiceDialog)
    StringMultiChoiceDialog(state = stringMultiChoiceDialog)
    BottomDefaultDialog(state = bottomDefaultDialogState)
    BottomIconTitleDialog(state = bottomIconTitleDialogState)
    BottomNotKeyboard(state = bottomNotKeyboardDialogState)
    BottomShowKeyboard(state = bottomShowKeyboardDialogState)
    BottomStringSingleChoiceDialog(state = bottomStringSingleChoiceDialog)
    BottomStringMultiChoiceDialog(state = bottomStringMultiChoiceDialog)
    DialogSample(viewModel = viewModel)
}

@Composable
fun DialogSample(viewModel: MainViewModel) {
    DialogList({
        when (it) {
            0 -> {
                viewModel.showDialog(DialogIntent.TitleDialog(true))
            }

            1 -> {
                viewModel.showDialog(DialogIntent.IconTitleDialog(true))
            }

            2 -> {
                viewModel.showDialog(DialogIntent.EnablePositiveDialog(true))
            }

            3 -> {
                viewModel.showDialog(DialogIntent.MoreButtonDialog(true))
            }

            4 -> {
                viewModel.showDialog(DialogIntent.NotKeyboardDialog(true))
            }

            5 -> {
                viewModel.showDialog(DialogIntent.ShowKeyboardDialog(true))
            }

            6 -> {
                viewModel.showDialog(DialogIntent.StringSingleChoiceDialog(true))
            }

            7 ->{
                viewModel.showDialog(DialogIntent.StringMultiChoiceDialog(true))
            }
        }
    }) {
        when (it) {
            0 -> {
                viewModel.showDialog(DialogIntent.BottomDefaultDialog(true))
            }

            1 -> {
                viewModel.showDialog(DialogIntent.BottomIconTitleDialog(true))
            }

            2 -> {
                viewModel.showDialog(DialogIntent.BottomNotKeyboardDialog(true))
            }

            3 -> {
                viewModel.showDialog(DialogIntent.BottomShowKeyboardDialog(true))
            }

            4 -> {
                viewModel.showDialog(DialogIntent.BottomStringSingleChoiceDialog(true))
            }

            5 ->{
                viewModel.showDialog(DialogIntent.BottomStringMultiChoiceDialog(true))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun DialogList(clickCallback: (position: Int) -> Unit, bottomClickCallback: (position: Int) -> Unit) {
    val defList = mutableListOf("默认弹窗", "Icon Title", "禁用Positive", "多按钮", "输入框-不显示软键盘", "输入框-显示软键盘", "单选", "多选")
    val bottomList = mutableListOf("默认弹窗", "Icon Title", "输入框-不显示软键盘", "输入框-显示软键盘", "单选框", "多选框")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            Image(
                painter = painterResource(id = R.drawable.ic_header_logo),
                contentDescription = "Logo"
            )
        }
        // 默认
        stickyHeader {
            Text(
                text = "默认",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
                    .padding(10.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        item {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
            ) {
                for ((index, item) in defList.withIndex()) {
                    AssistChip(onClick = { clickCallback(index) }, label = { Text(text = item) })
                }
            }
        }

        // 底部弹窗
        stickyHeader {
            Text(
                text = "底部弹窗",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .border(1.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
                    .padding(10.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
        item {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
            ) {
                for ((index, item) in bottomList.withIndex()) {
                    AssistChip(onClick = { bottomClickCallback(index) }, label = { Text(text = item) })
                }
            }
        }
    }
}