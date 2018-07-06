package com.example.charl.lostnfound.Room.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.charl.lostnfound.POJOs.Lobjects;

import java.util.List;

@Dao  //La marcamos como una DAO
public interface LostDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLost(Lobjects... lobjects);

    @Query("SELECT * FROM Lost_Table ORDER BY Date DESC")
    LiveData<List<Lobjects>> getAllLost();  //Livedata permite que este objeto sea Observado.

    @Query("SELECT * FROM Lost_Table WHERE Name = :CName")
    LiveData<List<Lobjects>> getOwnLost(String CName);  //Livedata permite que este objeto sea Observado.

}
