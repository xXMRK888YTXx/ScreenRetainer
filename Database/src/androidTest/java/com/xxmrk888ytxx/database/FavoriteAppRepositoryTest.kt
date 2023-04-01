package com.xxmrk888ytxx.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.xxmrk888ytxx.database.models.FavoriteAppModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before


@RunWith(AndroidJUnit4::class)
internal class FavoriteAppRepositoryTest {

    private lateinit var dataBase:AppDataBase

    lateinit var repo:FavoriteAppRepository


    private val context by lazy {
        ApplicationProvider.getApplicationContext<Context>()
    }



    @Before
    fun init() {
        dataBase = Room.inMemoryDatabaseBuilder(context,AppDataBase::class.java).build()
        repo = FavoriteAppRepository(dataBase.favoriteAppDao)
    }

    @After
    fun clear() {
        dataBase.close()
    }

    @Test
    fun addItemExpectTheyAddInDatabase() = runBlocking {
        val testData = listOf<FavoriteAppModel>(
            FavoriteAppModel("twfgd"),
            FavoriteAppModel("twfgd23452"),
            FavoriteAppModel("twfgd34565"),
        )

        testData.forEach { repo.addApp(it) }

        Assert.assertEquals(testData,repo.favoriteAppListFlow().first())
    }

    @Test
    fun addItemsAndRemoveExpectInDatabaseListWithoutRemovedItem() = runBlocking {
        val testData = listOf<FavoriteAppModel>(
            FavoriteAppModel("twfgd"),
            FavoriteAppModel("twfgd23452"),
            FavoriteAppModel("twfgd34565"),
        )

        val expectedList = listOf<FavoriteAppModel>(
            FavoriteAppModel("twfgd"),
            FavoriteAppModel("twfgd34565"),
        )

        testData.forEach { repo.addApp(it) }

        repo.removeApp("twfgd23452")

        Assert.assertEquals(expectedList,repo.favoriteAppListFlow().first())
    }
}