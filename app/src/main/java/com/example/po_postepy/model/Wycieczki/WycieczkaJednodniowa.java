package com.example.po_postepy.model.Wycieczki;

import androidx.annotation.NonNull;

import com.example.po_postepy.model.Odznaki.GOT;
import com.example.po_postepy.model.Uzytkownicy.Uzytkownik;

import java.util.ArrayList;
import java.util.Date;

public class WycieczkaJednodniowa {
    private int id;
    private String nazwa;
    private Date dataWycieczki;
    private boolean zatwierdzona;
    private boolean odbyta;
    private ArrayList<Trasa> trasy;
    private GOT got;
    private Uzytkownik zdobywajacy;
    private Uzytkownik przodownik;

    private int liczbaPunktow = 0;


    public WycieczkaJednodniowa(int id, String nazwa, Date dataWycieczki, boolean zatwierdzona, boolean odbyta, ArrayList<Trasa> trasy, GOT got, Uzytkownik zdobywajacy, Uzytkownik przodownik) {
        this.id = id;
        this.nazwa = nazwa;
        this.dataWycieczki = dataWycieczki;
        this.zatwierdzona = zatwierdzona;
        this.odbyta = odbyta;
        this.trasy = trasy;
        this.got = got;
        this.zdobywajacy = zdobywajacy;
        this.przodownik = przodownik;
        updatePoints();
    }

    public void updatePoints() {
        liczbaPunktow = 0;
        for (Trasa trasa : trasy) {
            liczbaPunktow += trasa.getPunkty();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Trasa> getTrasy() {
        return trasy;
    }

    public void setTrasy(ArrayList<Trasa> trasy) {
        this.trasy = trasy;
    }

    public GOT getGot() {
        return got;
    }

    public void setGot(GOT got) {
        this.got = got;
    }

    public Uzytkownik getZdobywajacy() {
        return zdobywajacy;
    }

    public void setZdobywajacy(Uzytkownik zdobywajacy) {
        this.zdobywajacy = zdobywajacy;
    }

    public Uzytkownik getPrzodownik() {
        return przodownik;
    }

    public void setPrzodownik(Uzytkownik przodownik) {
        this.przodownik = przodownik;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Date getDataWycieczki() {
        return dataWycieczki;
    }

    public void setDataWycieczki(Date dataWycieczki) {
        this.dataWycieczki = dataWycieczki;
    }

    public boolean isZatwierdzona() {
        return zatwierdzona;
    }

    public void setZatwierdzona(boolean zatwierdzona) {
        this.zatwierdzona = zatwierdzona;
    }

    public boolean isOdbyta() {
        return odbyta;
    }

    public void setOdbyta(boolean odbyta) {
        this.odbyta = odbyta;
    }

    public int getLiczbaPunktow() {
        return liczbaPunktow;
    }

    @NonNull
    @Override
    public String toString() {
        return " "+ String.format("%-15s %-3d", nazwa, liczbaPunktow);
//        return String.format("%1$" + String.valueOf(20-nazwa.length()) + "s", nazwa) + String.format("%1$" + String.valueOf(25-nazwa.length()) + "s", liczbaPunktow);
    }
}
