package com.example.fitpath.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "exercises")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("exercise_id") @NotNull var exercise_id:Int,
    @ColumnInfo("daily_exercise_id") @NotNull var daily_exercise_id:Int,
    @ColumnInfo("exercise_name") @NotNull var exercise_name:String,
    @ColumnInfo("exercise_set") @NotNull var exercise_set:String,
    @ColumnInfo("exercise_rep") @NotNull var exercise_rep:String,
    @ColumnInfo("exercise_weight") @NotNull var exercise_weight:String){

}
