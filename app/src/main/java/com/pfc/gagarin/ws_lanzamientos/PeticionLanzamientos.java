package com.pfc.gagarin.ws_lanzamientos;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PeticionLanzamientos {

    private static Document doc;

    public static void conectar() {
        try {
            doc = Jsoup.connect("https://www.spacelaunchschedule.com/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String pedirNombreCohete(int i) {

        int contador = 0;
        String tit = "";

        Elements newsHeadlines = doc.getElementsByClass("h5 mt-2 d-block");
        for (Element headline : newsHeadlines) {
            if (contador == i){
                tit = headline.text();

            }
            contador++;

        }

        return tit;
    }

    public static String pedirFecha(int i) {

        int contador = 0;
        String fecha = "";

        Elements newsHeadlines = doc.getElementsByClass("col h5 mb-1 pt-1");
        for (Element headline : newsHeadlines) {
            if (contador == i){
                fecha = headline.child(0).text();

            }
            contador++;

        }

        return fecha;
    }
    public static String pedirFoto(int i) {

        int contador = 0;
        String urlfoto = "";

        Elements newsHeadlines = doc.getElementsByClass("d-none d-md-block col-3 col-md-3 launch-list-thumbnail");
        for (Element headline : newsHeadlines) {
            if (contador == i){
                urlfoto = headline.attr("style");

            }
            contador++;
        }
        urlfoto = urlfoto.substring(21,urlfoto.length()-2);

        return urlfoto;
    }
}
