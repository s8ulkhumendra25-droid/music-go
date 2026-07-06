package com.musicgo.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.musicgo.app.data.local.entity.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: SongEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<SongEntity>)
    
    @Update
    suspend fun updateSong(song: SongEntity)
    
    @Delete
    suspend fun deleteSong(song: SongEntity)
    
    @Query("DELETE FROM songs WHERE id = :songId")
    suspend fun deleteSongById(songId: String)
    
    @Query("SELECT * FROM songs WHERE id = :songId")
    suspend fun getSongById(songId: String): SongEntity?
    
    @Query("SELECT * FROM songs WHERE id = :songId")
    fun getSongByIdFlow(songId: String): Flow<SongEntity?>
    
    @Query("SELECT * FROM songs")
    fun getAllSongs(): Flow<List<SongEntity>>
    
    @Query("SELECT * FROM songs WHERE isLiked = 1 ORDER BY addedToLibraryAt DESC")
    fun getLikedSongs(): Flow<List<SongEntity>>
    
    @Query("SELECT * FROM songs WHERE isDownloaded = 1 ORDER BY addedToLibraryAt DESC")
    fun getDownloadedSongs(): Flow<List<SongEntity>>
    
    @Query("SELECT * FROM songs WHERE lastPlayedAt IS NOT NULL ORDER BY lastPlayedAt DESC LIMIT :limit")
    fun getRecentlyPlayed(limit: Int = 20): Flow<List<SongEntity>>
    
    @Query("""
        SELECT * FROM songs 
        WHERE title LIKE '%' || :query || '%' 
        OR artistName LIKE '%' || :query || '%' 
        OR albumName LIKE '%' || :query || '%'
        ORDER BY title ASC
    """)
    fun searchSongs(query: String): Flow<List<SongEntity>>
    
    @Query("UPDATE songs SET isLiked = :isLiked WHERE id = :songId")
    suspend fun updateLikeStatus(songId: String, isLiked: Boolean)
    
    @Query("UPDATE songs SET playCount = playCount + 1, lastPlayedAt = :timestamp WHERE id = :songId")
    suspend fun incrementPlayCount(songId: String, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE songs SET isDownloaded = :isDownloaded, localPath = :localPath, downloadQuality = :quality, downloadFormat = :format WHERE id = :songId")
    suspend fun updateDownloadStatus(
        songId: String,
        isDownloaded: Boolean,
        localPath: String?,
        quality: String?,
        format: String?
    )
    
    @Query("SELECT COUNT(*) FROM songs")
    fun getTotalSongCount(): Flow<Int>
    
    @Query("SELECT SUM(durationMs) FROM songs")
    fun getTotalDuration(): Flow<Long?>
    
    @Query("SELECT * FROM songs WHERE source = :source ORDER BY addedToLibraryAt DESC")
    fun getSongsBySource(source: String): Flow<List<SongEntity>>
}
