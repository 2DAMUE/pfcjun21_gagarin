package com.pfc.gagarin.ws_body_noticias;

import com.pfc.gagarin.NoticiaScreen;
import com.pfc.gagarin.entidad.Noticia;
import com.pfc.gagarin.ws_noticias.PeticionNoticias;

import java.util.List;

public class HiloPeticionBodyNoticias implements Runnable {
    private NoticiaScreen context;
    private String url_noticia;

    public HiloPeticionBodyNoticias(NoticiaScreen context, String url_noticia) {
        this.context = context;
        this.url_noticia = url_noticia;
    }

    @Override
    public void run() {
        PeticionBodyNoticias.conectar(url_noticia);
        String cuerpoNoticia = PeticionBodyNoticias.pedirCuerpo();
        context.devolverBodyNoticias(cuerpoNoticia);
    }
    public interface InterfazBodyNoticias{
        public void devolverBodyNoticias(String cuerpoNoticia);
    }
}
