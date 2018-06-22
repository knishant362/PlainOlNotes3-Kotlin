@file:Suppress("JoinDeclarationAndAssignment")

package me.abdelrhman.notetakingapp.database

import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import me.abdelrhman.notetakingapp.utils.SingletonHolder
import me.abdelrhman.notetakingapp.utils.getNotes
import java.util.concurrent.Executors

/**
 * Created by Abdelrhman
 * on 6/22/18.
 */
class AppRepository private constructor(context: Context) {


    private var db: AppDatabase
    private val TAG = "AppRepository"
    val notes: LiveData<List<NoteEntity>>
    private val executer = Executors.newSingleThreadExecutor()


    init {
        Log.d(TAG, "init AppRepository")
        db = AppDatabase.getInstance(context)
        notes = getAllNotes()
    }


    fun addSampleData() {
        executer.execute {
            db.noteDao().insertAll(getNotes())
        }
    }

    private fun getAllNotes(): LiveData<List<NoteEntity>> {
        return db.noteDao().getAll()
    }

    fun deleteAllNotes() {
        executer.execute {
            db.noteDao().deleteAll()
        }
    }

    fun getNoteById(id: Int): NoteEntity {
        return db.noteDao().getNoteById(id)
    }

    fun insertNote(newNote: NoteEntity) {
        executer.execute {
            db.noteDao().insertNote(newNote)
        }
    }

    fun deleteNote(note: NoteEntity) {
        db.noteDao().deleteNote(note)
    }

    companion object : SingletonHolder<AppRepository, Context>(::AppRepository)

}
