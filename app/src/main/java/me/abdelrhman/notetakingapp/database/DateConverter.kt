package me.abdelrhman.notetakingapp.database

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by Abdelrhman
 * on 6/22/18.
 */
class DateConverter {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp == null) null else Date(timestamp)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return if (date == null) null else date.time
    }
}