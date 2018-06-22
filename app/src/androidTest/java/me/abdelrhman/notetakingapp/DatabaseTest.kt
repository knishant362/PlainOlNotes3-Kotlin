package me.abdelrhman.notetakingapp

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import me.abdelrhman.notetakingapp.database.AppDatabase
import me.abdelrhman.notetakingapp.database.NoteDao

import junit.framework.Assert.assertEquals
import me.abdelrhman.notetakingapp.utils.getNotes

/**
 * Created by Abdelrhman
 * on 6/22/18.
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private var db: AppDatabase? = null
    private var noteDao: NoteDao? = null


    @Before
    fun setup() {
        val ctx = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(ctx, AppDatabase::class.java).build()
        noteDao = db!!.noteDao()
        Log.d(TAG, "setup: Db created")
    }

    @After
    fun closeDb() {
        db!!.close()
        Log.d(TAG, "closeDb: db closed")
    }

    @Test
    fun createAndRetrieveNotes() {
        noteDao!!.insertAll(getNotes())
        val count = noteDao!!.getCount()
        Log.d(TAG, "createAndRetrieveNotes: $count")
        assertEquals(getNotes().size, count)

    }

    companion object {

        private val TAG = "DatabaseTest"
    }

}
