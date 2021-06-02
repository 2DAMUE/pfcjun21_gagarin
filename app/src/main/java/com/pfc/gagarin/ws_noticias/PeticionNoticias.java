package com.pfc.gagarin.ws_noticias;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class PeticionNoticias {

    private static Document doc;

    public static void conectar() {
        try {
            doc = Jsoup.connect("https://spaceflightnow.com/category/news-archive/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String pedirTitular(int i) {

        int contador = 0;
        String tit = "";

        Elements newsHeadlines = doc.getElementsByClass("entry-title mh-loop-title");
        for (Element headline : newsHeadlines) {
            if (contador == i){
                tit = headline.child(0).text();

            }
            contador++;

        }

        return tit;
    }

    public static String pedirFecha(int i) {

        int contador = 0;
        String fecha = "";

        Elements newsHeadlines = doc.getElementsByClass("mh-meta-date updated");
        for (Element headline : newsHeadlines) {
            if (contador == i){
                fecha = headline.text();

            }
            contador++;

        }

        return fecha;
    }
    public static String pedirFoto(int i) {

        int contador = 0;
        String urlfoto = "";

        Elements newsHeadlines = doc.getElementsByClass("mh-loop-thumb");
        for (Element headline : newsHeadlines) {
            if (contador == i){
                urlfoto = headline.child(0).child(0).attr("data-lazy-src");

            }
            contador++;

        }

        return urlfoto;
    }
    public static String pedirCuerpo(int i) {

        int contador = 0;
        String cuerpoNoticia = "";

        Elements newsHeadlines = doc.getElementsByClass("mh-loop-excerpt");
        for (Element headline : newsHeadlines) {
            if (contador == i){
                cuerpoNoticia = headline.child(0).child(0).getElementsByTag("p").first().text();
            }
            contador++;

        }

        return cuerpoNoticia;
    }

    public static String pedirLink(int i) {
        int contador = 0;
        String url = "";

        Elements newsHeadlines = doc.getElementsByClass("entry-title mh-loop-title");
        for (Element headline : newsHeadlines) {
            if (contador == i){
                url = headline.child(0).attr("href");

            }
            contador++;

        }

        return url;


    }
}
