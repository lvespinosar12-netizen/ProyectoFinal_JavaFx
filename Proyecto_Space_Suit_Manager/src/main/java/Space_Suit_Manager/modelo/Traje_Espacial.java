package Space_Suit_Manager.modelo;

import java.io.Serializable;

public class Traje_Espacial implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String idTraje;
    protected String talla;
    protected double pesoSoportado;
    protected String fechaInspeccion;

    public Traje_Espacial(String idTraje, String talla, double pesoSoportado, String fechaInspeccion) {
        this.idTraje = idTraje;
        this.talla = talla;
        this.pesoSoportado = pesoSoportado;
        this.fechaInspeccion = fechaInspeccion;
    }

    public String getIdTraje() {
        return idTraje;
    }

    public String getTalla() {
        return talla;
    }

    public double getPesoSoportado() {
        return pesoSoportado;
    }

    public String getFechaInspeccion() {
        return fechaInspeccion;
    }
}
