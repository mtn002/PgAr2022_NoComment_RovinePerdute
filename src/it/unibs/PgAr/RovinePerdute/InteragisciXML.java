package it.unibs.PgAr.RovinePerdute;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
public class InteragisciXML {

    public static final String VM = "Ha vinto Metzili";
    public static final String VT = "Ha vinto Tonatiuh";

    //Questo metodo costituisce "leggi XML"
    public LinkedList<Citta> creaGrafo(String filename, double[][] mappaVeicolo1, double[][] mappaVeicolo2) throws XMLStreamException {
        //inizializzazione
        XMLInputFactory xmlif;
        XMLStreamReader xmlr = null;
        LinkedList<Citta> listaCitta = new LinkedList<>();
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT) {
                //fino a che è diverso da un end element ed è uguale a city continua a rimanere nel while
                while (xmlr.getEventType() != XMLStreamConstants.END_ELEMENT && xmlr.getLocalName().equals("city")) {
                    if (xmlr.getLocalName().equals("city")) { //se il tag è uguale a city prende gli attributi e crea una nuova città con tali attributi
                        Citta citta = new Citta("", 0, 0, 0, 0);
                        int numeroLink = 0;
                        for (int i = 0; i < xmlr.getAttributeCount(); i++) {
                            switch (i) {
                                case 0 -> {
                                    int ID = Integer.parseInt(xmlr.getAttributeValue(i));
                                    citta.setID(ID);
                                }
                                case 1 -> citta.setNome(xmlr.getAttributeValue(i));
                                case 2 -> {
                                    int x = Integer.parseInt(xmlr.getAttributeValue(i));
                                    citta.setX(x);
                                }
                                case 3 -> {
                                    int y = Integer.parseInt(xmlr.getAttributeValue(i));
                                    citta.setY(y);
                                }
                                case 4 -> {
                                    int h = Integer.parseInt(xmlr.getAttributeValue(i));
                                    citta.setH(h);
                                }
                            }
                        }
                        //entra nel while se il prossimo tag è appunto un link ed esce quando non lo è più e nel mentre conta quanti link ha questa città
                        do {
                            //qui passa al tag successivo
                            xmlr.next();
                            numeroLink += 1;
                            citta.setNumeroLinkCitta(numeroLink);
                        } while (xmlr.getLocalName().equals("link"));
                        listaCitta.add(citta);//infine aggiunge la città alla mappa alla posizione del suo id
                    }
                    xmlr.next(); //se non è uguale a city passo a quello dopo fino a che non arrivo a un'altra city
                }//a questo punto dovrei avere la mappa piena delle città con i relativi attributi
                //ricomincio la lettura del xml e ogni qualvolta leggo un tag chiamato link, metto nella tabella map al posto id della mappa e link il valore della distanza tra le due città
                //fino a che è diverso da un end element ed è uguale a link continua a rimanere nel while
                Tonatiuh veicolo1 = new Tonatiuh();
                Metzili veicolo2 = new Metzili();
                while (xmlr.getEventType() != XMLStreamConstants.END_ELEMENT && xmlr.getLocalName().equals("link")) {
                    if (xmlr.getLocalName().equals("link")) {
                        for (int i = 0; i < listaCitta.size(); i++) { //scorro tutta la linked list e per ogni città aggiungo quelle a cui è connessa
                            for (int j = 0; j < listaCitta.get(i).getNumeroLinkCitta(); i++) {
                                int link_a_citta = Integer.parseInt(xmlr.getAttributeValue(0)); //imposta il valore di link_a_citta = il valore dell'attributo di link alla prima posizione
                                xmlr.next();
                                mappaVeicolo1[i][link_a_citta] = veicolo1.calcolaDistanza(listaCitta.get(i), listaCitta.get(link_a_citta));
                                mappaVeicolo2[i][link_a_citta] = veicolo2.calcoloDistanza(listaCitta.get(i), listaCitta.get(link_a_citta));
                            }
                        }

                    }
                    xmlr.next();
                }


            }

        }
        return listaCitta;
    }
    public void scriviXML(Tonatiuh t, Metzili m) throws XMLStreamException {
        XMLOutputFactory xmlof;
        XMLStreamWriter xmlw = null;
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("Routes.xml"), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }
        //inizio del doocumento: PRIMO TAG <routes>
        xmlw.writeStartDocument("routes");

        scritturaTagTeam(xmlw, "Tonathiu", t.getCarburante(), t.getCittaPassate().size());

        scritturaTagTeam(xmlw, "Metztli", m.getCarburante(), m.getCittaPassate().size());

        //fine del documento: CHIUSURA PRIMO TAG <routes>
        xmlw.writeEndDocument();
    }

    private void scritturaTagTeam(XMLStreamWriter xmlw, String team_name, double carburante_utilizzato, int numero_citta_toccate) throws XMLStreamException {
        xmlw.writeStartElement("route");
        xmlw.writeAttribute("team", team_name );
        xmlw.writeAttribute("cost", String.valueOf(carburante_utilizzato));
        xmlw.writeAttribute("cities", String.valueOf(numero_citta_toccate));

        xmlw.writeEndElement();
    }

    public void ricercaPercorso (Veicolo v, double[][]mappa, int livello, LinkedList <Citta> citta){
        boolean found = false;
        double minimo = 1000000000.0;
        int indice = 0;
        int i=0;
        do{

            for (int j=0; j<livello; j++) {
                if (minimo > mappa[i][j] && mappa[i][j] > 0) {
                    minimo = mappa[i][j];

                    indice = j;

                }

            }
            v.getCittaPassate().add(citta.get(indice));
            v.setCarburante(v.getCarburante()+mappa[i][indice]);
            i = indice;


        }while (indice != citta.size()-1);
    }


    public void vincitore(Tonatiuh t, Metzili m){
        if (t.getCarburante()>m.getCarburante())
            System.out.println(VM);
        else if (m.getCarburante()>t.getCarburante())
            System.out.println(VT);
        else if (m.getCittaPassate().size()>t.getCittaPassate().size())
            System.out.println(VT);
        else if (m.getCittaPassate().size()<t.getCittaPassate().size())
            System.out.println(VM);
        else if (m.getCittaPassate().get(m.getCittaPassate().size()-1).getID()<t.getCittaPassate().get(t.getCittaPassate().size()-1).getID())
            System.out.println(VM);
        else
            System.out.println(VT);



    }
    public void vinto(Tonatiuh t, Metzili m){
        if (t.getCarburante()>m.getCarburante())
            System.out.println(VM);
        else if (m.getCarburante()>t.getCarburante())
            System.out.println(VT);
        else if (m.getCittaPassate().size()>t.getCittaPassate().size())
            System.out.println(VT);
        else if (m.getCittaPassate().size()<t.getCittaPassate().size())
            System.out.println(VM);
        else if (m.getCittaPassate().get(m.getCittaPassate().size()-1).getID()<t.getCittaPassate().get(t.getCittaPassate().size()-1).getID())
            System.out.println(VM);
        else
            System.out.println(VT);



    }





}
