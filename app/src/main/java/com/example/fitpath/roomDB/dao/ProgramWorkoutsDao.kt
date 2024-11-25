package com.example.fitpath.roomDB.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fitpath.classes.Program
import com.example.fitpath.classes.ProgramWorkouts

@Dao
interface ProgramWorkoutsDao {

    @Query("SELECT * FROM program_workouts WHERE program_id=:id AND program_workout_day=:day")
    suspend fun getProgramIDWorkouts(id:Int,day:Int):List<ProgramWorkouts>

    @Query("SELECT * FROM program_workouts WHERE program_id=:id")
    suspend fun getProgramIDWorkoutsWithPW(id:Int):List<ProgramWorkouts>

    @Query("SELECT * FROM programs ORDER BY program_id DESC LIMIT 1;")
    suspend fun getLastID():Program

    @Insert
    suspend fun addProgramWorkout(program:ProgramWorkouts)
}