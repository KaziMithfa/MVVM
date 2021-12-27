package com.example.mvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    private TestDao testDao;
    private LiveData<List<ClassTest>> getAllNotes;


    public  Repository(Application application)
    {
        ClassTestDatabase classTestDatabase = ClassTestDatabase.getInstance(application);
        testDao = classTestDatabase.testDao();
        getAllNotes = testDao.getAllNotes();

    }

    public void insert(ClassTest classTest)
    {
        new InsertAsyncTask(testDao).execute(classTest);


    }
    public void update(ClassTest classTest)
    {
        new UpdateAsyncTask(testDao).execute(classTest);

    }
    public void delete(ClassTest classTest)
    {
        new DeleteAsynctask(testDao).execute(classTest);

    }
    public void deleteAllNotes()
    {
        new DeleteAllNotesAsyncTask(testDao).execute();

    }

    public LiveData<List<ClassTest>>getGetAllNotes(){
        return getAllNotes;
    }

    private static class InsertAsyncTask extends AsyncTask<ClassTest,Void,Void>
    {
        private TestDao testDao;

        private   InsertAsyncTask(TestDao testDao)
        {
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(ClassTest... classTests) {

            testDao.insert(classTests[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends  AsyncTask<ClassTest,Void,Void>

    {
        private TestDao testDao;

        private   UpdateAsyncTask(TestDao testDao)
        {
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(ClassTest... classTests) {
            testDao.update(classTests[0]);


            return null;
        }
    }

    private static class DeleteAsynctask extends  AsyncTask<ClassTest,Void,Void>

    {
        private TestDao testDao;

        private   DeleteAsynctask(TestDao testDao)
        {
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(ClassTest... classTests) {

            testDao.delete(classTests[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends  AsyncTask<Void,Void,Void>

    {
        private TestDao testDao;

        private   DeleteAllNotesAsyncTask(TestDao testDao)
        {
            this.testDao = testDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            testDao.deleteAllNotes();
            return null;
        }
    }



}
