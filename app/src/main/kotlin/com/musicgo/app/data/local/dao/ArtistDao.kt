package com.musicgo.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.musicgo.app.data.local.entity.ArtistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtistDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: ArtistEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtists(artists: List<ArtistEntity>)
    
    @Update
    suspend fun updateArtist(artist: ArtistEntity)
    
    @Delete
    suspend fun deleteArtist(artist: ArtistEntity)
    
    @Query("DELETE FROM artists WHERE id = :artistId")
    suspend fun deleteArtistById(artistId: String)
    
    @Query("SELECT * FROM artists WHERE id = :artistId")
    suspend fun getArtistById(artistId: String): ArtistEntity?
    
    @Query("SELECT * FROM artists WHERE id = :artistId")
    fun getArtistByIdFlow(artistId: String): Flow<ArtistEntity?>
    
    @Query("SELECT * FROM artists")
    fun getAllArtists(): Flow<List<ArtistEntity>>
    
    @Query("""
        SELECT * FROM artists 
        WHERE name LIKE '%' || :query || '%'
        ORDER BY name ASC
    """)
    fun searchArtists(query: String): Flow<List<ArtistEntity>>
    
    @Query("SELECT COUNT(*) FROM artists")
    fun getTotalArtistCount(): Flow<Int>
    
    @Query("SELECT * FROM artists WHERE source = :source ORDER BY name ASC")
    fun getArtistsBySource(source: String): Flow<List<ArtistEntity>>
}
