package com.shalatan.doggo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shalatan.doggo.navigation.AppNavigation
import com.shalatan.doggo.ui.theme.DoggoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoggoTheme {
                AppNavigation(activity = this)
            }
        }
    }
}
