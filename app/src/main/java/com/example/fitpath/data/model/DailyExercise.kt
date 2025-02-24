package com.example.fitpath.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "daily_exercise")
data class DailyExercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("exercise_daily_id") var exerciseDailyId:Int,
    @ColumnInfo("exercise_title") var title:String,
    @ColumnInfo("exercise_time") var time:String,
    @ColumnInfo("exercise_date") var date:String):Serializable {
}