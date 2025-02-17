package com.shalatan.doggo.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(containerColor = Color(66, 134, 244))
    ) {
        Text(text = text, Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
    }
}

@Preview
@Composable
private fun CustomComponentsPreview() {
    CustomButton(text = "My recently generated dogs!") { }
}