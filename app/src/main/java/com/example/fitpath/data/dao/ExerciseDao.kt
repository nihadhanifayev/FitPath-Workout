package com.example.fitpath.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fitpath.data.model.Exercise


@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises WHERE daily_exercise_id=:id ")
    suspend fun getExercises(id:Int):List<Exercise>

    @Insert
    suspend fun addExercise(exercise: Exercise)

}