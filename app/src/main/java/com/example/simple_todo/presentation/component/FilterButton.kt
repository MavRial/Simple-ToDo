package com.example.simple_todo.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.simple_todo.ui.theme.BlueDark
import com.example.simple_todo.ui.theme.SoftGray
import com.example.simple_todo.ui.theme.WhiteText

@Composable
fun FilterButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    AppButton(
        text = text,
        onClick = onClick,
        widthFraction = 0.3f,
        height = 36.dp,
        backgroundColor = if (isSelected) BlueDark else SoftGray,
        contentColor = if (isSelected) WhiteText else BlueDark,
        fontSize = 14f
    )
}