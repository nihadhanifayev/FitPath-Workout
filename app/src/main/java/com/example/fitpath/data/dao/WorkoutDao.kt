package com.example.fitpath.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fitpath.data.model.Workout


@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout WHERE workout_category=:category")
    suspend fun getWorkouts(category:String):List<Workout>

    @Query("SELECT workout_name FROM workout")
    suspend fun getWorkoutsAll():List<String>

    @Query("SELECT workout_image FROM workout WHERE workout_name=:workout_name")
    suspend fun getWorkoutsImage(workout_name:String):List<String>

}