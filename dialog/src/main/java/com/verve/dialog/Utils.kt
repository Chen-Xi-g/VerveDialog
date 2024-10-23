package com.verve.dialog

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
internal fun getString(@StringRes res: Int? = null, text: String? = null): String {
    return if (res != null) {
        LocalContext.current.getString(res)
    } else {
        text ?: ""
    }
}

internal fun List<Pair<TextButtonLayoutId, Placeable>>.buttons(type: TextButtonLayoutId) =
    this.filter { it.first == type }.map { it.second }

internal fun Alignment.textAlignment() = when(this){
    Alignment.TopStart -> TextAlign.Start
    Alignment.TopCenter -> TextAlign.Center
    Alignment.TopEnd -> TextAlign.End
    Alignment.CenterStart -> TextAlign.Start
    Alignment.Center -> TextAlign.Center
    Alignment.CenterEnd -> TextAlign.End
    Alignment.BottomStart -> TextAlign.Start
    Alignment.BottomCenter -> TextAlign.Center
    Alignment.BottomEnd -> TextAlign.End
    else -> TextAlign.Start
}