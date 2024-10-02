package com.example.fitpath.roomDB.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fitpath.classes.WorkoutCategory
import java.util.Locale.Category


@Dao
interface WorkoutCategoryDao {
    @Query("Select * From workout_category")
    suspend fun getCategory():List<WorkoutCategory>
}