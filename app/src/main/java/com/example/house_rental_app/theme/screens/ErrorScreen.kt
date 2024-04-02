package com.example.house_rental_app.theme.screens
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.window.DialogProperties

@Composable
fun ErrorPopup(
    message: String,
    description: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = message,
            fontFamily = FontFamily.Monospace) },
        text = { Text(text = description,
            fontFamily = FontFamily.Monospace) },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text("OK",
                    fontFamily = FontFamily.Monospace)
            }
        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    )
}
