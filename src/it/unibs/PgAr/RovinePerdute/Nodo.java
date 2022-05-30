package it.unibs.PgAr.RovinePerdute;
import java.util.*;
public class Nodo {
    private Città citta;
    private LinkedList <Integer> puntaA = new LinkedList <Integer>();

    public Città getCitta() {
        return citta;
    }

    public void setCitta(Città citta) {
        this.citta = citta;
    }

    public LinkedList<Integer> getPuntaA() {
        return puntaA;
    }

    public void setPuntaA(LinkedList<Integer> puntaA) {
        this.puntaA = puntaA;
    }
}
