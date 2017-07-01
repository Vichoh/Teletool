package backend;

import java.util.Date;

/**
 * Created by Adrian on 24/11/2016.
 */

public class Nota {

    private String descripcion;
    private Date Fecha;
    private String titulo;
    private int color;

    public Nota(String descripcion, Date fecha) {
        this.descripcion = descripcion;
        Fecha = fecha;
    }

    public Nota() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
