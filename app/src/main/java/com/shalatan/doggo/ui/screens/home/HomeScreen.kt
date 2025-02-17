package com.shalatan.doggo.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shalatan.doggo.ui.screens.CustomButton
import com.shalatan.doggo.ui.theme.DoggoTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, navigateToGenerate: () -> Unit, navigateToListView: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomButton(text = "Generate Dogs!") {
            navigateToGenerate.invoke()
        }
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(text = "My Recently Generated Dogs!") {
            navigateToListView.invoke()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DoggoTheme {
        HomeScreen(navigateToGenerate = { }, navigateToListView = { })
    }
}