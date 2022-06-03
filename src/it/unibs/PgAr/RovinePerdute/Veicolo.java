package it.unibs.PgAr.RovinePerdute;
import java.util.*;
public class Veicolo {
    private double carburante;
    private LinkedList <Citta> cittaPassate = new LinkedList <>();
    public void setCarburante(double carburante) {
        this.carburante = carburante;
    }

    public double getCarburante() {
        return carburante;
    }

    public LinkedList<Citta> getCittaPassate() {
        return cittaPassate;
    }
}
