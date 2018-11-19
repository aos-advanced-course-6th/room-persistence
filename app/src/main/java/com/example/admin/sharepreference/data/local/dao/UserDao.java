package com.example.admin.sharepreference.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.admin.sharepreference.data.local.room.entity.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void save(User[] user);
    @Insert
    void save2(User ... user);
    @Insert
    void save(List<User> users);
    @Insert
    void save(User user);

    @Query("select * from users order by id")
    List<User> getUser();

    @Delete
    void delete(User user);

    @Query("select * from users where id =:id")
    User getUserById(int id);


}
