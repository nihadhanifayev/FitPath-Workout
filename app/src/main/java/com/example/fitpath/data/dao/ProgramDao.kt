package com.example.fitpath.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fitpath.data.model.Program

@Dao
interface ProgramDao {

    @Query("SELECT * FROM programs")
    suspend fun getPrograms():List<Program>

    @Insert
    suspend fun addProgram(program: Program)

    @Update
    suspend fun updateProgram(program: Program)

    @Delete
    suspend fun deleteProgram(program: Program)

}