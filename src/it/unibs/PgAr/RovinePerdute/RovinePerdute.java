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
    public void scriviGrafo (String filename) throws XMLStreamException {
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
            switch (xmlr.getEventType()) { // switch sul tipo di evento
                case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento
                    System.out.println("Start Read Doc " + filename); break;//Caso con la mappa da 5 cita

                case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi
                    System.out.println("Tag " + xmlr.getLocalName());
                    while(xmlr.getEventType() != XMLStreamConstants.END_ELEMENT &&
                            xmlr.getLocalName().equals("city")){
                        if (xmlr.getLocalName().equals("city")){
                            Città citta = new Città("null", 0, 0, 0, 0);
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
                                mappa.add(citta);
                            }//fuori dal for
                        }//fine if
                        xmlr.next();
                           // if(xmlr.getLocalName() != XMLStreamConstants.START_ELEMENT &&
                                           // xmlr.getLocalName().equals("link")){

                                    //int DirezioneCitta = xmlr.getAttributeValue();

                             //}
                                 //prendo l'attributo e lo metto nell'arraylist di collegamenti

                     }

                    break;


            }
            xmlr.next();
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
