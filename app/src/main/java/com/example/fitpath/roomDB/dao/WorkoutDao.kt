package com.example.fitpath.roomDB.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fitpath.classes.Workout


@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workout WHERE workout_category=:category")
    suspend fun getWorkouts(category:String):List<Workout>

}