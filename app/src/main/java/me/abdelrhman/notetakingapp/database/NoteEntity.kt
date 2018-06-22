package me.abdelrhman.notetakingapp.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import java.util.*

/**
 * Created by Abdelrhman
 * on 6/21/18.
 */
@Entity(tableName = "notes")
data class NoteEntity(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val date: Date,
        val text: String) {

    @Ignore
    constructor() : this(0, Date(), "")

    @Ignore
    constructor(data: Date, text: String) : this(0, data, text)

}