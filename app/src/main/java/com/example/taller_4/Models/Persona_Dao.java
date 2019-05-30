package com.example.taller_4.Models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface Persona_Dao {
    @Insert
    public void insert(Persona... persona);

    @Query("SELECT * FROM persona")
    public List<Persona> getItems();

    @Delete
    public void delete (Persona... persona);

    @Update
    public void update (Persona... persona);

    @Query("SELECT count(id) FROM persona")
    public int getICount();

}
