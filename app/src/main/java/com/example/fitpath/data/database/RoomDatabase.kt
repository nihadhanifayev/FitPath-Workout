package com.example.fitpath.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitpath.data.model.DailyExercise
import com.example.fitpath.data.model.Exercise
import com.example.fitpath.data.model.Program
import com.example.fitpath.data.model.ProgramWorkouts
import com.example.fitpath.data.model.Workout
import com.example.fitpath.data.model.WorkoutCategory
import com.example.fitpath.data.dao.DailyExerciseDao
import com.example.fitpath.data.dao.ExerciseDao
import com.example.fitpath.data.dao.ProgramDao
import com.example.fitpath.data.dao.ProgramWorkoutsDao
import com.example.fitpath.data.dao.WorkoutCategoryDao
import com.example.fitpath.data.dao.WorkoutDao


@Database(entities = [WorkoutCategory::class, Workout::class, Program::class, Exercise::class, DailyExercise::class, ProgramWorkouts::class], version = 1)
abstract class roomDatabase:RoomDatabase() {
    abstract fun getWorkoutCategoryDao(): WorkoutCategoryDao
    abstract fun getWorkoutDao(): WorkoutDao
    abstract fun getProgramDao(): ProgramDao
    abstract fun getExerciseDao(): ExerciseDao
    abstract fun getDailyExerciseDao(): DailyExerciseDao
    abstract fun getProgramWorkoutsDao(): ProgramWorkoutsDao


    companion object{
        var INSTANCE: roomDatabase? = null

        fun dataBaseAccess(context:Context): roomDatabase?{
            if (INSTANCE == null){
                synchronized(roomDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        roomDatabase::class.java,"fitpath.db")
                        .createFromAsset("fitpath.db")
                        .build()
                }
            }
            return INSTANCE
        }
    }

}