package com.example.mynotesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynotesapp.Models.Note

@Dao
interface NoteDao {
   @Insert(onConflict=OnConflictStrategy.REPLACE) ///if note is already exist then replace it
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("UPDATE notes_table Set title=:title,note=:note WHERE id=:id")
    suspend fun update(id: Int?, title: String?, note: String?)

    @Query("Select * from notes_table order by id ASC")
    fun getAllnotes():LiveData<List<Note>>
}