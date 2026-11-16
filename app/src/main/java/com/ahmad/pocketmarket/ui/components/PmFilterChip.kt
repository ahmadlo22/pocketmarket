package com.ahmad.pocketmarket.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import com.ahmad.pocketmarket.R
import com.ahmad.pocketmarket.ui.theme.DsPreviews
import com.ahmad.pocketmarket.ui.theme.PreviewSurface
import com.ahmad.pocketmarket.ui.theme.spacing

@Composable
fun PmFilterChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val stateText = stringResource(
        if (selected) R.string.chip_selected else R.string.chip_not_selected,
    )

    FilterChip(
        selected = selected,
        onClick = onClick,
        enabled = enabled,
        label = { Text(text = label) },
        modifier = modifier.semantics { stateDescription = stateText },
    )
}

@DsPreviews
@Composable
private fun PmFilterChipDefaultPreview() {
    PreviewSurface {
        Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
            PmFilterChip(label = stringResource(R.string.demo_phones), selected = true, onClick = {})
            PmFilterChip(label = stringResource(R.string.demo_laptops), selected = false, onClick = {})
        }
    }
}

@DsPreviews
@Composable
private fun PmFilterChipDisabledPreview() {
    PreviewSurface {
        Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
            PmFilterChip(label = stringResource(R.string.demo_phones), selected = true, onClick = {}, enabled = false)
            PmFilterChip(label = stringResource(R.string.demo_laptops), selected = false, onClick = {}, enabled = false)
        }
    }
}