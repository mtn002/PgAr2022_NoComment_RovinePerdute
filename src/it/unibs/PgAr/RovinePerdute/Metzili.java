package it.unibs.PgAr.RovinePerdute;

public class Metzili extends Veicolo{
    public double calcoloDistanza (Citta citta1, Citta citta2){
        return Math.abs(citta1.getH()-citta2.getH());
    }

}
