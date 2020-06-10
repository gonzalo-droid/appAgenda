package com.example.uifirst;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.uifirst.db.entity.NotaEntity;

import java.util.List;

public class NuevaNotaDialogViewModel extends AndroidViewModel {
    private LiveData<List<NotaEntity>> allNotas; // lista q se actualiza
    private NotaRepositoty notaRepositoty; // clase facilita los datos de room o api

    public NuevaNotaDialogViewModel(Application application) {
        super(application);
        notaRepositoty = new NotaRepositoty(application);
        allNotas = notaRepositoty.getAll();
    }

    //frgamento q necesita recibir la nueva lista de datos
    public LiveData<List<NotaEntity>> getAllNotas() {
        return allNotas;
    }

    //frgamento q insert una nueva nota
    public void insrtNota(NotaEntity nuevaNotaEntity) {
        notaRepositoty.insert(nuevaNotaEntity);
    }

}
