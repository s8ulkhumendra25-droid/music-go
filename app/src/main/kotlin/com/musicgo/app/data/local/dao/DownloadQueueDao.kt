package com.musicgo.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.musicgo.app.data.local.entity.DownloadQueueEntity
import com.musicgo.app.data.local.entity.DownloadStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadQueueDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownload(download: DownloadQueueEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloads(downloads: List<DownloadQueueEntity>)
    
    @Update
    suspend fun updateDownload(download: DownloadQueueEntity)
    
    @Delete
    suspend fun deleteDownload(download: DownloadQueueEntity)
    
    @Query("DELETE FROM download_queue WHERE id = :downloadId")
    suspend fun deleteDownloadById(downloadId: String)
    
    @Query("SELECT * FROM download_queue WHERE id = :downloadId")
    suspend fun getDownloadById(downloadId: String): DownloadQueueEntity?
    
    @Query("SELECT * FROM download_queue WHERE id = :downloadId")
    fun getDownloadByIdFlow(downloadId: String): Flow<DownloadQueueEntity?>
    
    @Query("SELECT * FROM download_queue")
    fun getAllDownloads(): Flow<List<DownloadQueueEntity>>
    
    @Query("""
        SELECT * FROM download_queue 
        WHERE status = :status
        ORDER BY queuedAt ASC
    """)
    fun getDownloadsByStatus(status: String = DownloadStatus.PENDING.name): Flow<List<DownloadQueueEntity>>
    
    @Query("""
        SELECT * FROM download_queue 
        WHERE status = :pending OR status = :downloading
        ORDER BY queuedAt ASC
    """)
    fun getActiveDownloads(
        pending: String = DownloadStatus.PENDING.name,
        downloading: String = DownloadStatus.DOWNLOADING.name
    ): Flow<List<DownloadQueueEntity>>
    
    @Query("""
        SELECT * FROM download_queue 
        WHERE status = :pending
        ORDER BY queuedAt ASC 
        LIMIT :limit
    """)
    fun getPendingDownloads(
        limit: Int = 50,
        pending: String = DownloadStatus.PENDING.name
    ): Flow<List<DownloadQueueEntity>>
    
    @Query("""
        UPDATE download_queue 
        SET progress = :progress 
        WHERE id = :downloadId
    """)
    suspend fun updateProgress(downloadId: String, progress: Int)
    
    @Query("""
        UPDATE download_queue 
        SET status = :status, completedAt = :timestamp
        WHERE id = :downloadId
    """)
    suspend fun updateStatus(downloadId: String, status: String, timestamp: Long = System.currentTimeMillis())
    
    @Query("""
        UPDATE download_queue 
        SET status = :status, progress = :progress
        WHERE id = :downloadId
    """)
    suspend fun updateStatusAndProgress(downloadId: String, status: String, progress: Int)
    
    @Query("SELECT COUNT(*) FROM download_queue WHERE status = :status")
    fun getDownloadCountByStatus(status: String = DownloadStatus.COMPLETED.name): Flow<Int>
    
    @Query("DELETE FROM download_queue WHERE status = :completed")
    suspend fun clearCompletedDownloads(completed: String = DownloadStatus.COMPLETED.name)
}
