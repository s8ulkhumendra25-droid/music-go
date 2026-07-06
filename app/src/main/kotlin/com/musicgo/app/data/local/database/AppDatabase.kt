package com.musicgo.app.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.musicgo.app.data.local.converter.RoomTypeConverters
import com.musicgo.app.data.local.dao.AlbumDao
import com.musicgo.app.data.local.dao.ArtistDao
import com.musicgo.app.data.local.dao.DownloadQueueDao
import com.musicgo.app.data.local.dao.PlaylistDao
import com.musicgo.app.data.local.dao.SearchHistoryDao
import com.musicgo.app.data.local.dao.SongDao
import com.musicgo.app.data.local.entity.AlbumEntity
import com.musicgo.app.data.local.entity.ArtistEntity
import com.musicgo.app.data.local.entity.DownloadQueueEntity
import com.musicgo.app.data.local.entity.PlaylistEntity
import com.musicgo.app.data.local.entity.PlaylistSongCrossRef
import com.musicgo.app.data.local.entity.SearchHistoryEntity
import com.musicgo.app.data.local.entity.SongEntity
import com.musicgo.app.data.local.entity.UserPreferencesEntity

@Database(
    entities = [
        SongEntity::class,
        PlaylistEntity::class,
        PlaylistSongCrossRef::class,
        ArtistEntity::class,
        AlbumEntity::class,
        SearchHistoryEntity::class,
        DownloadQueueEntity::class,
        UserPreferencesEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun songDao(): SongDao
    abstract fun playlistDao(): PlaylistDao
    abstract fun artistDao(): ArtistDao
    abstract fun albumDao(): AlbumDao
    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun downloadQueueDao(): DownloadQueueDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "musicgo_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
