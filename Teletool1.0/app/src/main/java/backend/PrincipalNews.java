package backend;

import com.example.adrian.teletool10.R;
/**
 * Created by camil on 16-11-2016.
 */

public class PrincipalNews {

    private int IdDrawablePri;
    private String nombre;
    private String url;

    public PrincipalNews(int IdDrawablePri, String nombre, String url){
        this.IdDrawablePri = IdDrawablePri;
        this.nombre = nombre;
        this.url = url;
    }

    public int getIdDrawablePri() {
        return IdDrawablePri;
    }

    public void setIdDrawablePri(int idDrawablePri) {
        IdDrawablePri = idDrawablePri;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static PrincipalNews[] getItems() {
        return Items;
    }

    public static void setItems(PrincipalNews[] items) {
        Items = items;
    }

    public static PrincipalNews[] Items = {
            new PrincipalNews(R.drawable.pagprincipal1, "Campaña Teletón 2016", "http://www.teleton.cl/campana/"),
            new PrincipalNews(R.drawable.pagprincipal2, "Voluntariado 2016", "http://www.teleton.cl/noticias/teleton-llama-a-voluntarios-para-la-proxima-campana/"),
            new PrincipalNews(R.drawable.pagprincipal3, "Cuenta Banco de Chile: 24.500-03", "http://www.teleton.bancochile.cl/")
    };

    public static PrincipalNews getItem(int id) {
        for (PrincipalNews item : Items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public int getId() {
        return nombre.hashCode();
    }
}
