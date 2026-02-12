package com.example.ramon_gonzalez_ap2_p1.di

import android.content.Context
import androidx.room.Room
import com.example.ramon_gonzalez_ap2_p1.data.db.AppDatabase
import com.example.ramon_gonzalez_ap2_p1.data.repository.CervezaRepositoryImpl
import com.example.ramon_gonzalez_ap2_p1.domain.registro.repository.CervezaRepository
import com.example.ramon_gonzalez_ap2_p1.local.dao.CervezaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "CervezaDb.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCervezaDao(db: AppDatabase): CervezaDao {
        return db.CervezaDao()
    }

    @Provides
    @Singleton
    fun provideCervezaRepository(dao: CervezaDao): CervezaRepository {
        return CervezaRepositoryImpl(dao)
    }
}