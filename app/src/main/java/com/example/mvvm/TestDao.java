package com.example.mvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TestDao {
@Insert
void insert(ClassTest classTest);
@Update
void update (ClassTest classTest);
@Delete
void delete (ClassTest classTest);

@Query("DELETE FROM Class_Test")
    void deleteAllNotes();

@Query("SELECT * FROM Class_Test ORDER BY Roll DESC")
LiveData<List<ClassTest>> getAllNotes();


}
