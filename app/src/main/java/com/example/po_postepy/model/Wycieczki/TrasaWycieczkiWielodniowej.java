package com.example.po_postepy.model.Wycieczki;

import java.util.Date;

public class TrasaWycieczkiWielodniowej {

    private int id;
    private WycieczkaWielodniowa wycieczkaWielodniowa;
    private Trasa trasa;
    private Date dataTWJ;

    public WycieczkaWielodniowa getWycieczkaWielodniowa() {
        return wycieczkaWielodniowa;
    }

    public void setWycieczkaWielodniowa(WycieczkaWielodniowa wycieczkaWielodniowa) {
        this.wycieczkaWielodniowa = wycieczkaWielodniowa;
    }

    public Date getDataTWJ() {
        return dataTWJ;
    }

    public void setDataTWJ(Date dataTWJ) {
        this.dataTWJ = dataTWJ;
    }

    public Trasa getTrasa() {
        return trasa;
    }

    public void setTrasa(Trasa trasa) {
        this.trasa = trasa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
