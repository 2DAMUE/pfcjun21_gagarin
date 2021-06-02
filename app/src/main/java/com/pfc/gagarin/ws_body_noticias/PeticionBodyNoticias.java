package com.pfc.gagarin.ws_body_noticias;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PeticionBodyNoticias {
    private static Document doc;

    public static void conectar(String url_noticia) {
        try {
            doc = Jsoup.connect(url_noticia).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String pedirCuerpo() {

        String cuerpoNoticia = "";

        Elements newsHeadlines = doc.getElementsByClass("entry-content");
        for (Element headline : newsHeadlines) {
                Elements phrases = headline.getElementsByTag("p");
                for(Element phrase : phrases){

                    if(!phrase.text().contains("<span")){
                        cuerpoNoticia += phrase.text()+"\n\n";
                    }
                }
        }
        return cuerpoNoticia;
    }
}
