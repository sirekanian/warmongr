package com.sirekanian.acf.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.sirekanian.acf.D
import com.sirekanian.acf.SearchState
import com.sirekanian.acf.TagModel
import com.sirekanian.acf.ext.AdvancedAnimatedVisibility
import com.sirekanian.acf.ui.icons.IconDone

@Composable
fun MainTags(searchState: SearchState, tags: List<TagModel>) {
    LazyRow(
        contentPadding = D.tagsPaddings,
        horizontalArrangement = Arrangement.spacedBy(D.tagsSpacing),
    ) {
        items(tags, key = { it.id }) { tag ->
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
