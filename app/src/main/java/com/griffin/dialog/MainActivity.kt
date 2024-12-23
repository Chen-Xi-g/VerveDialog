package com.griffin.dialog

import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.griffin.dialog.ui.BottomShowKeyboard
import com.griffin.dialog.ui.DialogSample
import com.griffin.dialog.ui.IconTitleDialog
import com.griffin.dialog.ui.MainComponent
import com.griffin.dialog.ui.TitleDialog
import com.griffin.dialog.ui.theme.VerveDialogTheme
import com.verve.dialog.DialogState
import com.verve.dialog.Input
import com.verve.dialog.Message
import com.verve.dialog.Title
import com.verve.dialog.VerveBottomDialog
import com.verve.dialog.rememberDialogState
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VerveDialogTheme {
                Surface {
                    MainComponent()
                }
            }
        }
    }
}

