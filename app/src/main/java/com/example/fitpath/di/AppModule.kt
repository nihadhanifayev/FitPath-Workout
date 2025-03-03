package com.example.fitpath.di

import android.content.Context
import com.example.fitpath.data.dao.DailyExerciseDao
import com.example.fitpath.data.dao.ProgramDao
import com.example.fitpath.data.dao.WorkoutCategoryDao
import com.example.fitpath.data.dao.WorkoutDao
import com.example.fitpath.data.dao.ExerciseDao
import com.example.fitpath.data.dao.ProgramWorkoutsDao
import com.example.fitpath.data.database.roomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideWorkoutCategoryDao(@ApplicationContext context: Context): WorkoutCategoryDao {
        val db = roomDatabase.dataBaseAccess(context)!!
        return db.getWorkoutCategoryDao()
    }
    @Provides
    @Singleton
    fun provideWorkoutDao(@ApplicationContext context: Context): WorkoutDao {
        val db = roomDatabase.dataBaseAccess(context)!!
        return db.getWorkoutDao()
    }
    @Provides
    @Singleton
    fun provideProgramDao(@ApplicationContext context: Context): ProgramDao {
        val db = roomDatabase.dataBaseAccess(context)!!
        return db.getProgramDao()
    }
    @Provides
    @Singleton
    fun provideDailyExerciseDao(@ApplicationContext context: Context): DailyExerciseDao {
        val db = roomDatabase.dataBaseAccess(context)!!
        return db.getDailyExerciseDao()
    }
    @Provides
    @Singleton
    fun provideExerciseDao(@ApplicationContext context: Context): ExerciseDao {
        val db = roomDatabase.dataBaseAccess(context)!!
        return db.getExerciseDao()
    }
    @Provides
    @Singleton
    fun provideProgramWorkoutsDao(@ApplicationContext context: Context): ProgramWorkoutsDao {
        val db = roomDatabase.dataBaseAccess(context)!!
        return db.getProgramWorkoutsDao()
    }
}