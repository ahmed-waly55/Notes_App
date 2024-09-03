package com.example.notesapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.database.Note
import com.example.notesapp.database.RomDBHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(app : Application):AndroidViewModel(app) {
   private val db = RomDBHelper.getInstance(app)

    fun upsert (n:Note) {
        viewModelScope.launch(Dispatchers.IO) {
            db.dao.upsertNote(n)
        }
    }


    fun delete(n : Note) {
        viewModelScope.launch(Dispatchers.IO) {
            db.dao.deleteNote(n)
        }

    }


    fun getNotes() = db.dao.getNotes()

}