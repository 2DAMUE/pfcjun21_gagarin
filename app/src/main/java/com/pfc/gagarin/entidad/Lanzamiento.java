package com.pfc.gagarin.entidad;

public class Lanzamiento {
    private String rocket;
    private String hora;
    private String imagen;
    private String lugar;

    public Lanzamiento(String rocket, String hora, String imagen) {
        this.rocket = rocket;
        this.hora = hora;
        this.imagen = imagen;
    }

    public String getRocket() {
        return rocket;
    }

    public void setRocket(String rocket) {
        this.rocket = rocket;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
