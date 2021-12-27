package com.example.mvvm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ConcurrentSkipListMap;


@Database(entities = {ClassTest.class},version = 1)

public abstract class ClassTestDatabase extends RoomDatabase {

    public static ClassTestDatabase instance;
    public  abstract TestDao testDao();

    public static synchronized ClassTestDatabase getInstance(Context context){
        if(instance == null)
        {
            /*instance = Room.databaseBuilder(context.getApplicationContext(),ClassTestDatabase.class,"ClassTest_database").
                    fallbackToDestructiveMigration().build();*/

           instance = Room.databaseBuilder(context.getApplicationContext(),ClassTestDatabase.class,"ClassTest_database")
                   .fallbackToDestructiveMigration()
                   .addCallback(roomCallback).build();

        }
        return instance;

    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback()
    {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateAsynTask(instance).execute();
        }
    };

    private static class PopulateAsynTask extends AsyncTask<Void,Void,Void>{

        private TestDao testDao;

        public  PopulateAsynTask(ClassTestDatabase database)
        {
            testDao = database.testDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            testDao.insert(new ClassTest("Mithfa",1,20.00));
            testDao.insert(new ClassTest("Kamrul",2,21.00));
            testDao.insert(new ClassTest("Ashik",3,18.00));

            return null;
        }
    }


}
