package com.sirekanian.acf.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
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
import com.sirekanian.acf.D
import com.sirekanian.acf.data.Warmonger
import com.sirekanian.acf.ext.isCyrillicResources

@Composable
fun WarmongerCard(warmonger: Warmonger) {
    var isExpanded by remember { mutableStateOf(false) }
    val surfaceCornerSize by animateDpAsState(
        if (isExpanded) {
            D.cornerSizeSmall
        } else {
            D.cornerSizeMedium
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
        WarmongerCardContent(warmonger, isExpanded) { isExpanded = !isExpanded }
    }
}

@Composable
private fun WarmongerCardContent(warmonger: Warmonger, isExpanded: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(24.dp)
            .padding(PaddingValues())
            .animateContentSize()
    ) {
        Text(
            text = if (isCyrillicResources()) warmonger.cyrillicName else warmonger.name,
            style = MaterialTheme.typography.h6
        )
        Spacer(Modifier.size(12.dp))
        Text(
            text = warmonger.notes,
            modifier = Modifier.alpha(ContentAlpha.medium),
            style = MaterialTheme.typography.body1,
            maxLines = if (isExpanded) Int.MAX_VALUE else 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
