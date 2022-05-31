package it.unibs.PgAr.RovinePerdute;


import it.unibs.fp.mylib.MyMenu;

import javax.xml.stream.XMLStreamException;

public class RovineMain {
    public static void main(String[] args) throws XMLStreamException {
    //Far scegliere all'utente quale mappa scegliere
        RovinePerdute rovine = new RovinePerdute();
        Double[][] map;
        int scelta = 0;
        String[] voci = {"Mappa da 5", "Mappa da 12", "Mappa da 50", "Mappa da 200", "Mappa da 2000", "Mappa da 10000"};
        MyMenu menu = new MyMenu("Scegli mappa: ", voci);
        do {
            scelta = menu.scegli();
            switch (scelta){
                case 1:
                    rovine.scriviGrafo("Map5.xml");
                    map = new Double[5][5];
                    break;
                case 2:
                    rovine.scriviGrafo("Map12.xml");
                    map = new Double[12][12];
                    break;
                case 3:
                    rovine.scriviGrafo("Map50.xml");
                    map = new Double[50][50];
                    break;
                case 4:
                    rovine.scriviGrafo("Map200.xml");
                    map = new Double[200][200];
                    break;
                case 5:
                    rovine.scriviGrafo("Map2000.xml");
                    map = new Double[2000][2000];
                    break;
                case 6:
                    rovine.scriviGrafo("Map10000.xml");
                    map = new Double[10000][10000];
                    break;
            }
        }while(scelta != 0);
    }
}
