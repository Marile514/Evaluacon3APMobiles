package com.example.evaluacon3apmobiles;

import java.util.Date;

public class Eventos {
    private String titulo, importancia, observacion, lugar;
    private Date fecha_evento, tiempo_aviso;
    private Integer id_usuario;

    public Eventos(){

    }

    public Eventos(String titulo, String importancia, String observacion, String lugar, Date fecha_evento, Date tiempo_aviso, Integer id_usuario) {
        this.titulo = titulo;
        this.importancia = importancia;
        this.observacion = observacion;
        this.lugar = lugar;
        this.fecha_evento = fecha_evento;
        this.tiempo_aviso = tiempo_aviso;
        this.id_usuario = id_usuario;
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

    public Date getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(Date fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public Date getTiempo_aviso() {
        return tiempo_aviso;
    }

    public void setTiempo_aviso(Date tiempo_aviso) {
        this.tiempo_aviso = tiempo_aviso;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }
}
