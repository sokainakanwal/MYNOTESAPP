package com.example.mynotesapp.database

import androidx.lifecycle.LiveData
import com.example.mynotesapp.Models.Note

class notes_repository(private val notedao:NoteDao) {

    val allNotes:LiveData<List<Note>>  = notedao.getAllnotes()

    suspend fun insert(note:Note){
        notedao.insert(note)
    }

    suspend fun delete(note:Note){
        notedao.delete(note)
    }

    suspend fun update(note: Note){
        notedao.update(note.Id,note.title,note.note)
    }
}