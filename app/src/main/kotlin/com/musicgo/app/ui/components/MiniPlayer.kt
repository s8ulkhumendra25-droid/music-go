package com.musicgo.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
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
import com.musicgo.app.ui.theme.DividerColor
import com.musicgo.app.ui.theme.MiniPlayerBackground
import com.musicgo.app.ui.theme.OnSurface
import com.musicgo.app.ui.theme.Primary
import com.musicgo.app.ui.theme.Surface
import com.musicgo.app.ui.theme.TextSecondary
import com.musicgo.app.ui.theme.AppTypography

@Composable
fun MiniPlayer(
    modifier: Modifier = Modifier,
    title: String = "Song Title",
    artist: String = "Artist Name",
    thumbnailUrl: String? = null,
    isPlaying: Boolean = false,
    onPlayPauseClick: () -> Unit = {},
    onNextClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MiniPlayerBackground)
            .border(width = 1.dp, color = DividerColor, shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Thumbnail
            if (thumbnailUrl != null) {
                AsyncImage(
                    model = thumbnailUrl,
                    contentDescription = "Current Track",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Surface),
                    contentScale = ContentScale.Crop
                )
            } else {
                androidx.compose.foundation.background(
                    color = Surface,
                    shape = RoundedCornerShape(4.dp)
                ).let {
                    Modifier
                        .size(48.dp)
                        .then(it)
                }
            }

            // Song Info
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = AppTypography.titleMedium,
                    color = OnSurface,
                    maxLines = 1
                )
                Text(
                    text = artist,
                    style = AppTypography.bodyMedium,
                    color = TextSecondary,
                    maxLines = 1
                )
            }

            // Controls
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onPlayPauseClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        tint = Primary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                IconButton(
                    onClick = onNextClick,
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = "Next",
                        tint = Primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
