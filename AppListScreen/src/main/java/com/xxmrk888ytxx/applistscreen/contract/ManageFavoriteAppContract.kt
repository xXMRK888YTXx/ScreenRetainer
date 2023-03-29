package com.xxmrk888ytxx.applistscreen.contract

import kotlinx.coroutines.flow.Flow

interface ManageFavoriteAppContract {

    suspend fun addInFavoriteApp(packageName:String)

    suspend fun removeFromFavoriteApp(packageName:String)

    fun getFavoriteAppFlow() : Flow<List<String>>
}