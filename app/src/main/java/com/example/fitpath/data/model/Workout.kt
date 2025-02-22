package com.example.fitpath.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "workout")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("workout_id") var workoutId:Int,
    @ColumnInfo("workout_category") var workoutCategory:String,
    @ColumnInfo("workout_name") var workout:String,
    @ColumnInfo("workout_info") var workoutInfo:String,
    @ColumnInfo("workout_image") var workoutImage:String):Serializable {

}