package com.example.simple_todo.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simple_todo.ui.theme.BlueDark
import com.example.simple_todo.ui.theme.WhiteText

@Composable
fun AppButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  backgroundColor: Color = BlueDark,
  contentColor: Color = WhiteText,
  shape: RoundedCornerShape = RoundedCornerShape(12.dp),
  widthFraction: Float = 0.9f,
  height: Dp = 50.dp,
  fontSize: Float = 16f,
  loading: Boolean = false,
  enabled: Boolean = true
){
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(widthFraction)
            .height(height),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor
        ),
        shape = shape,
        enabled = enabled
    ) {
        if (loading) CircularProgressIndicator(modifier = Modifier.size(24.dp), color = contentColor)
        else Text(text = text, fontSize = fontSize.sp, fontWeight = FontWeight.Medium)
    }
}