package com.musicgo.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class PlaylistSource {
    LOCAL,
    YTM_SYNCED,
    JIO_SAAVN,
    SPOTIFY
}

@Entity(tableName = "playlists")
data class PlaylistEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String? = null,
    val thumbnailUrl: String? = null,
    val isAutoPlaylist: Boolean = false,
    val isLocalOnly: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val songCount: Int = 0,
    val totalDurationMs: Long = 0,
    val source: PlaylistSource = PlaylistSource.LOCAL
)
