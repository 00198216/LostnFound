package com.example.charl.lostnfound.Room.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.charl.lostnfound.Activities.Login;
import com.example.charl.lostnfound.Interfaces.LobjectsDeserializer;
import com.example.charl.lostnfound.Interfaces.LostnFoundAPI;
import com.example.charl.lostnfound.Interfaces.TokenDeserializer;
import com.example.charl.lostnfound.POJOs.Lobjects;
import com.example.charl.lostnfound.Room.DAOs.LostDAO;
import com.example.charl.lostnfound.Room.Database.LostnFoundDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LostRepository {
    private LostDAO lostDao;  //El DAO
    private LiveData<List<Lobjects>> list;   // La lista donde recibiremos los datos
    private LiveData<List<Lobjects>> Ownlist;
    private String UsrToken;
    private String User;
    private Context ctx;
    private Application app;

    public LostRepository(Application application){ //Application es para Viewmodel.

        LostnFoundDatabase db= LostnFoundDatabase.getAppDataBase(application);
        lostDao = db.lostDao();

        app= application;

        //Recuperando el Token
        SharedPreferences sharedPref = application.getSharedPreferences("LToken",Context.MODE_PRIVATE);
        UsrToken = sharedPref.getString("Token","");
        User = sharedPref.getString("usuario","");

        ConsumeObjects();
        list= lostDao.getAllLost();
        Ownlist= lostDao.getOwnLost(User);
    }

    public LiveData<List<Lobjects>> getAllobjects(){
        return  list;
    }

    public LiveData<List<Lobjects>> getOwnlist(){return Ownlist;}

    public void ConsumeObjects(){
        new Consobj(UsrToken,lostDao,app).execute();
    }

    private static class AsyncTaskI extends AsyncTask<ArrayList<Lobjects>,Void,Void> {

        private LostDAO lostDao;

        AsyncTaskI(LostDAO lostDao){
            this.lostDao= lostDao;
        }

        @Override
        protected Void doInBackground(ArrayList<Lobjects>... arrayLists) {
            ArrayList<Lobjects> Not = arrayLists[0]; //Nos vamos a la posicion 0
            for(int e=0; e<Not.size(); e++){
                Lobjects lost = Not.get(e); //Vamos una por una
                lost.setImagen("https://proyectopdm2018.herokuapp.com/"+lost.getImagen().replace("uploads\\","uploads//"));
                lostDao.insertLost(lost);
            }
            return null;
        }


    }

    private static class Consobj extends AsyncTask<Void,Void,Void>{

        private String UsrToken;
        private LostDAO lostDao;
        private Application app;

        public Consobj(String UsrToken,LostDAO lostDAO,Application app){
            this.UsrToken= UsrToken;
            this.lostDao= lostDAO;
            this.app = app;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(LostnFoundAPI.FINISH).addConverterFactory(GsonConverterFactory.create(new Gson())).build();
            LostnFoundAPI LostAPI = retrofit.create(LostnFoundAPI.class);


            Call<ArrayList<Lobjects>> lostO = LostAPI.getObjects("Bearer " + UsrToken);

            lostO.enqueue(new Callback<ArrayList<Lobjects>>() {


                @Override
                public void onResponse(Call<ArrayList<Lobjects>> call, Response<ArrayList<Lobjects>> response) {
                    if(response.isSuccessful()){

                        ArrayList<Lobjects> objects =(ArrayList<Lobjects>) response.body();
                        new AsyncTaskI(lostDao).execute(objects);

                    }else {
                        Toast.makeText(app,response.code()+""+" Secion vencida.",Toast.LENGTH_SHORT).show();
                        SharedPreferences SavedLogin = app.getSharedPreferences("LToken", Context.MODE_PRIVATE);;
                        SavedLogin.edit().clear().apply();
                        Intent intent = new Intent(app,Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        app.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Lobjects>> call, Throwable t) {
                    Toast.makeText(app,"Error de Conexion",Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }
    }


}
