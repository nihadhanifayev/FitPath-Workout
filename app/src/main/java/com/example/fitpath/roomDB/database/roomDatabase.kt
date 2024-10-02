package com.example.fitpath.roomDB.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fitpath.classes.DailyExercise
import com.example.fitpath.classes.Exercise
import com.example.fitpath.classes.Program
import com.example.fitpath.classes.Workout
import com.example.fitpath.classes.WorkoutCategory
import com.example.fitpath.roomDB.dao.DailyExerciseDao
import com.example.fitpath.roomDB.dao.ExerciseDao
import com.example.fitpath.roomDB.dao.ProgramDao
import com.example.fitpath.roomDB.dao.WorkoutCategoryDao
import com.example.fitpath.roomDB.dao.WorkoutDao


@Database(entities = [WorkoutCategory::class,Workout::class,Program::class,Exercise::class,DailyExercise::class], version = 1)
abstract class roomDatabase:RoomDatabase() {
    abstract fun getWorkoutCategoryDao():WorkoutCategoryDao
    abstract fun getWorkoutDao():WorkoutDao
    abstract fun getProgramDao():ProgramDao
    abstract fun getExerciseDao():ExerciseDao
    abstract fun getDailyExerciseDao():DailyExerciseDao


    companion object{
        var INSTANCE:roomDatabase? = null

        fun dataBaseAccess(context:Context):roomDatabase?{
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