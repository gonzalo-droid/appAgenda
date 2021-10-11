package com.example.uifirst.db.entity;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//default room use name of Javaclass as name of tableDB
//default room use name of attributeClass as name of columnDB

@Entity(tableName = "notas")
public class NotaEntity {

    //autoGenerate = true ===> id automaticos a entity
    @PrimaryKey(autoGenerate = true)
    public int id;

    //@ColumnInfo(name="titulo")
    public String titulo;
    public String contenido;
    public boolean favorita;
    public String color;

    //ignorar campos
    @Ignore
    Bitmap picture;

    public NotaEntity() {

    }

    public NotaEntity( String titulo, String contenido, boolean favorita, String color) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.favorita = favorita;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isFavorita() {
        return favorita;
    }

    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
