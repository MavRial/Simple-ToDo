package com.example.simple_todo.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simple_todo.domain.model.Task
import com.example.simple_todo.ui.theme.BlueDark
import com.example.simple_todo.ui.theme.BlueSecondary
import com.example.todoapp.R

@Composable
fun TaskCard(
    task: Task,
    onDelete: (Task) -> Unit,
    onEdit: (Task) -> Unit,
    onToggleComplete: (Task) -> Unit,
    modifier: Modifier = Modifier,
    cardBackgroundColor: Color = Color.White,
    titleColor: Color = BlueDark,
    descriptionColor: Color = BlueDark.copy(alpha = 0.7f)
) {
    AppCard(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        contentPadding = 16.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                AppText(
                    text = task.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = titleColor
                )
                AppText(
                    text = task.description,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    color = descriptionColor
                )
            }

            Row {
                // Toggle complete
                AppIconButton(
                    onClick = { onToggleComplete(task) },
                    painter = painterResource(
                        id = if (task.isCompleted) R.drawable.radio else R.drawable.radiobutton
                    ),
                    backGroundColor = if (task.isCompleted) BlueDark.copy(alpha = 0.1f) else BlueSecondary.copy(alpha = 0.1f)
                )

                // Edit
                AppIconButton(
                    onClick = { onEdit(task) },
                    painter = painterResource(id = R.drawable.edit),
                    backGroundColor = BlueDark.copy(alpha = 0.1f)
                )

                // Delete
                AppIconButton(
                    onClick = { onDelete(task) },
                    painter = painterResource(id = R.drawable.bin),
                    backGroundColor = Color.Red.copy(alpha = 0.1f)
                )
            }
        }
    }
}

