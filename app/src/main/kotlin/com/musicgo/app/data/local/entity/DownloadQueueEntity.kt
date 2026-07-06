package com.musicgo.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class DownloadStatus {
    PENDING,
    DOWNLOADING,
    PAUSED,
    COMPLETED,
    FAILED
}

@Entity(tableName = "download_queue")
data class DownloadQueueEntity(
    @PrimaryKey
    val id: String,
    val status: DownloadStatus = DownloadStatus.PENDING,
    val progress: Int = 0,
    val quality: String = "320kbps",
    val format: String = "mp3",
    val queuedAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null
)
