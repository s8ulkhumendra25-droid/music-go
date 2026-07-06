package com.musicgo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.musicgo.app.ui.theme.OnSurface
import com.musicgo.app.ui.theme.Surface
import com.musicgo.app.ui.theme.TextSecondary
import com.musicgo.app.ui.theme.AppTypography

@Composable
fun PlaylistCard(
    modifier: Modifier = Modifier,
    title: String = "Playlist Title",
    subtitle: String = "Subtitle",
    imageUrl: String? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        // Square Image
        if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Surface),
                contentScale = ContentScale.Crop
            )
        } else {
            // Placeholder
            androidx.compose.foundation.background(
                color = Surface,
                shape = RoundedCornerShape(8.dp)
            ).let {
                Modifier
                    .size(150.dp)
                    .then(it)
            }
        }

        // Title
        Text(
            text = title,
            style = AppTypography.titleMedium,
            color = OnSurface,
            maxLines = 1
        )

        // Subtitle
        Text(
            text = subtitle,
            style = AppTypography.bodyMedium,
            color = TextSecondary,
            maxLines = 1
        )
    }
}
