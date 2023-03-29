package com.xxmrk888ytxx.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xxmrk888ytxx.database.Dao.FavoriteAppDao
import com.xxmrk888ytxx.database.Entity.FavoriteAppEntity

@Database(
    version = 1,
    entities = [
        FavoriteAppEntity::class
    ]
)
internal abstract class AppDataBase : RoomDatabase() {

    abstract val favoriteAppDao : FavoriteAppDao
}