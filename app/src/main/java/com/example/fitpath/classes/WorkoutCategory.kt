package com.example.fitpath.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "workout_category")
data class WorkoutCategory(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("workoutId") @NotNull var workout_id:Int,
    @ColumnInfo("workoutCategory") @NotNull var category:String) {
}