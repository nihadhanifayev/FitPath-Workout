package com.example.fitpath.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "program_workouts")
data class ProgramWorkouts(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("program_workout_id") var program_workout_id:Int,
    @ColumnInfo("program_id") var program_id:Int,
    @ColumnInfo("workout_name") var workout_name:String,
    @ColumnInfo("program_workout_day") var program_workout_day:Int): Serializable {
}