package it.unibs.PgAr.RovinePerdute;
import jdk.jshell.spi.ExecutionControl;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
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

    public void scriviXML(){


    }

    //agg metodo per ricerca percorso migliore

}
