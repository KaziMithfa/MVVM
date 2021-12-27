package com.example.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import javax.xml.transform.Result;

public class ResultViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<ClassTest>> allNotes;

    public ResultViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        allNotes = repository.getGetAllNotes();
    }

    public void insert(ClassTest classTest) {
        repository.insert(classTest);
    }

    public void update(ClassTest classTest) {
        repository.update(classTest);
    }

    public void delete(ClassTest classTest) {
        repository.delete(classTest);
    }
    public void deleteAll(){
        repository.deleteAllNotes();
    }

    public LiveData<List<ClassTest>>getAllNotes()
    {
        return  allNotes;
    }






}
