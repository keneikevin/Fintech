package com.example.fintech.di

//
//import android.app.Application
//import androidx.room.Room
//import com.example.fintech.db.SchoolDatabase
//import com.example.fintech.domain.RepositoryImpl
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideSchoolDatabase(app: Application): SchoolDatabase {
//        return Room.databaseBuilder(
//            app,
//            SchoolDatabase::class.java,
//            SchoolDatabase.DATABASE_NAME
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideSchoolRepository(db: SchoolDatabase): RepositoryImpl {
//        return RepositoryImpl(db.classDao)
//    }
//
//}