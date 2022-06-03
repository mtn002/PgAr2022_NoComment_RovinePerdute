package it.unibs.PgAr.RovinePerdute;

public class Citta {
    private String nome;
    private double x, y, h;
    private int ID;
    private int numeroLinkCitta;

    public Citta(String nome, double x, double y, double h, int ID){
        this.nome = nome;
        this.x=x;
        this.y=y;
        this.h=h;
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public double getX() {
        return x;
    }

    public double getH() {
        return h;
    }

    public double getY() {
        return y;
    }

    public int getID() {
        return ID;
    }

    public int getNumeroLinkCitta() {
        return numeroLinkCitta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNumeroLinkCitta(int numeroLinkCitta) {
        this.numeroLinkCitta = numeroLinkCitta;
    }

    public double determinaDistanza(Citta citta){
        return Math.sqrt((Math.pow((this.getX()- citta.getX()), 2))+(Math.pow((this.getY()- citta.getY()), 2)));
    }
}
