package com.griffin.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _dialogIntent = MutableSharedFlow<DialogIntent>()
    val dialogIntent = _dialogIntent.asSharedFlow()

    fun showDialog(intent: DialogIntent) {
        viewModelScope.launch {
            _dialogIntent.emit(intent)
        }
    }

}

sealed class DialogIntent() {
    data class TitleDialog(val isShow: Boolean = false) : DialogIntent()
    data class IconTitleDialog(val isShow: Boolean = false) : DialogIntent()
    data class EnablePositiveDialog(val isShow: Boolean = false) : DialogIntent()
    data class MoreButtonDialog(val isShow: Boolean = false) : DialogIntent()
    data class NotKeyboardDialog(val isShow: Boolean = false) : DialogIntent()
    data class ShowKeyboardDialog(val isShow: Boolean = false) : DialogIntent()
    data class StringSingleChoiceDialog(val isShow: Boolean = false) : DialogIntent()
    data class StringMultiChoiceDialog(val isShow: Boolean = false) : DialogIntent()
    data class BottomDefaultDialog(val isShow: Boolean = false) : DialogIntent()
    data class BottomIconTitleDialog(val isShow: Boolean = false) : DialogIntent()
    data class BottomNotKeyboardDialog(val isShow: Boolean = false) : DialogIntent()
    data class BottomShowKeyboardDialog(val isShow: Boolean = false) : DialogIntent()
    data class BottomStringSingleChoiceDialog(val isShow: Boolean = false) : DialogIntent()
    data class BottomStringMultiChoiceDialog(val isShow: Boolean = false) : DialogIntent()
}