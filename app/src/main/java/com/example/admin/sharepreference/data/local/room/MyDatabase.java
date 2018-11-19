package com.example.admin.sharepreference.data.local.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.admin.sharepreference.data.local.dao.UserDao;
import com.example.admin.sharepreference.data.local.room.entity.User;

@Database(entities = {User.class},version = 1)
public  abstract class MyDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

    public static MyDatabase getDatabase(Context context){
        return Room.databaseBuilder(context,MyDatabase.class,"my-db")
                .allowMainThreadQueries()
                .build();
    }


}
