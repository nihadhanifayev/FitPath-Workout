package com.example.fitpath.classes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "daily_exercise")
data class DailyExercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("exercise_daily_id") @NotNull var exercise_daily_id:Int,
    @ColumnInfo("exercise_title") @NotNull var title:String,
    @ColumnInfo("exercise_time") @NotNull var time:String,
    @ColumnInfo("exercise_date") @NotNull var date:String) {
}