package com.example.mrcook.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {FavList.class}, version = 2)
@TypeConverters({DataConverter.class})
public abstract class FavDB extends RoomDatabase {
    public abstract ListDao listDao();
}
