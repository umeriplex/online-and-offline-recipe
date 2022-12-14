package com.example.mrcook.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ListDao {

    @Insert
    void insertRecord(FavList favList);

    @Query("SELECT EXISTS(SELECT * FROM FavList WHERE pid = :pid)")
    Boolean is_exists(int pid);

    @Query("SELECT * FROM FavList")
    List<FavList> getAll();

    @Query("DELETE FROM FavList WHERE id = :id")
    void deleteById(int id);

}
