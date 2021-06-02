package com.pfc.gagarin.entidad;

public class Lanzamiento {

    private String rocket;
    private String rocketModel;
    private String hora;
    private String imagen;
    private String lugar;
    private String link;
    private String countdown;
    private String localizacon;
    private String infor;

    public Lanzamiento(String rocket, String hora, String imagen,String lugar) {
        this.rocket = rocket;
        this.hora = hora;
        this.imagen = imagen;
        this.lugar = lugar;
    }
    public Lanzamiento() {
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRocketModel() {
        return rocketModel;
    }

    public void setRocketModel(String rocketModel) {
        this.rocketModel = rocketModel;
    }

    public String getCountdown() {
        return countdown;
    }

    public void setCountdown(String countdown) {
        this.countdown = countdown;
    }

    public String getLocalizacon() {
        return localizacon;
    }

    public void setLocalizacon(String localizacon) {
        this.localizacon = localizacon;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }
}
