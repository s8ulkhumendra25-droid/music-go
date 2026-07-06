package com.musicgo.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.musicgo.app.data.local.entity.PlaylistEntity
import com.musicgo.app.data.local.entity.PlaylistSongCrossRef
import com.musicgo.app.data.local.entity.SongEntity
import kotlinx.coroutines.flow.Flow

data class PlaylistWithSongs(
    val playlist: PlaylistEntity,
    val songs: List<SongEntity>
)

@Dao
interface PlaylistDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: PlaylistEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylists(playlists: List<PlaylistEntity>)
    
    @Update
    suspend fun updatePlaylist(playlist: PlaylistEntity)
    
    @Delete
    suspend fun deletePlaylist(playlist: PlaylistEntity)
    
    @Query("DELETE FROM playlists WHERE id = :playlistId")
    suspend fun deletePlaylistById(playlistId: String)
    
    @Query("SELECT * FROM playlists WHERE id = :playlistId")
    suspend fun getPlaylistById(playlistId: String): PlaylistEntity?
    
    @Query("SELECT * FROM playlists WHERE id = :playlistId")
    fun getPlaylistByIdFlow(playlistId: String): Flow<PlaylistEntity?>
    
    @Query("SELECT * FROM playlists")
    fun getAllPlaylists(): Flow<List<PlaylistEntity>>
    
    @Query("SELECT * FROM playlists WHERE isAutoPlaylist = 1 ORDER BY name ASC")
    fun getAutoPlaylists(): Flow<List<PlaylistEntity>>
    
    @Query("SELECT * FROM playlists WHERE isAutoPlaylist = 0 ORDER BY createdAt DESC")
    fun getCustomPlaylists(): Flow<List<PlaylistEntity>>
    
    @Transaction
    @Query("SELECT * FROM playlists WHERE id = :playlistId")
    fun getPlaylistWithSongs(playlistId: String): Flow<PlaylistWithSongs?>
    
    @Query("""
        SELECT s.* FROM songs s
        INNER JOIN playlist_songs ps ON s.id = ps.songId
        WHERE ps.playlistId = :playlistId
        ORDER BY ps.position ASC
    """)
    fun getSongsInPlaylist(playlistId: String): Flow<List<SongEntity>>
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSongToPlaylist(crossRef: PlaylistSongCrossRef)
    
    @Query("DELETE FROM playlist_songs WHERE playlistId = :playlistId AND songId = :songId")
    suspend fun removeSongFromPlaylist(playlistId: String, songId: String)
    
    @Query("DELETE FROM playlist_songs WHERE playlistId = :playlistId")
    suspend fun removeAllSongsFromPlaylist(playlistId: String)
    
    @Query("SELECT COUNT(*) FROM playlist_songs WHERE playlistId = :playlistId")
    suspend fun getPlaylistSongCount(playlistId: String): Int
    
    @Query("""
        UPDATE playlist_songs 
        SET position = :position 
        WHERE playlistId = :playlistId AND songId = :songId
    """)
    suspend fun updateSongPosition(playlistId: String, songId: String, position: Int)
    
    @Query("""
        UPDATE playlists 
        SET songCount = (SELECT COUNT(*) FROM playlist_songs WHERE playlistId = :playlistId),
            updatedAt = :timestamp
        WHERE id = :playlistId
    """)
    suspend fun updatePlaylistMetadata(playlistId: String, timestamp: Long = System.currentTimeMillis())
    
    @Query("SELECT * FROM playlists WHERE isLocalOnly = 1 ORDER BY createdAt DESC")
    fun getLocalPlaylists(): Flow<List<PlaylistEntity>>
}
