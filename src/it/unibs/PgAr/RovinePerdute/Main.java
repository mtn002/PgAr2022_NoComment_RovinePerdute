package it.unibs.PgAr.RovinePerdute;




import javax.xml.stream.XMLStreamException;
import java.util.LinkedList;

public class Main {
    public final static int C = 5;
    public final static int DOD = 12;
    public final static int DC = 200;
    public final static int DM = 2000;
    public final static int CIN = 50;
    public final static int M = 10000;




    public static void main(String[] args) throws XMLStreamException {
//Far scegliere all'utente quale mappa scegliere
        InteragisciXML rovine = new InteragisciXML();
        double[][] mappaTonatiuh;
        double[][] mappaMetzili;
        Metzili m = new Metzili();
        Tonatiuh t = new Tonatiuh();
        LinkedList<Citta> listaCitta = new LinkedList<>();
        int scelta;
        String[] voci = {"Mappa da 5", "Mappa da 12", "Mappa da 50", "Mappa da 200", "Mappa da 2000", "Mappa da 10000"};
        MyMenu menu = new MyMenu("Scegli mappa: ", voci);
        do {
            scelta = menu.scegli();
            switch (scelta) {
                case 1:
                    mappaTonatiuh = new double[C][C];
                    mappaMetzili = new double[C][C];
                    listaCitta = rovine.creaGrafo("Map5.xml", mappaTonatiuh, mappaMetzili);
                    //determina percorso migliore veicolo1
                    rovine.ricercaPercorso(t, mappaTonatiuh, C, listaCitta);
                    //determina percorso migliore veicolo2
                    rovine.ricercaPercorso(m, mappaMetzili, C, listaCitta);
                    //riempi XML e stampalo
                    rovine.scriviXML(t, m);
                    //stampa chi ha vinto
                    rovine.vinto(t, m);
                    break;
                case 2:
                    mappaTonatiuh = new double[DOD][DOD];
                    mappaMetzili = new double[DOD][DOD];
                    listaCitta = rovine.creaGrafo("Map12.xml", mappaTonatiuh, mappaMetzili);
                    //determina percorso migliore veicolo1
                    rovine.ricercaPercorso(t, mappaTonatiuh, DOD, listaCitta);
                    //determina percorso migliore veicolo2
                    rovine.ricercaPercorso(m, mappaMetzili, DOD, listaCitta);
                    //riempi XML e stampalo
                    rovine.scriviXML(t, m);
                    //stampa chi ha vinto
                    rovine.vinto(t, m);
                    break;
                case 3:
                    mappaTonatiuh = new double[CIN][CIN];
                    mappaMetzili = new double[CIN][CIN];
                    listaCitta = rovine.creaGrafo("Map50.xml", mappaTonatiuh, mappaMetzili);
                    //determina percorso migliore veicolo1
                    rovine.ricercaPercorso(t, mappaTonatiuh, CIN, listaCitta);
                    //determina percorso migliore veicolo2
                    rovine.ricercaPercorso(m, mappaMetzili, CIN, listaCitta);
                    //riempi XML e stampalo
                    rovine.scriviXML(t, m);
                    //stampa chi ha vinto
                    rovine.vinto(t, m);
                    break;
                case 4:
                    mappaTonatiuh = new double[DC][DC];
                    mappaMetzili = new double[DC][DC];
                    listaCitta = rovine.creaGrafo("Map200.xml", mappaTonatiuh, mappaMetzili);
                    //determina percorso migliore veicolo1
                    rovine.ricercaPercorso(t, mappaTonatiuh, DC, listaCitta);
                    //determina percorso migliore veicolo2
                    rovine.ricercaPercorso(m, mappaMetzili, DC, listaCitta);
                    //riempi XML e stampalo
                    rovine.scriviXML(t, m);
                    //stampa chi ha vinto
                    rovine.vinto(t, m);
                    break;
                case 5:
                    mappaTonatiuh = new double[DM][DM];
                    mappaMetzili = new double[DM][DM];
                    listaCitta = rovine.creaGrafo("Map2000.xml", mappaTonatiuh, mappaMetzili);
                    //determina percorso migliore veicolo1
                    rovine.ricercaPercorso(t, mappaTonatiuh, DM, listaCitta);
                    //determina percorso migliore veicolo2
                    rovine.ricercaPercorso(m, mappaMetzili, DM, listaCitta);
                    //riempi XML e stampalo
                    rovine.scriviXML(t, m);
                    //stampa chi ha vinto
                    rovine.vinto(t, m);
                    break;
                case 6:
                    mappaTonatiuh = new double[M][M];
                    mappaMetzili = new double[M][M];
                    listaCitta = rovine.creaGrafo("Map10000.xml", mappaTonatiuh, mappaMetzili);
                    //determina percorso migliore veicolo1
                    rovine.ricercaPercorso(t, mappaTonatiuh, M, listaCitta);
                    //determina percorso migliore veicolo2
                    rovine.ricercaPercorso(m, mappaMetzili, M, listaCitta);
                    //riempi XML e stampalo
                    rovine.scriviXML(t, m);
                    //stampa chi ha vinto
                    rovine.vinto(t, m);
                    break;
            }
        } while (scelta != 0);
    }
}

