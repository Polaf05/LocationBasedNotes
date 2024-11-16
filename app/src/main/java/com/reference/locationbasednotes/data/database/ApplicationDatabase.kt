package com.reference.locationbasednotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reference.locationbasednotes.data.database.dao.NoteDao
import com.reference.locationbasednotes.data.database.dao.NotificationDao
import com.reference.locationbasednotes.data.database.entity.Note
import com.reference.locationbasednotes.data.database.entity.Notification

@Database(entities = [Notification::class, Note::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration() // Destroys old data on schema change
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}