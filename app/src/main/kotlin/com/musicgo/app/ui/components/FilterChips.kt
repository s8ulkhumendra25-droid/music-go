package com.musicgo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.musicgo.app.ui.theme.ChipBackground
import com.musicgo.app.ui.theme.ChipInactiveBackground
import com.musicgo.app.ui.theme.OnSurface
import com.musicgo.app.ui.theme.TextSecondary
import com.musicgo.app.ui.theme.AppTypography

@Composable
fun FilterChipsRow(
    modifier: Modifier = Modifier,
    chips: List<String> = listOf("All", "Songs", "Videos", "Albums", "Artists", "Community"),
    onChipSelected: (String) -> Unit = {}
) {
    var selectedChip by remember { mutableStateOf(chips.firstOrNull() ?: "All") }

    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp)
    ) {
        items(chips) { chip ->
            FilterChip(
                label = chip,
                isSelected = selectedChip == chip,
                onClick = {
                    selectedChip = chip
                    onChipSelected(chip)
                }
            )
        }
    }
}

@Composable
fun FilterChip(
    label: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .height(36.dp)
            .background(
                color = if (isSelected) ChipBackground else ChipInactiveBackground,
                shape = RoundedCornerShape(18.dp)
            )
            .clip(RoundedCornerShape(18.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = label,
            style = AppTypography.labelLarge,
            color = if (isSelected) OnSurface else TextSecondary
        )
    }
}
