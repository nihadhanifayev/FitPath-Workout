package com.example.fitpath.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "program_workouts")
data class ProgramWorkouts(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("program_workout_id") var programWorkoutId:Int,
    @ColumnInfo("program_id") var programId:Int,
    @ColumnInfo("workout_name") var workoutName:String,
    @ColumnInfo("program_workout_day") var programWorkoutDay:Int): Serializable {
}