package com.ahmad.pocketmarket.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ahmad.pocketmarket.R
import com.ahmad.pocketmarket.ui.theme.DsPreviews
import com.ahmad.pocketmarket.ui.theme.PreviewSurface
import com.ahmad.pocketmarket.ui.theme.spacing

@Composable
fun PmTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    errorText: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
) {
    val clearDescription = stringResource(R.string.field_clear)

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        singleLine = singleLine,
        label = { Text(text = label) },
        isError = errorText != null,
        supportingText = if (errorText != null) {
            { Text(text = errorText) }
        } else {
            null
        },
        trailingIcon = if (value.isNotEmpty() && enabled) {
            {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = clearDescription,
                    )
                }
            }
        } else {
            null
        },
    )
}

@DsPreviews
@Composable
private fun PmTextFieldDefaultPreview() {
    PreviewSurface {
        Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
            PmTextField(value = "", onValueChange = {}, label = stringResource(R.string.search_hint))
            PmTextField(
                value = stringResource(R.string.demo_phones),
                onValueChange = {},
                label = stringResource(R.string.search_hint),
            )
        }
    }
}

@DsPreviews
@Composable
private fun PmTextFieldErrorDisabledPreview() {
    PreviewSurface {
        Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
            PmTextField(
                value = stringResource(R.string.demo_laptops),
                onValueChange = {},
                label = stringResource(R.string.search_hint),
                errorText = stringResource(R.string.demo_field_error),
            )
            PmTextField(
                value = "",
                onValueChange = {},
                label = stringResource(R.string.search_hint),
                enabled = false,
            )
        }
    }
}