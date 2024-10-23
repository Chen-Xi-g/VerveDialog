package com.griffin.dialog.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.griffin.dialog.DialogIntent
import com.griffin.dialog.MainViewModel
import com.griffin.dialog.R
import com.griffin.dialog.ui.theme.VerveDialogTheme
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
    val entitySingleChoiceDialog = rememberDialogState()
    val stringMultiChoiceDialog = rememberDialogState()
    val entityMultiChoiceDialog = rememberDialogState()
    val bottomTitleDialogState = rememberDialogState()
    val bottomIconTitleDialogState = rememberDialogState()
    val bottomEnablePositiveDialogState = rememberDialogState()
    val bottomMoreButtonDialogState = rememberDialogState()
    val bottomNotKeyboardDialogState = rememberDialogState()
    val bottomShowKeyboardDialogState = rememberDialogState()
    val bottomStringSingleChoiceDialog = rememberDialogState()
    val bottomEntitySingleChoiceDialog = rememberDialogState()
    val bottomStringMultiChoiceDialog = rememberDialogState()
    val bottomEntityMultiChoiceDialog = rememberDialogState()
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

                is DialogIntent.EntitySingleChoiceDialog -> {
                    entitySingleChoiceDialog.isVisible = it.isShow
                }

                is DialogIntent.EntityMultiChoiceDialog -> {
                    entityMultiChoiceDialog.isVisible = it.isShow
                }
                is DialogIntent.StringMultiChoiceDialog -> {
                    stringMultiChoiceDialog.isVisible = it.isShow
                }

                is DialogIntent.BottomEnablePositiveDialog -> {
                    bottomEnablePositiveDialogState.isVisible = it.isShow
                }
                is DialogIntent.BottomEntityMultiChoiceDialog -> {
                    bottomEntityMultiChoiceDialog.isVisible = it.isShow
                }
                is DialogIntent.BottomEntitySingleChoiceDialog -> {
                    bottomEntitySingleChoiceDialog.isVisible = it.isShow
                }
                is DialogIntent.BottomIconTitleDialog -> {
                    bottomIconTitleDialogState.isVisible = it.isShow
                }
                is DialogIntent.BottomMoreButtonDialog -> {
                    bottomMoreButtonDialogState.isVisible = it.isShow
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
                is DialogIntent.BottomTitleDialog -> {
                    bottomTitleDialogState.isVisible = it.isShow
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
    EntitySingleChoiceDialog(state = entitySingleChoiceDialog)
    StringMultiChoiceDialog(state = stringMultiChoiceDialog)
    EntityMultiChoiceDialog(state = entityMultiChoiceDialog)
    BottomTitleDialog(state = bottomTitleDialogState)
    BottomIconTitleDialog(state = bottomIconTitleDialogState)
    BottomEnablePositiveDialog(state = bottomEnablePositiveDialogState)
    BottomMoreButtonDialog(state = bottomMoreButtonDialogState)
    BottomNotKeyboard(state = bottomNotKeyboardDialogState)
    BottomShowKeyboard(state = bottomShowKeyboardDialogState)
    BottomStringSingleChoiceDialog(state = bottomStringSingleChoiceDialog)
    BottomEntitySingleChoiceDialog(state = bottomEntitySingleChoiceDialog)
    BottomStringMultiChoiceDialog(state = bottomStringMultiChoiceDialog)
    BottomEntityMultiChoiceDialog(state = bottomEntityMultiChoiceDialog)
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

            7 -> {
                viewModel.showDialog(DialogIntent.EntitySingleChoiceDialog(true))
            }

            8 ->{
                viewModel.showDialog(DialogIntent.StringMultiChoiceDialog(true))
            }

            9 ->{
                viewModel.showDialog(DialogIntent.EntityMultiChoiceDialog(true))
            }
        }
    }) {
        when (it) {
            0 -> {
                viewModel.showDialog(DialogIntent.BottomTitleDialog(true))
            }

            1 -> {
                viewModel.showDialog(DialogIntent.BottomIconTitleDialog(true))
            }

            2 -> {
                viewModel.showDialog(DialogIntent.BottomEnablePositiveDialog(true))
            }

            3 -> {
                viewModel.showDialog(DialogIntent.BottomMoreButtonDialog(true))
            }

            4 -> {
                viewModel.showDialog(DialogIntent.BottomNotKeyboardDialog(true))
            }

            5 -> {
                viewModel.showDialog(DialogIntent.BottomShowKeyboardDialog(true))
            }

            6 -> {
                viewModel.showDialog(DialogIntent.BottomStringSingleChoiceDialog(true))
            }

            7 -> {
                viewModel.showDialog(DialogIntent.BottomEntitySingleChoiceDialog(true))
            }

            8 ->{
                viewModel.showDialog(DialogIntent.BottomStringMultiChoiceDialog(true))
            }

            9 ->{
                viewModel.showDialog(DialogIntent.BottomEntityMultiChoiceDialog(true))
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DialogList(clickCallback: (position: Int) -> Unit, bottomClickCallback: (position: Int) -> Unit) {
    val defList = mutableListOf("普通弹窗", "标题带Icon弹窗", "PositiveButton不可点击", "多按钮弹窗", "输入框弹窗-不弹出软键盘", "输入框弹窗-弹出软键盘", "字符串单选弹窗", "实体类单选弹窗", "字符串多选弹窗", "实体类多选弹窗")
    val bottomList = mutableListOf("底部弹窗", "底部标题带Icon弹窗", "底部PositiveButton不可点击", "底部多按钮弹窗", "底部输入框弹窗-不弹出软键盘", "底部输入框弹窗-弹出软键盘", "底部字符串单选弹窗", "底部实体类单选弹窗", "底部字符串多选弹窗", "底部实体类多选弹窗")
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
        items(defList.size) {
            Button(
                modifier = Modifier
                    .clickable(onClick = { clickCallback(it) }),
                onClick = { clickCallback(it) },
            ) {
                Text(text = defList[it])
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
        items(bottomList.size) {
            Button(
                modifier = Modifier
                    .clickable(onClick = { bottomClickCallback(it) }),
                onClick = { bottomClickCallback(it) },
            ) {
                Text(text = bottomList[it])
            }
        }
    }
}