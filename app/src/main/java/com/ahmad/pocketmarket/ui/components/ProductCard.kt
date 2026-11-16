package com.ahmad.pocketmarket.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.ahmad.pocketmarket.R
import com.ahmad.pocketmarket.domain.fixtures.ProductFixtures
import com.ahmad.pocketmarket.domain.model.Product
import com.ahmad.pocketmarket.ui.theme.DsPreviews
import com.ahmad.pocketmarket.ui.theme.PreviewSurface
import com.ahmad.pocketmarket.ui.theme.spacing

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = MaterialTheme.spacing
    val priceText = stringResource(R.string.price_format, formatPrice(product.priceCents))
    val ratingText = stringResource(R.string.product_rating, product.rating.toString())

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(spacing.md),
            verticalArrangement = Arrangement.spacedBy(spacing.xs),
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = priceText,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = ratingText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            if (!product.inStock) {
                Text(
                    text = stringResource(R.string.product_out_of_stock),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        }
    }
}

private fun formatPrice(cents: Long): String = "%d.%02d".format(cents / 100, cents % 100)

@DsPreviews
@Composable
private fun ProductCardDefaultPreview() {
    PreviewSurface {
        ProductCard(
            product = ProductFixtures.product().first { it.inStock },
            onClick = {},
        )
    }
}

@DsPreviews
@Composable
private fun ProductCardOutOfStockPreview() {
    PreviewSurface {
        ProductCard(
            product = ProductFixtures.product().first().copy(
                title = "A very long product title that should wrap onto two lines and then be truncated",
                stock = 0,
            ),
            onClick = {},
        )
    }
}