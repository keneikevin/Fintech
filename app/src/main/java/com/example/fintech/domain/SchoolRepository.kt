package com.example.fintech.domain
import com.example.fintech.db.entity.Stream
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {

    fun getStreams(): Flow<List<Stream>>

    suspend fun getSteamById(id: Int): Stream?

    suspend fun insertStream(note: Stream)

    suspend fun deleteStudent(note: Stream)
}