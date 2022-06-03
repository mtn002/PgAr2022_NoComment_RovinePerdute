package it.unibs.PgAr.RovinePerdute;


import it.unibs.fp.mylib.MyMenu;

import javax.xml.stream.XMLStreamException;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws XMLStreamException {
    //Far scegliere all'utente quale mappa scegliere
        InteragisciXML rovine = new InteragisciXML();
        double[][] mappaTonatiuh;
        double[][] mappaMetzili;
        LinkedList<Citta> listaCitta = new LinkedList<>();
        int scelta;
        String[] voci = {"Mappa da 5", "Mappa da 12", "Mappa da 50", "Mappa da 200", "Mappa da 2000", "Mappa da 10000"};
        MyMenu menu = new MyMenu("Scegli mappa: ", voci);
        do {
            scelta = menu.scegli();
            switch (scelta) {
                case 1 -> {
                    mappaTonatiuh = new double[5][5];
                    mappaMetzili = new double[5][5];
                    listaCitta = rovine.creaGrafo("Map5.xml", mappaTonatiuh, mappaMetzili);
                    //determina percorso migliore veicolo1
                    trovaPercorso(mappaTonatiuh);
                    //determina percorso migliore veicolo2
                    //riempi XML e stampalo
                }
                case 2 -> {
                    mappaTonatiuh = new double[12][12];
                    mappaMetzili = new double[12][12];
                    listaCitta = rovine.creaGrafo("Map12.xml", mappaTonatiuh, mappaMetzili);
                }
                case 3 -> {
                    mappaTonatiuh = new double[50][50];
                    mappaMetzili = new double[50][50];
                    listaCitta = rovine.creaGrafo("Map50.xml", mappaTonatiuh, mappaMetzili);
                }
                case 4 -> {
                    mappaTonatiuh = new double[200][200];
                    mappaMetzili = new double[200][200];
                    listaCitta = rovine.creaGrafo("Map200.xml", mappaTonatiuh, mappaMetzili);
                }
                case 5 -> {
                    mappaTonatiuh = new double[2000][2000];
                    mappaMetzili =  new double[2000][2000];
                    listaCitta = rovine.creaGrafo("Map2000.xml", mappaTonatiuh, mappaMetzili);
                }
                case 6 -> {
                    mappaTonatiuh = new double[10000][10000];
                    mappaMetzili = new double[10000][10000];
                    listaCitta = rovine.creaGrafo("Map10000.xml", mappaTonatiuh, mappaMetzili);
                }
            }
        }while(scelta != 0);
    }

    private static void trovaPercorso(double[][] mappa){
        double distanzaPercorsa;
        int i=0;
        int j= mappa.length;
        do {
            if (mappa[i][j] != 0) {
                distanzaPercorsa = +mappa[i][j];
                i = j;
                j = mappa.length;
            } else j--;
        }while(mappa[i][mappa.length]!=0);
    }
}
