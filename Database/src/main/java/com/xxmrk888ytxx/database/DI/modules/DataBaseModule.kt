package com.xxmrk888ytxx.database.DI.modules

import android.content.Context
import androidx.room.Room
import com.xxmrk888ytxx.database.AppDataBase
import com.xxmrk888ytxx.database.DI.DataBaseScope
import com.xxmrk888ytxx.database.Dao.FavoriteAppDao
import dagger.Module
import dagger.Provides

@Module
internal class DataBaseModule {
    @Provides
    @DataBaseScope
    fun provideAppDataBase(context: Context) : AppDataBase {
        return Room.databaseBuilder(context,AppDataBase::class.java,"Database.db").build()
    }

    @Provides
    fun provideFavoriteAppDao(appDataBase: AppDataBase) : FavoriteAppDao {
        return appDataBase.favoriteAppDao
    }
}