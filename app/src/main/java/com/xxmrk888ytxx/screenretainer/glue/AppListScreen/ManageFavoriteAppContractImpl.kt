package com.xxmrk888ytxx.screenretainer.glue.AppListScreen

import com.xxmrk888ytxx.applistscreen.contract.ManageFavoriteAppContract
import com.xxmrk888ytxx.database.FavoriteAppRepository
import com.xxmrk888ytxx.database.models.FavoriteAppModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ManageFavoriteAppContractImpl @Inject constructor(
    private val favoriteAppRepositoryFactory: FavoriteAppRepository
) : ManageFavoriteAppContract {
    override suspend fun addInFavoriteApp(packageName: String) {
        favoriteAppRepositoryFactory.addApp(FavoriteAppModel(packageName))
    }

    override suspend fun removeFromFavoriteApp(packageName: String) {
        favoriteAppRepositoryFactory.removeApp(packageName)
    }

    override fun getFavoriteAppFlow(): Flow<List<String>> {
        return favoriteAppRepositoryFactory.favoriteAppListFlow().map {
           it.map { it.packageName }
        }
    }
}