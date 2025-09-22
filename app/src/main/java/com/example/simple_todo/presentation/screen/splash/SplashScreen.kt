package com.example.simple_todo.presentation.screen.splash



import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.simple_todo.presentation.component.AppIcon
import com.example.todoapp.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {

    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    val scaleAnim = remember { Animatable(1f) }

    LaunchedEffect(Unit) {

        while (true) {
            scaleAnim.animateTo(1.2f, animationSpec = tween(600, easing = FastOutSlowInEasing))
            scaleAnim.animateTo(1f, animationSpec = tween(600, easing = FastOutSlowInEasing))
        }
    }

    LaunchedEffect(Unit) {
        delay(1500)
        onTimeout()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AppIcon(
            painter = painterResource(id = R.drawable.icontask),
            modifier = Modifier.graphicsLayer {
                rotationZ = rotation
                scaleX = scaleAnim.value
                scaleY = scaleAnim.value
            },
            size = 120.dp
        )
    }
}