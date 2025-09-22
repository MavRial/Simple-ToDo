package com.example.simple_todo.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.simple_todo.ui.theme.BlueDark
import com.example.simple_todo.ui.theme.WhiteText

@Composable
fun FloatingAddButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = BlueDark,
        contentColor = WhiteText
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}