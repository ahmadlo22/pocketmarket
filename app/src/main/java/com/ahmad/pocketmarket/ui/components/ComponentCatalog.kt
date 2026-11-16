package com.ahmad.pocketmarket.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import com.ahmad.pocketmarket.R
import com.ahmad.pocketmarket.domain.fixtures.ProductFixtures
import com.ahmad.pocketmarket.ui.theme.DsPreviews
import com.ahmad.pocketmarket.ui.theme.PreviewSurface
import com.ahmad.pocketmarket.ui.theme.spacing

@Composable
fun ComponentCatalog(modifier: Modifier = Modifier) {
    val spacing = MaterialTheme.spacing
    var query by rememberSaveable { mutableStateOf("") }
    var phonesSelected by rememberSaveable { mutableStateOf(true) }
    var laptopsSelected by rememberSaveable { mutableStateOf(false) }
    var isLoading by rememberSaveable { mutableStateOf(false) }
    val inStock = remember { ProductFixtures.product().first { it.inStock } }
    val outOfStock = remember { ProductFixtures.product().first { !it.inStock } }

    Scaffold(modifier = modifier) { padding ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = spacing.md,
                end = spacing.md,
                top = padding.calculateTopPadding() + spacing.md,
                bottom = padding.calculateBottomPadding() + spacing.md,
            ),
            verticalArrangement = Arrangement.spacedBy(spacing.lg),
        ) {
            item {
                Section(stringResource(R.string.catalog_section_buttons)) {
                    PmButton(text = stringResource(R.string.demo_primary), onClick = { isLoading = !isLoading }, isLoading = isLoading)
                    PmButton(text = stringResource(R.string.demo_secondary), onClick = {}, style = PmButtonStyle.Secondary)
                    PmButton(text = stringResource(R.string.demo_disabled), onClick = {}, enabled = false)
                }
            }
            item {
                Section(stringResource(R.string.catalog_section_fields)) {
                    PmTextField(value = query, onValueChange = { query = it }, label = stringResource(R.string.search_hint))
                    PmTextField(
                        value = query,
                        onValueChange = { query = it },
                        label = stringResource(R.string.search_hint),
                        errorText = if (query.isNotEmpty() && query.length < 3) stringResource(R.string.demo_field_error) else null,
                    )
                }
            }
            item {
                Section(stringResource(R.string.catalog_section_chips)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(spacing.sm)) {
                        PmFilterChip(
                            label = stringResource(R.string.demo_phones),
                            selected = phonesSelected,
                            onClick = { phonesSelected = !phonesSelected },
                        )
                        PmFilterChip(
                            label = stringResource(R.string.demo_laptops),
                            selected = laptopsSelected,
                            onClick = { laptopsSelected = !laptopsSelected },
                        )
                    }
                }
            }
            item {
                Section(stringResource(R.string.catalog_section_cards)) {
                    ProductCard(product = inStock, onClick = {})
                    ProductCard(product = outOfStock, onClick = {})
                }
            }
            item {
                Section(stringResource(R.string.catalog_section_states)) {
                    LoadingState()
                    ErrorState(onRetry = {})
                    EmptyState()
                }
            }
        }
    }
}

@Composable
private fun Section(
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sm)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.semantics { heading() },
        )
        content()
    }
}

@DsPreviews
@Composable
private fun ComponentCatalogPreview() {
    PreviewSurface { ComponentCatalog() }
}