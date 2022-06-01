package it.unibs.PgAr.RovinePerdute;
import jdk.jshell.spi.ExecutionControl;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.*;
public class RovinePerdute {
    private LinkedList <Città> mappa = new LinkedList <Città>();

    //Questo metodo costituisce "leggi XML"
    public void scriviGrafo (String filename, Double[][] map) throws XMLStreamException {
        //inizializzazione
        XMLInputFactory xmlif= null;
        XMLStreamReader xmlr = null;
        try{
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
        } catch (Exception e){
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
            if (xmlr.getEventType() == XMLStreamConstants.START_ELEMENT ) {
                //fino a che è diverso da un end element ed è uguale a city continua a rimanere nel while
                while(xmlr.getEventType() != XMLStreamConstants.END_ELEMENT && xmlr.getLocalName().equals("city")){
                    if (xmlr.getLocalName().equals("city")){ //se il tag è uguale a city prende gli attributi e crea una nuova città con tali attributi
                        Città citta = new Città("", 0, 0, 0, 0);
                        int numeroLink = 0;
                        for (int i=0; i<xmlr.getAttributeCount(); i++){
                            switch (i){
                                case 0:
                                    int ID=  Integer.parseInt(xmlr.getAttributeValue(i));
                                    citta.setID(ID);
                                    break;
                                case 1:
                                    citta.setNome(xmlr.getAttributeValue(i));
                                    break;
                                case 2:
                                    int x= Integer.parseInt(xmlr.getAttributeValue(i));
                                    citta.setX(x);
                                    break;
                                case 3:
                                    int y =Integer.parseInt(xmlr.getAttributeValue(i));
                                    citta.setY(y);
                                    break;
                                case 4:
                                    int h = Integer.parseInt(xmlr.getAttributeValue(i));
                                    citta.setH(h);
                                    break;
                            }
                        }//qui passa al tag successivo
                        xmlr.next();
                        //entra nel while se il prossimo tag è appunto un link e esce quando non lo è più e nel mentre conta quanti link ha questa città
                        while(xmlr.getLocalName().equals("link")){
                            xmlr.next();
                            numeroLink += 1;
                            citta.setNumeroLinkCitta(numeroLink);
                        }
                        mappa.add(citta);//infine aggiunge la città alla mappa alla posizione del suo id
                    }
                    xmlr.next(); //se non è uguale a city passo a quello dopo fino a che non arrivo ad un'altra city
                }//a questo punto dovrei avere la mappa piena delle città con i relativi attributi
                //ricomincio la lettura del xml e ogni qualvolta leggo un tag chiamato link, metto nella tabella map al posto id della mappa e link il valore della distanza tra le due città
                //fino a che è diverso da un end element ed è uguale a link continua a rimanere nel while
                while(xmlr.getEventType() != XMLStreamConstants.END_ELEMENT && xmlr.getLocalName().equals("link")) {
                    if (xmlr.getLocalName().equals("link")) {
                        for (int i =0; i<mappa.size(); i++){ //scorro tutta la linked list e per ogni città aggiungo quelle a cui è connessa
                           for (int j=0; j<mappa.get(i).getNumeroLinkCitta(); i++) {
                               int link_a_citta = Integer.parseInt(xmlr.getAttributeValue(0)); //imposta il valore di link_a_citta = il valore dell'attributo di link alla prima posizione
                               xmlr.next();
                               double distanza = mappa.get(i).determinaDistanza(mappa.get(link_a_citta));
                               distanza = map[i][link_a_citta];
                           }
                        }

                    }
                    xmlr.next();
                }


            }

        }


/*
    public void scriviXML() throws XMLStreamException {
        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("Routes.xml"), "utf-8");
            xmlw.writeStartDocument("utf-8", "1.0");
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del writer:");
            System.out.println(e.getMessage());
        }
        xmlw.writeStartDocument("routes");
        xmlw.writeStartElement("route");
        xmlw.writeAttribute("team", "Tonathiu");
        xmlw.writeAttribute("cost", "carburante_utilizzato"); //cambiare la string con un getter e un tostring
        xmlw.writeAttribute("cities", "numero_città_toccate"); //come sopra
        for (int i=0; i<citta.lenght; i++){ //nome vettore da modificare citta solo per esempio
            xmlw.writeStartElement("city");
            xmlw.writeAttribute("id", citta(i).getId.toString()); //come sopra
            xmlw.writeAttribute("name", citta(i).getName.toString()); //come sopra
            xmlw.writeEndElement();
        }
        xmlw.writeEndElement();
        xmlw.writeStartElement("route");
        xmlw.writeAttribute("team", "Metztli");
        xmlw.writeAttribute("cost", "carburante_utilizzato"); //cambiare la string con un getter e un tostring
        xmlw.writeAttribute("cities", "numero_città_toccate"); //come sopra
        for (int i=0; i<citta.lenght; i++){
            xmlw.writeStartElement("city");
            xmlw.writeAttribute("id", citta(i).getId.toString()); //come sopra
            xmlw.writeAttribute("name", citta(i).getName.toString()); //come sopra
            xmlw.writeEndElement();
        }
        xmlw.writeEndElement();
        xmlw.writeEndDocument();*/
    }

    //agg metodo per ricerca percorso migliore


}
