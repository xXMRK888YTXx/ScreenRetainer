package com.xxmrk888ytxx.database.Entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    indices = [
        Index("packageName", unique = true)
    ]
)
internal data class FavoriteAppEntity(
    @PrimaryKey val packageName:String
)
