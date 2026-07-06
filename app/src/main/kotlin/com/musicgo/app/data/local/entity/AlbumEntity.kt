package com.musicgo.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class AlbumEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val artistName: String,
    val thumbnailUrl: String? = null,
    val year: String? = null,
    val songCount: Int = 0,
    val source: MusicSource = MusicSource.LOCAL
)
