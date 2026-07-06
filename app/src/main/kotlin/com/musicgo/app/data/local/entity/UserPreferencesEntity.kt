package com.musicgo.app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_prefs")
data class UserPreferencesEntity(
    @PrimaryKey
    val key: String,
    val value: String
)
