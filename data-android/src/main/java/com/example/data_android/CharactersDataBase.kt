package com.example.data_android

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data_android.local.CharacterDao
import com.example.data_android.model.MyResponseEntity

@Database(entities = [MyResponseEntity::class], version = 1, exportSchema = false)
@TypeConverters(com.example.data_android.local.TypeConverters::class)
abstract class CharactersDataBase: RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}