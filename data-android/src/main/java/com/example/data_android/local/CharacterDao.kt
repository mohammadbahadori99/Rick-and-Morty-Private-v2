package com.example.data_android.local

import androidx.paging.PagingSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.data_android.model.MyResponseEntity


@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(list: List<MyResponseEntity>)

    @Query("SELECT * FROM characters")
    fun getAllCharacters(): PagingSource<Int, MyResponseEntity>

    @Query("DELETE FROM characters")
    suspend fun clearAll()

    @Query("SELECT id FROM characters")
    suspend fun getRecordsCount(): List<Int>


}