package com.ahmad.pocketmarket.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ahmad.pocketmarket.R
import com.ahmad.pocketmarket.ui.theme.DsPreviews
import com.ahmad.pocketmarket.ui.theme.PreviewSurface
import com.ahmad.pocketmarket.ui.theme.spacing

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    val description = stringResource(R.string.state_loading)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.xl)
            .semantics { contentDescription = description },
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorState(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
    message: String = stringResource(R.string.state_error_message),
) {
    StateMessage(
        icon = Icons.Default.Warning,
        iconTint = MaterialTheme.colorScheme.error,
        title = stringResource(R.string.state_error_title),
        message = message,
        modifier = modifier,
        action = {
            PmButton(text = stringResource(R.string.action_retry), onClick = onRetry)
        },
    )
}

@Composable
fun EmptyState(
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.state_empty_title),
    message: String = stringResource(R.string.state_empty_message),
) {
    StateMessage(
        icon = Icons.Default.Search,
        iconTint = MaterialTheme.colorScheme.onSurfaceVariant,
        title = title,
        message = message,
        modifier = modifier,
    )
}

@Composable
private fun StateMessage(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    action: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(48.dp),
        )
        Column(
            modifier = Modifier.semantics(mergeDescendants = true) {
                liveRegion = LiveRegionMode.Polite
            },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xs),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )
        }
        action?.invoke()
    }
}

@DsPreviews
@Composable
private fun LoadingStatePreview() {
    PreviewSurface { LoadingState() }
}

@DsPreviews
@Composable
private fun LoadingStateInCardPreview() {
    PreviewSurface { Card { LoadingState() } }
}

@DsPreviews
@Composable
private fun ErrorStatePreview() {
    PreviewSurface { ErrorState(onRetry = {}) }
}

@DsPreviews
@Composable
private fun ErrorStateLongMessagePreview() {
    PreviewSurface {
        ErrorState(
            onRetry = {},
            message = stringResource(R.string.state_error_message).repeat(3),
        )
    }
}

@DsPreviews
@Composable
private fun EmptyStatePreview() {
    PreviewSurface { EmptyState() }
}

@DsPreviews
@Composable
private fun EmptyStateInCardPreview() {
    PreviewSurface { Card { EmptyState() } }
}