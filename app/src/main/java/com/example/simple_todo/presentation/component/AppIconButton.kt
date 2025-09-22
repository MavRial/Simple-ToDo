package com.example.simple_todo.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.simple_todo.ui.theme.BlueDark

@Composable
fun AppIconButton(
    onClick: () -> Unit,
    painter: Painter,
    backGroundColor: Color = BlueDark,
    size: Dp = 48.dp,
    padding: Dp = 12.dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(backGroundColor, CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painter, contentDescription = null, modifier = Modifier.padding(padding))
    }
}