package com.xxmrk888ytxx.screenretainer.DI.modules

import android.content.Context
import com.xxmrk888ytxx.database.FavoriteAppRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class FavoriteAppRepositoryModule {
    @Provides
    fun provideFavoriteAppRepository(context: Context) : FavoriteAppRepository {
        return FavoriteAppRepository.Factory(context).create()
    }
}