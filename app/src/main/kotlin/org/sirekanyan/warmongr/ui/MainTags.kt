package org.sirekanyan.warmongr.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.sirekanyan.warmongr.D
import org.sirekanyan.warmongr.SearchState
import org.sirekanyan.warmongr.TagModel
import org.sirekanyan.warmongr.ext.AdvancedAnimatedVisibility
import org.sirekanyan.warmongr.ui.icons.IconDone

@Composable
fun MainTags(paddings: PaddingValues, searchState: SearchState, tags: List<TagModel>) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(paddings),
        horizontalArrangement = Arrangement.spacedBy(D.tagsSpacing),
    ) {
        tags.forEach { tag ->
            val selected = tag.id == searchState.tag
            TagView(
                tag = tag,
                selected = selected,
                onClick = { searchState.tag = if (selected) null else tag.id }
            )
        }
    }
}

@Composable
private fun TagView(tag: TagModel, selected: Boolean, onClick: () -> Unit) {
    val color = if (selected) MaterialTheme.colors.secondary else MaterialTheme.colors.surface
    val border = if (selected) null else BorderStroke(D.tagBorder, contentColorFor(color))
    Surface(
        shape = D.tagShape,
        color = color,
        border = border,
    ) {
        Row(
            Modifier
                .clickable(onClick = onClick)
                .height(D.tagHeight)
                .padding(horizontal = D.tagPaddings),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AdvancedAnimatedVisibility(selected) {
                Icon(
                    modifier = Modifier.size(D.tagIconSize),
                    imageVector = IconDone,
                    contentDescription = null,
                )
            }
            Text(
                modifier = Modifier.padding(horizontal = D.tagPaddings),
                text = tag.name,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
