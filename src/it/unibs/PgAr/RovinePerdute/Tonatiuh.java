package it.unibs.PgAr.RovinePerdute;

public class Tonatiuh extends Veicolo{

    public double calcolaDistaza (Città citta1, Città citta2){
        double distanza =0;
        distanza = Math.sqrt(Math.pow(citta1.getX()-citta2.getX(), 2)+Math.pow(citta1.getY()-citta2.getY(), 2));

        return distanza;
    }

}
