package me.abdelrhman.notetakingapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import me.abdelrhman.notetakingapp.utils.SingletonHolder

/**
 * Created by Abdelrhman
 * on 6/22/18.
 */

@Database(entities = [(NoteEntity::class)], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao


    companion object : SingletonHolder<AppDatabase, Context>({
        val DATABASE_NAME = "AppDatabase.db"
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
    })
}
