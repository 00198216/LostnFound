package com.example.charl.lostnfound.Room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.charl.lostnfound.POJOs.Lobjects;
import com.example.charl.lostnfound.POJOs.LostFavorite;
import com.example.charl.lostnfound.Room.Repositories.FavRepository;
import com.example.charl.lostnfound.Room.Repositories.LostRepository;

import java.util.List;

public class FavViewModels extends AndroidViewModel {

    private FavRepository LostRep;
    private LiveData<List<LostFavorite>> Ownlist;

    public FavViewModels(@NonNull Application application){
        super(application);
        LostRep = new FavRepository(application);
        Ownlist= LostRep.getOwnlist();
    }

    public LiveData<List<LostFavorite>> getFavList(){
        return Ownlist;
    }

}
