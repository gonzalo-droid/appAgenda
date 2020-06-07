package com.example.uifirst;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.uifirst.db.NotaRoomDatabase;
import com.example.uifirst.db.dao.NotaDao;
import com.example.uifirst.db.entity.NotaEntity;

import java.util.List;

public class NotaRepositoty {

    private NotaDao notaDao;
    private LiveData<List<NotaEntity>> allNotas;
    private LiveData<List<NotaEntity>> allNotasFavoritas;

    public NotaRepositoty(Application application){
        NotaRoomDatabase db = NotaRoomDatabase.getDatabase(application);
        notaDao = db.notaDao();
        allNotas = notaDao.getAll();
        allNotasFavoritas = notaDao.getAllFavoritas();
    }

    public LiveData<List<NotaEntity>> getAll(){ return  allNotas;}
    public LiveData<List<NotaEntity>> getAllNotasFavoritas(){ return  allNotasFavoritas;}

    public void insert(NotaEntity notaEntity){
        // insert en la db
        new insertAsyncTask(notaDao).execute(notaEntity);
        // tarea en segundo plano
        // .execute(notaEntity);
    }

    private static class insertAsyncTask extends AsyncTask<NotaEntity,Void,Void>{
        private NotaDao notaDaoAsyncTask;

        //instancia
        insertAsyncTask(NotaDao dao){
            notaDaoAsyncTask = dao;
        }

        //parametro es un array pero como solo se envia un elemento "[0]"
        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDaoAsyncTask.insert(notaEntities[0]);
            return null;
        }


    }

    public void update(NotaEntity notaEntity){
        // insert en la db
        new updateAsyncTask(notaDao).execute(notaEntity);
        // tarea en segundo plano
        // .execute(notaEntity);
    }
    private static class updateAsyncTask extends AsyncTask<NotaEntity,Void,Void>{
        private NotaDao notaDaoAsyncTask;

        //instancia
        updateAsyncTask(NotaDao dao){
            notaDaoAsyncTask = dao;
        }

        //parametro es un array pero como solo se envia un elemento "[0]"
        @Override
        protected Void doInBackground(NotaEntity... notaEntities) {
            notaDaoAsyncTask.update(notaEntities[0]);
            return null;
        }


    }
}
