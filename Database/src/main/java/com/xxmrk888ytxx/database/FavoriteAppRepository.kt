package com.xxmrk888ytxx.database

import android.content.Context
import com.xxmrk888ytxx.database.DI.DaggerDataBaseComponent
import com.xxmrk888ytxx.database.Dao.FavoriteAppDao
import com.xxmrk888ytxx.database.Entity.FavoriteAppEntity
import com.xxmrk888ytxx.database.models.FavoriteAppModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FavoriteAppRepository private constructor(
    private val favoriteAppDao: FavoriteAppDao
) {

    suspend fun addApp(favoriteAppModel: FavoriteAppModel) = withContext(Dispatchers.IO) {
        favoriteAppDao.insertFavoriteApp(FavoriteAppEntity(favoriteAppModel.packageName))
    }

    fun favoriteAppListFlow() = favoriteAppDao.getAllFavoriteAppFlow().map {
        FavoriteAppModel(it.packageName)
    }

    suspend fun removeApp(packageName:String) = withContext(Dispatchers.IO) {
        favoriteAppDao.removeFavoriteAppEntity(packageName)
    }


    class Factory(private val context: Context) {

        fun create() : FavoriteAppRepository {
            val dao = DaggerDataBaseComponent.factory().create(context).favoriteAppDao

            return FavoriteAppRepository(dao)
        }
    }
}