package com.ahmad.pocketmarket.ui.theme

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "Light", showBackground = true)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "Font 1.5x", showBackground = true, fontScale = 1.5f)
@Preview(name = "Arabic RTL", showBackground = true, locale = "ar")
annotation class DsPreviews

@Composable
fun PreviewSurface(content: @Composable () -> Unit) {
    PocketMarkerTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Box(modifier = Modifier.padding(MaterialTheme.spacing.md)) {
                content()
            }
        }
    }
}