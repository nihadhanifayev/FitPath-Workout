package com.example.fitpath.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "programs")
data class Program(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("program_id") var programId:Int,
    @ColumnInfo("program_name") var programName:String,
    @ColumnInfo("day_one") var dayOne:String,
    @ColumnInfo("day_two") var dayTwo:String,
    @ColumnInfo("day_three") var dayThree:String,
    @ColumnInfo("day_four") var dayFour:String,
    @ColumnInfo("day_fife") var dayFife:String,
    @ColumnInfo("day_six") var daySix:String,
    @ColumnInfo("day_seven") var daySeven:String,
    ) : Serializable{
}