package it.unibs.PgAr.RovinePerdute;

public class Metzili extends Veicolo{
    public double calcoloDistanza (Città citta1, Città citta2){
        double carburante =0;
        carburante = Math.abs(citta1.getH()-citta2.getH());
        return carburante;
    }

}
