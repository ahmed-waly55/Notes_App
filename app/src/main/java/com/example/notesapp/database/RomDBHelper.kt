package com.example.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class RomDBHelper:RoomDatabase() {
    abstract val dao:NoteDao

    companion object{

        @Volatile
        private var INSTANCE:RomDBHelper? = null
        fun getInstance(context: Context):RomDBHelper{
            return INSTANCE ?: synchronized(this){


                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RomDBHelper::class.java,
                    "MyDB"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance
                instance
            }
        }

        // Database migration from version 1 to version 2
        // In this migration, we are adding a new column named "title" to the "note" table
        // The new column is of type TEXT and is marked as NOT NULL
        // If no value is provided during insertion, an empty string ("") will be set as the default value

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE note ADD COLUMN title TEXT NOT NULL DEFAULT ''")
            }
        }


    }
}