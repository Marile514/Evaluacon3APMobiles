package com.example.evaluacon3apmobiles;

import java.io.Serializable;
import java.util.Date;

public class Eventos implements Serializable {
    private int id_evento;
    private String titulo, importancia, observacion, lugar;
    private String fecha_evento, tiempo_aviso;
    private Integer id_usuario;

    public Eventos(){

    }

    public Eventos(int id_evento, String titulo, String importancia, String observacion, String lugar, String fecha_evento, String tiempo_aviso, Integer id_usuario) {
        this.id_evento = id_evento;
        this.titulo = titulo;
        this.importancia = importancia;
        this.observacion = observacion;
        this.lugar = lugar;
        this.fecha_evento = fecha_evento;
        this.tiempo_aviso = tiempo_aviso;
        this.id_usuario = id_usuario;
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImportancia() {
        return importancia;
    }

    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(String fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public String getTiempo_aviso() {
        return tiempo_aviso;
    }

    public void setTiempo_aviso(String tiempo_aviso) {
        this.tiempo_aviso = tiempo_aviso;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
