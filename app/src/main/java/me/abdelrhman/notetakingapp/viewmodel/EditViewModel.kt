package me.abdelrhman.notetakingapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import me.abdelrhman.notetakingapp.database.AppRepository
import me.abdelrhman.notetakingapp.database.NoteEntity
import java.util.*
import java.util.concurrent.Executors

/**
 * Created by Abdelrhman
 * on 6/22/18.
 */

class EditViewModel(val app: Application) : AndroidViewModel(app) {


    val liveNote = MutableLiveData<NoteEntity>()
    val repo = AppRepository.getInstance(app)
    val executor = Executors.newSingleThreadExecutor()

    fun loadData(id: Int) {
        executor.execute {
            val note = repo.getNoteById(id)
            liveNote.postValue(note)
        }
    }

    fun saveNote(noteText: String) {
        val note = liveNote.value
        val newNote = note?.copy(text = noteText.trim()) ?: NoteEntity(Date(), noteText.trim())
        repo.insertNote(newNote)
    }

    fun deleteNote() {
        executor.execute {
            val note = liveNote.value
            repo.deleteNote(note!!)
        }
    }

}