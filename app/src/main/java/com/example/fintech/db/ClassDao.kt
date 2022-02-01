package com.example.fintech.db

import androidx.room.*
import com.example.fintech.db.entity.Stream
import com.example.fintech.presentation.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassDao {
    @Query("SELECT * FROM class")
    fun getClasses(): Flow<List<Stream>>

    @Query("SELECT * FROM class WHERE id = :id")
    suspend fun getStreamById(id: Int): Stream?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStream(stream: Stream)

    @Delete
    suspend fun deleteStudent(student: Student)
}