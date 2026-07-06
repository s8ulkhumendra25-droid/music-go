package com.musicgo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
fun ArtistCircleItem(
    modifier: Modifier = Modifier,
    name: String = "Artist Name",
    imageUrl: String? = null
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Circular Image
        if (imageUrl != null) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Surface),
                contentScale = ContentScale.Crop
            )
        } else {
            // Placeholder
            androidx.compose.foundation.background(
                color = Surface,
                shape = CircleShape
            ).let {
                Modifier
                    .size(80.dp)
                    .then(it)
            }
        }

        // Artist Name
        Text(
            text = name,
            style = AppTypography.titleMedium,
            color = OnSurface,
            maxLines = 1
        )
    }
}
