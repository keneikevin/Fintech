package com.example.fintech.domain

import com.example.fintech.db.ClassDao
import com.example.fintech.db.entity.Stream
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
private val dao: ClassDao
):SchoolRepository{
    override fun getStreams(): Flow<List<Stream>> {
       return dao.getClasses()
    }

    override suspend fun getSteamById(id: Int): Stream? {
       return dao.getStreamById(id)
    }

    override suspend fun insertStream(stream: Stream) {
      return dao.insertStream(stream)
    }

    override suspend fun deleteStudent(stream: Stream) {
        TODO("Not yet implemented")
    }

}