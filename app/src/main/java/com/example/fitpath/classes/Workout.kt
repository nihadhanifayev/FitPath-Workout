package com.example.fitpath.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "workout")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("workout_id") @NotNull var workout_id:Int,
    @ColumnInfo("workout_category") @NotNull var workoutCategory:String,
    @ColumnInfo("workout_name") @NotNull var workout:String,
    @ColumnInfo("workout_info") @NotNull var workoutInfo:String,
    @ColumnInfo("workout_image") @NotNull var workoutImage:String) {

}