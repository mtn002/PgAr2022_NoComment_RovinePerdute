package it.unibs.PgAr.RovinePerdute;
import jdk.jshell.spi.ExecutionControl;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.*;
public class RovinePerdute {
    private LinkedList <Nodo> mappa = new LinkedList <Nodo>();

    //Questo metodo costituisce "leggi XML"
    public void scriviGrafo () throws XMLStreamException {
        //inizializzazione
        XMLInputFactory xmlif;
        XMLStreamReader xmlr = null;
        try{
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader("Map5.xml", new FileInputStream("Map5.xml"));
        } catch (Exception e){
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        while(xmlr.hasNext()){
            switch (xmlr.getEventType()){
                case XMLStreamConstants.START_DOCUMENT:
                    xmlr.getAttributeValue(0);
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    Nodo nodo = new Nodo();
                    if (xmlr.getLocalName().equals("City")){
                        for (int i=0; i<xmlr.getAttributeCount(); i++){
                            switch (i){
                                case 0:
                                    int ID=  Integer.parseInt(xmlr.getAttributeValue(i));
                                    nodo.getCitta().setID(ID);
                                    break;
                                case 1:
                                    nodo.getCitta().setNome(xmlr.getAttributeValue(i));
                                    break;
                                case 2:
                                    int x= Integer.parseInt(xmlr.getAttributeValue(i));
                                    nodo.getCitta().setX(x);
                                    break;
                                case 3:
                                    int y =Integer.parseInt(xmlr.getAttributeValue(i));
                                    nodo.getCitta().setY(y);
                                    break;
                                case 4:
                                    int h = Integer.parseInt(xmlr.getAttributeValue(i));
                                    nodo.getCitta().setH(h);
                                    break;
                            }

                        }

                    }
                    else {
                        int ID=0;
                        for (int i=0; i< xmlr.getAttributeCount(); i++){
                            if (xmlr.getText().trim().length()>0) {
                                ID = Integer.parseInt(xmlr.getAttributeValue(i));
                                nodo.getPuntaA().set(i, ID);
                                System.out.printf ("Attributo %s ->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
                            }
                        }

                    }
                    mappa.add(nodo);
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    break;


            }
        }

    }

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
        xmlw.writeEndDocument();
    }

    //agg metodo per ricerca percorso migliore



}
