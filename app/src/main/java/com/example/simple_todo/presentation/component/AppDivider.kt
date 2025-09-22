package com.example.simple_todo.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppDivider(height: Dp = 8.dp, @SuppressLint("ModifierParameter") modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(height))
}