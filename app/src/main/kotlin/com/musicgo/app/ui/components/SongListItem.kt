package com.musicgo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.musicgo.app.ui.theme.OnSurface
import com.musicgo.app.ui.theme.OnSurfaceVariant
import com.musicgo.app.ui.theme.Surface
import com.musicgo.app.ui.theme.TextSecondary
import com.musicgo.app.ui.theme.AppTypography

@Composable
fun SongListItem(
    modifier: Modifier = Modifier,
    title: String = "Song Title",
    artist: String = "Artist Name",
    duration: String = "3:45",
    thumbnailUrl: String? = null,
    onMoreClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Thumbnail
        if (thumbnailUrl != null) {
            AsyncImage(
                model = thumbnailUrl,
                contentDescription = "Album Art",
                modifier = Modifier
                    .size(56.dp)
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
                    .size(56.dp)
                    .then(it)
            }
        }

        // Song Info
        Column(
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = AppTypography.titleMedium,
                color = OnSurface,
                maxLines = 1
            )
            Row(
                modifier = Modifier.padding(top = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = artist,
                    style = AppTypography.bodyMedium,
                    color = TextSecondary,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = duration,
                    style = AppTypography.bodyMedium,
                    color = OnSurfaceVariant
                )
            }
        }

        // Menu Button
        IconButton(
            onClick = onMoreClick,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = OnSurfaceVariant
            )
        }
    }
}
