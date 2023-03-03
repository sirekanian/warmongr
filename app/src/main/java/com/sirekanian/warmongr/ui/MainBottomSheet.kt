package com.sirekanian.warmongr.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import com.sirekanian.warmongr.D
import com.sirekanian.warmongr.DialogState
import com.sirekanian.warmongr.R
import com.sirekanian.warmongr.ext.createShareIntent
import com.sirekanian.warmongr.ui.icons.IconCopy
import com.sirekanian.warmongr.ui.icons.IconShare

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainBottomSheet(dialogState: DialogState, content: @Composable () -> Unit) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    ModalBottomSheetLayout(
        sheetState = dialogState.bottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier.navigationBarsPadding(),
            ) {
                Text(
                    text = dialogState.title,
                    modifier = Modifier
                        .padding(D.dialogTitlePadding)
                        .alpha(ContentAlpha.medium),
                    style = MaterialTheme.typography.subtitle1
                )
                BottomSheetItem(
                    icon = IconCopy,
                    text = stringResource(R.string.app_copy_menu),
                    onClick = {
                        clipboardManager.setText(AnnotatedString(dialogState.content))
                        dialogState.hide()
                    }
                )
                BottomSheetItem(
                    icon = IconShare,
                    text = stringResource(R.string.app_share_menu),
                    onClick = {
                        context.startActivity(createShareIntent(dialogState.content))
                    }
                )
            }
        },
        content = content,
    )
}

@Composable
private fun BottomSheetItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            modifier = Modifier
                .padding(D.menuIconPadding)
                .alpha(ContentAlpha.medium),
            contentDescription = text,
        )
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = D.menuTextPadding),
            style = MaterialTheme.typography.body1,
        )
    }
}
