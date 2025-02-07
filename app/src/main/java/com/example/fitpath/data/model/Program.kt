package com.example.fitpath.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "programs")
data class Program(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("program_id") @NotNull var program_id:Int,
    @ColumnInfo("program_name") @NotNull var program_name:String,
    @ColumnInfo("day_one") @NotNull var dayOne:String,
    @ColumnInfo("day_two") @NotNull var dayTwo:String,
    @ColumnInfo("day_three") @NotNull var dayThree:String,
    @ColumnInfo("day_four") @NotNull var dayFour:String,
    @ColumnInfo("day_fife") @NotNull var dayFife:String,
    @ColumnInfo("day_six") @NotNull var daySix:String,
    @ColumnInfo("day_seven") @NotNull var daySeven:String,
    ) : Serializable{
}