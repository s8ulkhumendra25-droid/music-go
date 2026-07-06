package com.musicgo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.musicgo.app.ui.theme.ChipInactiveBackground
import com.musicgo.app.ui.theme.TextSecondary
import com.musicgo.app.ui.theme.AppTypography

@Composable
fun CategoryPills(
    modifier: Modifier = Modifier,
    categories: List<String> = listOf(
        "Podcasts",
        "Romance",
        "Feel good",
        "Workout",
        "Relax"
    )
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp)
    ) {
        items(categories) { category ->
            Text(
                text = category,
                style = AppTypography.labelLarge,
                color = TextSecondary,
                modifier = Modifier
                    .background(
                        color = ChipInactiveBackground,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
