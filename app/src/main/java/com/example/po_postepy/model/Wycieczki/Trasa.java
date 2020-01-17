package com.example.po_postepy.model.Wycieczki;

import com.example.po_postepy.model.Obszary.GrupaGorska;
import com.example.po_postepy.model.Obszary.TerenGorski;

public class Trasa {
    private int id;
    private String nazwa;
    private double odleglosc;
    private double sumaRoznicPoziomow;
    private PunktPosredni punktA;
    private PunktPosredni punktB;
    private GrupaGorska grupaGorska;
    private TerenGorski terenGorski;
    private int punkty;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getOdleglosc() {
        return odleglosc;
    }

    public void setOdleglosc(double odleglosc) {
        this.odleglosc = odleglosc;
    }

    public double getSumaRoznicPoziomow() {
        return sumaRoznicPoziomow;
    }

    public void setSumaRoznicPoziomow(double sumaRoznicPoziomow) {
        this.sumaRoznicPoziomow = sumaRoznicPoziomow;
    }

    public PunktPosredni getPunktA() {
        return punktA;
    }

    public void setPunktA(PunktPosredni punktA) {
        this.punktA = punktA;
    }

    public PunktPosredni getPunktB() {
        return punktB;
    }

    public void setPunktB(PunktPosredni punktB) {
        this.punktB = punktB;
    }

    public GrupaGorska getGrupaGorska() {
        return grupaGorska;
    }

    public void setGrupaGorska(GrupaGorska grupaGorska) {
        this.grupaGorska = grupaGorska;
    }

    public TerenGorski getTerenGorski() {
        return terenGorski;
    }

    public void setTerenGorski(TerenGorski terenGorski) {
        this.terenGorski = terenGorski;
    }

    public int getPunkty() {
        return punkty;
    }

    public void setPunkty(int punkty) {
        this.punkty = punkty;
    }




}
