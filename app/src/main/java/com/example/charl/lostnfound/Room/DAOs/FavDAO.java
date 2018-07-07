package com.example.charl.lostnfound.Room.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.charl.lostnfound.POJOs.Lobjects;
import com.example.charl.lostnfound.POJOs.LostFavorite;

import java.util.List;

@Dao
public interface FavDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLostFav(LostFavorite... lost);

    @Query("SELECT * FROM Favorite_Table WHERE FavUser = :CName")
    LiveData<List<LostFavorite>> getFavLost(String CName);  //Livedata permite que este objeto sea Observado.
}
