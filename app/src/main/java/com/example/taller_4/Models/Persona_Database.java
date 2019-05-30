package com.example.taller_4.Models;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Persona.class}, version = 1)

public abstract class Persona_Database  extends RoomDatabase {
    public abstract Persona_Dao getItemDAO();
}
