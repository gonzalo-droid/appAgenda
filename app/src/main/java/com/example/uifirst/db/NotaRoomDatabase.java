package com.example.uifirst.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.uifirst.db.entity.NotaEntity;
import com.example.uifirst.db.dao.NotaDao;

// version de base de datos num de referencia para futuras actualizacions
@Database(entities = {NotaEntity.class}, version = 1)
public abstract class NotaRoomDatabase extends RoomDatabase {

    public abstract NotaDao notaDao();
    //def en documentacion
    public static volatile NotaRoomDatabase INSTANCE;

    // patron singleton
    public static NotaRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (NotaRoomDatabase.class){
                if(INSTANCE ==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NotaRoomDatabase.class,"notas_database")
                    .build();
                }
            }
        }
        return INSTANCE;
    }


}
