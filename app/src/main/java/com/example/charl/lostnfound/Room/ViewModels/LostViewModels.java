package com.example.charl.lostnfound.Room.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.charl.lostnfound.POJOs.Lobjects;
import com.example.charl.lostnfound.Room.Repositories.LostRepository;

import java.util.List;

public class LostViewModels extends AndroidViewModel {

    private LostRepository LostRep;
    private LiveData<List<Lobjects>> list;

    public LostViewModels(@NonNull Application application){
        super(application);
        LostRep = new LostRepository(application);
        list= LostRep.getAllNews();
    }

    public LiveData<List<Lobjects>> getAllobjects(){
        return list;
    }
}
