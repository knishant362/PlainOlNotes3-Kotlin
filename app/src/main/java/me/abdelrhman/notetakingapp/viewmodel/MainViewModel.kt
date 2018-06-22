package me.abdelrhman.notetakingapp.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import me.abdelrhman.notetakingapp.database.AppRepository
import me.abdelrhman.notetakingapp.database.NoteEntity

/**
 * Created by Abdelrhman
 * on 6/22/18.
 */
class MainViewModel(val app: Application) : AndroidViewModel(app) {
    fun addSampleData() {
        AppRepository.getInstance(app).addSampleData()
    }

    fun deleteAllNotes() {
        AppRepository.getInstance(app).deleteAllNotes()
    }

    val notes: LiveData<List<NoteEntity>> = AppRepository.getInstance(app).notes


}