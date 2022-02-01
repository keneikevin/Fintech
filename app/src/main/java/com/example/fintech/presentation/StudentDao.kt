package com.example.fintech.presentation

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao{
    @Query("SELECT * FROM studentTbl ORDER BY id DESC")
    fun getStudents():LiveData<MutableList<Student>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student:Student)

    @Update
    suspend fun update(student:Student)

    @Delete
    suspend fun delete(student:Student)

    @Query("DELETE FROM studentTbl")
    suspend fun clear()
}