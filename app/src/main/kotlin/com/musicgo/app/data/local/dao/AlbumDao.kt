package com.musicgo.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.musicgo.app.data.local.entity.AlbumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(album: AlbumEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums: List<AlbumEntity>)
    
    @Update
    suspend fun updateAlbum(album: AlbumEntity)
    
    @Delete
    suspend fun deleteAlbum(album: AlbumEntity)
    
    @Query("DELETE FROM albums WHERE id = :albumId")
    suspend fun deleteAlbumById(albumId: String)
    
    @Query("SELECT * FROM albums WHERE id = :albumId")
    suspend fun getAlbumById(albumId: String): AlbumEntity?
    
    @Query("SELECT * FROM albums WHERE id = :albumId")
    fun getAlbumByIdFlow(albumId: String): Flow<AlbumEntity?>
    
    @Query("SELECT * FROM albums")
    fun getAllAlbums(): Flow<List<AlbumEntity>>
    
    @Query("""
        SELECT * FROM albums 
        WHERE artistName = :artistName
        ORDER BY year DESC, title ASC
    """)
    fun getAlbumsByArtist(artistName: String): Flow<List<AlbumEntity>>
    
    @Query("""
        SELECT * FROM albums 
        WHERE title LIKE '%' || :query || '%' 
        OR artistName LIKE '%' || :query || '%'
        ORDER BY title ASC
    """)
    fun searchAlbums(query: String): Flow<List<AlbumEntity>>
    
    @Query("SELECT COUNT(*) FROM albums")
    fun getTotalAlbumCount(): Flow<Int>
    
    @Query("SELECT * FROM albums WHERE source = :source ORDER BY title ASC")
    fun getAlbumsBySource(source: String): Flow<List<AlbumEntity>>
}
