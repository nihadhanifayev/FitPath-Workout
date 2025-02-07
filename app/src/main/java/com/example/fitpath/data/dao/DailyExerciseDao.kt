package com.example.fitpath.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fitpath.data.model.DailyExercise


@Dao
interface DailyExerciseDao {

    @Insert
    suspend fun addDailyExercise(dailyExercise: DailyExercise)

    @Query("SELECT * FROM daily_exercise ORDER BY exercise_daily_id DESC LIMIT 1;")
    suspend fun getLastID(): DailyExercise

    @Update
    suspend fun updateDailyExercise(dailyExercise: DailyExercise)

    @Query("SELECT * FROM daily_exercise")
    suspend fun getDailyExercises():List<DailyExercise>

    @Delete
    suspend fun deleteDailyExercise(exercise: DailyExercise)

}