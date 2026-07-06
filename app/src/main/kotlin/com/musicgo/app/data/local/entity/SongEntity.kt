package com.musicgo.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class MusicSource {
    YTM,
    JIO_SAAVN,
    SPOTIFY,
    LOCAL,
    YOUTUBE
}

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val artistName: String,
    val albumName: String,
    val durationMs: Long,
    val thumbnailUrl: String? = null,
    val audioUrl: String? = null,
    val source: MusicSource = MusicSource.LOCAL,
    val isLiked: Boolean = false,
    val isDownloaded: Boolean = false,
    val localPath: String? = null,
    val downloadQuality: String? = null,
    val downloadFormat: String? = null,
    val addedToLibraryAt: Long = System.currentTimeMillis(),
    val lastPlayedAt: Long? = null,
    val playCount: Int = 0,
    val lyrics: String? = null
)
