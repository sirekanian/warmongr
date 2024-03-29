package org.sirekanyan.warmongr.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.sirekanyan.warmongr.*

@Composable
fun WarmongerCard(state: MainState, warmonger: WarmongerModel) {
    var isExpanded by remember { mutableStateOf(false) }
    val surfaceCornerSize by animateDpAsState(
        if (isExpanded) {
            D.cardSelectedCornerSize
        } else {
            D.cardDefaultCornerSize
        }
    )
    val surfaceElevation by animateDpAsState(
        if (isExpanded) {
            D.cardSelectedElevation
        } else {
            D.cardDefaultElevation
        }
    )
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(surfaceCornerSize),
        elevation = surfaceElevation
    ) {
        WarmongerCardContent(
            searchState = state.search,
            warmonger = warmonger,
            isExpanded = isExpanded,
            onClick = { isExpanded = !isExpanded },
            onLongClick = { state.dialog.show(warmonger) },
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WarmongerCardContent(
    searchState: SearchState,
    warmonger: WarmongerModel,
    isExpanded: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .combinedClickable(onClick = onClick, onLongClick = onLongClick)
            .padding(vertical = D.cardVerticalPadding)
            .animateContentSize()
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = D.cardHorizontalPadding),
            text = warmonger.title,
            style = MaterialTheme.typography.h6
        )
        Spacer(Modifier.size(12.dp))
        Text(
            text = warmonger.description,
            modifier = Modifier
                .padding(horizontal = D.cardHorizontalPadding)
                .alpha(ContentAlpha.medium),
            style = MaterialTheme.typography.body1,
            maxLines = if (isExpanded) Int.MAX_VALUE else 2,
            overflow = TextOverflow.Ellipsis
        )
        if (isExpanded) {
            MainTags(D.cardTagsPaddings, searchState, warmonger.tags)
        }
    }
}
