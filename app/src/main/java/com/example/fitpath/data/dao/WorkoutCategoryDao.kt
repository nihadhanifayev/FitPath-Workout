package com.example.fitpath.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fitpath.data.model.WorkoutCategory


@Dao
interface WorkoutCategoryDao {
    @Query("Select * From workout_category")
    suspend fun getCategory():List<WorkoutCategory>
}