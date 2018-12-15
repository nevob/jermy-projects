package com.example.nevo.sqlroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 2)
public abstract  class DataBase extends RoomDatabase {
        public abstract  UserDao userDao();
}
