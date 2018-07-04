package com.example.charl.lostnfound.Room.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.charl.lostnfound.POJOs.Lobjects;
import com.example.charl.lostnfound.Room.DAOs.LostDAO;

@Database(entities = {Lobjects.class},exportSchema = false, version = 1)
public abstract class LostnFoundDatabase extends RoomDatabase {

    private static LostnFoundDatabase INSTANCE;

    public abstract LostDAO lostDao();

    public static LostnFoundDatabase getAppDataBase(Context context) {
        if (INSTANCE == null) {
            synchronized (LostnFoundDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LostnFoundDatabase.class, "Lucina-database").build();
                }

            }
        }
        return INSTANCE;

    }
}
