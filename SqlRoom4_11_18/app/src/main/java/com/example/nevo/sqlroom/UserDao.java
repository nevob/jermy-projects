package com.example.nevo.sqlroom;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT uid FROM user WHERE first_name IN (:userName)")
    int loadUidByFirstName(String userName);

    @Query("Delete FROM user WHERE first_name IS (:userName)")
    int deleteByName(String userName);

    @Query("DELETE FROM user")
    void nukeTable();

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
