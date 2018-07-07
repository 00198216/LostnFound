package com.example.charl.lostnfound.Room.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.charl.lostnfound.POJOs.Lobjects;
import com.example.charl.lostnfound.POJOs.LostFavorite;
import com.example.charl.lostnfound.Room.DAOs.FavDAO;
import com.example.charl.lostnfound.Room.DAOs.LostDAO;
import com.example.charl.lostnfound.Room.Database.LostnFoundDatabase;

import java.util.List;

public class FavRepository {
    private FavDAO fDao;  //El DAO
    private LiveData<List<LostFavorite>> Fablist;
    private String UsrToken;
    private String User;
    private Context ctx;
    private Application app;

    public FavRepository(Application application){ //Application es para Viewmodel.

        LostnFoundDatabase db= LostnFoundDatabase.getAppDataBase(application);
        fDao = db.favDAO();

        app= application;

        //Recuperando el Token
        SharedPreferences sharedPref = application.getSharedPreferences("LToken",Context.MODE_PRIVATE);
        UsrToken = sharedPref.getString("Token","");
        User = sharedPref.getString("usuario","");

        Fablist= fDao.getFavLost(User);
    }

    public LiveData<List<LostFavorite>> getOwnlist(){return Fablist;}
}
