package me.abdelrhman.notetakingapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

/**
 * Created by Abdelrhman
 * on 6/22/18.
 */

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(notes: List<NoteEntity>)


    @Delete
    fun deleteNote(note: NoteEntity)


    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): NoteEntity


    @Query("SELECT * FROM notes ORDER BY date DESC")
    fun getAll(): LiveData<List<NoteEntity>>


    @Query("DELETE FROM notes")
    fun deleteAll()

    @Query("SELECT COUNT(*) FROM notes")
    fun getCount(): Int

}