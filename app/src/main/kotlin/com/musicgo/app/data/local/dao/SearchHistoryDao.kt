package com.musicgo.app.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.musicgo.app.data.local.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {
    
    @Insert
    suspend fun insertSearch(search: SearchHistoryEntity)
    
    @Delete
    suspend fun deleteSearch(search: SearchHistoryEntity)
    
    @Query("DELETE FROM search_history WHERE id = :id")
    suspend fun deleteSearchById(id: Long)
    
    @Query("DELETE FROM search_history")
    suspend fun clearAll()
    
    @Query("""
        SELECT * FROM search_history 
        ORDER BY searchedAt DESC 
        LIMIT :limit
    """)
    fun getRecentSearches(limit: Int = 20): Flow<List<SearchHistoryEntity>>
    
    @Query("""
        SELECT DISTINCT query FROM search_history 
        ORDER BY searchedAt DESC 
        LIMIT :limit
    """)
    fun getUniqueRecentSearches(limit: Int = 20): Flow<List<String>>
    
    @Query("""
        SELECT * FROM search_history 
        WHERE query LIKE '%' || :prefix || '%'
        ORDER BY searchedAt DESC 
        LIMIT :limit
    """)
    fun searchHistoryByPrefix(prefix: String, limit: Int = 10): Flow<List<SearchHistoryEntity>>
    
    @Query("SELECT COUNT(*) FROM search_history")
    fun getSearchHistoryCount(): Flow<Int>
    
    @Query("""
        DELETE FROM search_history 
        WHERE searchedAt < :timestamp
    """)
    suspend fun clearOldSearches(timestamp: Long)
}
