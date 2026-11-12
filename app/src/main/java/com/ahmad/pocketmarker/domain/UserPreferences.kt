package com.ahmad.pocketmarker.domain

enum class ThemeModel{SYSTEM,LIGHT,DARK}
enum class CatalogLayout{LIST,GRID}

data class UserPreferences(
    val themeModel: ThemeModel = ThemeModel.SYSTEM,
    val layout: CatalogLayout = CatalogLayout.GRID,
    val preferredCategorySlug: String? = null,
    val onboardingCompleted: Boolean = false,

    )

