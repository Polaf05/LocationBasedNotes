package com.reference.locationbasednotes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.reference.locationbasednotes.data.database.entity.Notification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: Notification): Long

    @Query("SELECT * from Notifications ORDER BY id DESC")
    fun loadAllNotifications(): Flow<List<Notification>>

    @Query("UPDATE Notifications SET read = :read WHERE id = :id")
    suspend fun updateRead(id: Int, read: Boolean)

    @Query("SELECT COUNT(read) FROM Notifications WHERE read = :read")
    fun countUnread(read: Boolean): Flow<Int>

    @Query("DELETE FROM Notifications")
    suspend fun clearNotifications()
}
