package com.example.fintech.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fintech.presentation.Student

@Entity(tableName = "class")
data class Stream(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "stream")
    val stream: String?,
    @ColumnInfo(name = "students")
    val students: List<Student>?,
)
