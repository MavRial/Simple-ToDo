package com.example.simple_todo.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.simple_todo.ui.theme.SoftGray


@Composable
fun AppIcon(
    painter: Painter,
    modifier: Modifier = Modifier,
    size: Dp = 120.dp,
    backgroundColor: Color = Color.White,
    padding: Dp = 16.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .background(backgroundColor, CircleShape)
            .border(2.dp, SoftGray, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painter, contentDescription = null, modifier = Modifier.padding(padding))
    }
}