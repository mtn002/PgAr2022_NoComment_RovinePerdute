package it.unibs.PgAr.RovinePerdute;


import it.unibs.fp.mylib.MyMenu;

import javax.xml.stream.XMLStreamException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws XMLStreamException {
    //Far scegliere all'utente quale mappa scegliere
        InteragisciXML rovine = new InteragisciXML();
        Double[][] map;
        LinkedList<Citta> grafo = new LinkedList<>();
        int scelta;
        String[] voci = {"Mappa da 5", "Mappa da 12", "Mappa da 50", "Mappa da 200", "Mappa da 2000", "Mappa da 10000"};
        MyMenu menu = new MyMenu("Scegli mappa: ", voci);
        do {
            scelta = menu.scegli();
            switch (scelta) {
                case 1 -> {
                    map = new Double[5][5];
                    grafo = rovine.creaGrafo("Map5.xml", map);
                }
                case 2 -> {
                    map = new Double[12][12];
                    grafo = rovine.creaGrafo("Map12.xml", map);
                }
                case 3 -> {
                    map = new Double[50][50];
                    grafo = rovine.creaGrafo("Map50.xml", map);
                }
                case 4 -> {
                    map = new Double[200][200];
                    grafo = rovine.creaGrafo("Map200.xml", map);
                }
                case 5 -> {
                    map = new Double[2000][2000];
                    grafo = rovine.creaGrafo("Map2000.xml", map);
                }
                case 6 -> {
                    map = new Double[10000][10000];
                    grafo = rovine.creaGrafo("Map10000.xml", map);
                }
            }
        }while(scelta != 0);
    }
}
