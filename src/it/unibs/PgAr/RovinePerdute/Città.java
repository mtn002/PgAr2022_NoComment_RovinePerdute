package it.unibs.PgAr.RovinePerdute;

import java.util.ArrayList;
import java.util.LinkedList;

public class Città {
    private String nome;
    private double x, y, h;
    private int ID;

    public LinkedList <Città> citta = new LinkedList<>();

    public Città (String nome, double x, double y, double h, int ID){
        this.nome = nome;
        this.x=x;
        this.y=y;
        this.h=h;
        this.ID = ID;
        //cittavicine<ArrayList>
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
}
