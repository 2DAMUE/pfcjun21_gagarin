package com.pfc.gagarin.ws_lanzamientos_detalles;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PeticionLanzamientosDetalle {

    private static Document doc;
    private static int n = 3;

    public static void conectar(String link) {
        try {
            doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String pedirInfo() {
        String urldatos = "";
        Elements newsHeadlines = doc.getElementsByClass("my-4 mx-4").tagName("p");
        for (Element headline : newsHeadlines) {
            urldatos += headline.text() + " ";
        }
        return urldatos;
    }
    public static String pedirLocation() {
        String urldatos = "";
        Elements newsHeadlines = doc.getElementsByClass("stretched-link");
        int i = 0;
        for (Element headline : newsHeadlines) {
            if(i == 0){
                urldatos = headline.attr("href");
            }
           i++;
        }
        urldatos = urldatos.replace("https://maps.google.com/maps?q=","");
        return urldatos;
    }
    public static String pedirMeses() {
        String digito1 = "";
        String digito2 = "";
        String unidad = "";
        int i = 0;
        Elements newsHeadlines = doc.getElementsByClass("scd-unit scd-unit-horz");
        for (Element ele : newsHeadlines) {
            if(i == 1){
                digito1 = ele.child(0).child(0).child(0).text();
                digito2 = ele.child(0).child(1).child(0).text();
                unidad = ele.child(1).text();
            }
            i++;
        }
        String meses = digito1 + digito2 + " " + unidad;
        return meses;
    }
    public static String pedirDias() {
        String digito1 = "";
        String digito2 = "";
        String unidad = "";
        int i = 0;
        Elements newsHeadlines = doc.getElementsByClass("scd-unit scd-unit-horz");
        for (Element ele : newsHeadlines) {
            if(i == 3){
                digito1 = ele.child(0).child(0).child(0).text();
                digito2 = ele.child(0).child(1).child(0).text();
                unidad = ele.child(1).text();
            }
            i++;
        }
        String dias = digito1 + digito2 + " " + unidad;
        return dias;
    }
    public static String pedirHoras() {
        String digito1 = "";
        String digito2 = "";
        String unidad = "";
        int i = 0;
        Elements newsHeadlines = doc.getElementsByClass("scd-unit scd-unit-horz");
        for (Element ele : newsHeadlines) {
            if(i == 4){
                digito1 = ele.child(0).child(0).child(0).text();
                digito2 = ele.child(0).child(1).child(0).text();
                unidad = ele.child(1).text();
            }
            i++;
        }
        String horas = digito1 + digito2 + " " + unidad;
        return horas;
    }
    public static String pedirMinutos() {
        String digito1 = "";
        String digito2 = "";
        String unidad = "";
        int i = 0;
        Elements newsHeadlines = doc.getElementsByClass("scd-unit scd-unit-horz");
        for (Element ele : newsHeadlines) {
            if(i == 4){
                digito1 = ele.child(0).child(0).child(0).text();
                digito2 = ele.child(0).child(1).child(0).text();
                unidad = ele.child(1).text();
            }
            i++;
        }
        String minuteos = digito1 + digito2 + " " + unidad;
        return minuteos;
    }

}
