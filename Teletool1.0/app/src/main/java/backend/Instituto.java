package backend;



import com.example.adrian.teletool10.R;

/**
 * Created by Adrian on 08/11/2016.
 */

public class Instituto {

    private String nombre;
    private String direccion;
    private String fono;
    private String correoElectronico;
    private String descripcion;
    private int idDrawable;
    private int idImaDir;
    private String nom_dire;
    private double latitud;
    private double longitud;



    public Instituto(double latitud, double longitud, String nombre, String direccion, String fono, String correoElectronico,
                     int idDrawable, int idImaDir, String nom_dire,String descripcion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.fono = fono;
        this.correoElectronico = correoElectronico;
        this.idDrawable = idDrawable;
        this.descripcion = descripcion;
        this.idImaDir = idImaDir;
        this.nom_dire = nom_dire;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Instituto(){

    }



    public String getNom_dire() {return nom_dire;}

    public void setNom_dire(String nom_dire) {this.nom_dire = nom_dire;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public int getIdImaDir() { return idImaDir; }

    public void setIdImaDir (int idImaDir) { this.idImaDir = idImaDir; }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public static Instituto[] getItems() {
        return Items;
    }

    public static void setItems(Instituto[] items) {
        Items = items;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public static Instituto[] Items = {

            new Instituto(-38.7299727, -72.6557303, "Temuco", "Aldunate 0390, Temuco", "+56452208500", "temuco@teleton.cl", R.drawable.temuco, R.drawable.d_temuco,"Dr. Iván Barbosa", "El Instituto Teletón Temuco, fue inaugurado el 13 de noviembre del 2001. Es un centro que brinda atención a la Región de la Araucanía (IX) y la Región de Los Ríos (XIV), actualmente, atiende a 1.800 niños y sus familias."),
            new Instituto(-41.4707166, -72.9268541, "Puerto Montt", "Egaña 650, Puerto Montt", "+56652223900", "ptomontt@teleton.cl", R.drawable.puertomontt, R.drawable.d_puertomontt,"Dr. Marcelo Salazar", "El Instituto Teletón Puerto Montt, fue inaugurado en el 7 de diciembre de 1990, gracias a los aportes recibidos en la Octava Campaña Teletón. Es un centro que brinda atención a la Región de Los Lagos (IX) y la Región de Los Ríos (XIV), actualmente, atiende a más de 2.000 niños y sus familias."),
            new Instituto(-18.4853765, -70.2930083, "Arica", "Diego Portales 2471, Arica", "+56582224703", "arica@teleton.cl", R.drawable.arica, R.drawable.d_arica,"Dra. Patricia Huber M.", "El Instituto Teletón  Arica, fue inaugurado el 6 de marzo de 1989. Es un centro que brinda atención a la Región de Arica y Parinacota (XV),  actualmente, en él se atienden más de 650 niños y sus familias."),
            new Instituto(-20.222487, -70.1478839, "Iquique", " J.J. Pérez 999, Iquique", "+56572588888", "iquique@teleton.cl", R.drawable.iquique, R.drawable.d_iquique,"Dr. Julio Volenski", "El Instituto Teletón Iquique, fue inaugurado el 7 de diciembre de 1996. Es un centro que brinda atención a la Región de Tarapacá (I), actualmente, atiende a más de 700 niños y sus familias."),
            new Instituto(-22.4448115, -68.9040971,"Calama", "Teniente Merino 3551, Calama", "+56552695400", "calama@teleton.cl", R.drawable.calama, R.drawable.d_antofagasta_calama,"Dr. Bruno Camaggi Díaz", "El Instituto Teletón Calama, fue inaugurado el 27 de septiembre de 2013. Es un centro que brinda atención a la Comuna de Calama, en la Región de Antofagasta (II), actualmente, atiende a más de 450 niños y sus familias."),
            new Instituto(-23.6774214, -70.4112499,"Antofagasta", " Avenida Angamos 0475, Antofagasta", "+56552683100", "antofagasta@teleton.cl", R.drawable.antofagasta, R.drawable.d_antofagasta_calama,"Dr. Bruno Camaggi Díaz", "El Instituto Teletón Antofagasta, fue inaugurado el 1 de julio de 1981, siendo el segundo Instituto construido a nivel nacional. Es un centro que en sus inicios, brindó atención a todo el Norte grande de Chile, centrando hoy su atención a la Región de Antofagasta (II). En la actualidad, atiende a cerca de 1.300 niños y sus familias."),
            new Instituto(-27.3495885, -70.3504804,"Atacama", "La Cruz 760, Población La Pradera, Copiapó", "+56522207000", "copiapo@teleton.cl", R.drawable.atacama, R.drawable.d_atacama,"Dra. Gloria Osorio", "El Instituto Teletón Atacama, fue inaugurado el 18 de julio de 2011. Es un centro que brinda atención a la Región de Atacama (III), actualmente, atiende a cerca de 600 niños y sus  familias."),
            new Instituto(-29.9298551, -71.2763921,"Coquimbo", "Universidad Pedro de Valdivia, sede la Serena\n" +
                    "Avenida Cuatro Esquinas 060, La Serena", "+56952261800", "coquimbo@teleton.cl", R.drawable.coquimbo, R.drawable.d_coquimbo,"Dra. Adriana Fuenzalida", "El Instituto, fue inaugurado el 25 de abril de 2003.  Actualmente, atiende a más de 1.400 niños, niñas y jóvenes con discapacidad en la región, los cuales tienen la oportunidad de recibir atención médica en el instituto o en las localidades en que viven, gracias a la instauración de rondas profesionales que llegan en ocasiones a sectores como Illapel, Vicuña, Punta de Choros, entre otros."),
            new Instituto(-33.0459112, -71.6145626,"Valparaíso", "Avda. Francia 259, Valparaíso", "+563222324400", "valparaiso@teleton.cl", R.drawable.valparaiso, R.drawable.d_valaparadise,"Dra. Margarita Solar B.", "El Instituto Teletón Valparaíso, fue inaugurado el 2 de abril de 1982. Es un centro que brinda atención a la Región de Valparaiso (V) y parte de la Región de Coquimbo (IV), actualmente, atiende a cerca de 3.400 niños y sus familias."),
            new Instituto(-33.4566199, -70.7019695,"Santiago", "Avda. Libertador Bernardo O’Higgins 4620, Santiago", "+56226772000", "santiago@teleton.cl", R.drawable.santiago, R.drawable.d_santiago,"Dr. Ricardo Eckardt", "El Instituto Teletón Santiago, fue inaugurado el 1 de diciembre de 1979, durante la transmisión de la Segunda Teletón. El centro brinda atención a la Región de Metropolitana (XIII) y la Región del Libertador General Bernardo O´Higgins (VI), actualmente, atiende a más de 10.000 niños y sus familias."),
            new Instituto(-35.4058181,-71.6448644,"Del Maule", "Lircay s/n, Talca", " +56712417600", "talca@teleton.cl", R.drawable.maule, R.drawable.d_talca,"Dra. Evelyn Aravena", "El Instituto Teletón Maule, fue inaugurado el 2 de diciembre de 2006. Es un centro que brinda atención a la Región del Maule (VII) y la Región del Libertador Gral. Bernardo O´Higgins (VI). Actualmente, atiende a más de 1.500 niños y sus familias."),
            new Instituto(-36.8429971, -73.1092694,"Concepción", "Los Acacios 1656, Villa San Pedro, Concepción", "+56412209300", "concepcion@teleton.cl", R.drawable.concepcion, R.drawable.d_concepcion,"Dra. Lorena LLorente", "El Instituto Teletón Concepción, fue inaugurado el 20 de julio de 1981, constituyéndose como el segundo Instituto a nivel nacional. Es un centro que brinda atención a la Región del Biobío (VIII), actualmente, atiende a cerca de 3.834 niños y sus familias."),
            new Instituto(-45.5657217, -72.0739758, "Aysén", "Calle Ejército 567, Coyhaique", "+5652672243700", "coyhaique@teleton.cl", R.drawable.aysen, R.drawable.d_aysen,"Dra. Pilar Arismendi", "El Instituto Teletón Aysén, fue inaugurado el 6 de enero del 2014. Es un centro que brinda atención a la Región Aysén del General Carlos Ibáñez del Campo (XI), actualmente, atiende a cerca de 250 niños y sus familias."),

    };

    public static Instituto getItem(int id) {
        for (Instituto item : Items) {
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
