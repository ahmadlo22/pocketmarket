package com.ahmad.pocketmarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ahmad.pocketmarket.ui.components.ComponentCatalog
import com.ahmad.pocketmarket.ui.theme.PocketMarkerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PocketMarkerTheme {
                ComponentCatalog()
            }
        }
    }
}