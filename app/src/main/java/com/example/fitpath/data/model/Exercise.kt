package com.example.fitpath.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("exercise_id") var exerciseId:Int,
    @ColumnInfo("daily_exercise_id") var dailyExerciseId:Int,
    @ColumnInfo("exercise_name") var exerciseName:String,
    @ColumnInfo("exercise_set") var exerciseSet:String,
    @ColumnInfo("exercise_rep") var exerciseRep:String,
    @ColumnInfo("exercise_weight") var exerciseWeight:String){

}
