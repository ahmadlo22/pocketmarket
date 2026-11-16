package com.ahmad.pocketmarket.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.ahmad.pocketmarket.R
import com.ahmad.pocketmarket.ui.theme.DsPreviews
import com.ahmad.pocketmarket.ui.theme.PreviewSurface
import com.ahmad.pocketmarket.ui.theme.spacing

enum class PmButtonStyle { Primary, Secondary }

@Composable
fun PmButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: PmButtonStyle = PmButtonStyle.Primary,
    enabled: Boolean = true,
    isLoading: Boolean = false,
) {
    val loadingDescription = stringResource(R.string.state_loading)
    val buttonModifier = modifier.semantics {
        if (isLoading) stateDescription = loadingDescription
    }
    val guardedClick: () -> Unit = { if (!isLoading) onClick() }

    val content: @Composable RowScope.() -> Unit = {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                strokeWidth = 2.dp,
                color = LocalContentColor.current,
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.sm))
        }
        Text(text = text)
    }

    when (style) {
        PmButtonStyle.Primary -> Button(
            onClick = guardedClick,
            modifier = buttonModifier,
            enabled = enabled,
            content = content,
        )
        PmButtonStyle.Secondary -> FilledTonalButton(
            onClick = guardedClick,
            modifier = buttonModifier,
            enabled = enabled,
            content = content,
        )
    }
}

@DsPreviews
@Composable
private fun PmButtonDefaultPreview() {
    PreviewSurface {
        Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
            PmButton(text = stringResource(R.string.demo_primary), onClick = {})
            PmButton(
                text = stringResource(R.string.demo_secondary),
                onClick = {},
                style = PmButtonStyle.Secondary,
            )
        }
    }
}

@DsPreviews
@Composable
private fun PmButtonDisabledLoadingPreview() {
    PreviewSurface {
        Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
            PmButton(text = stringResource(R.string.demo_disabled), onClick = {}, enabled = false)
            PmButton(text = stringResource(R.string.state_loading), onClick = {}, isLoading = true)
        }
    }
}