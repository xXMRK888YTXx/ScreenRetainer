package com.xxmrk888ytxx.database.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xxmrk888ytxx.database.Entity.FavoriteAppEntity
import kotlinx.coroutines.flow.Flow


@Dao
internal interface FavoriteAppDao {

    @Query("SELECT * FROM FavoriteAppEntity")
    fun getAllFavoriteAppFlow() : Flow<FavoriteAppEntity>

    @Insert
    suspend fun insertFavoriteApp(favoriteAppEntity: FavoriteAppEntity)

    @Query("DELETE FROM FavoriteAppEntity WHERE packageName = :packageName")
    suspend fun removeFavoriteAppEntity(packageName: String)
}