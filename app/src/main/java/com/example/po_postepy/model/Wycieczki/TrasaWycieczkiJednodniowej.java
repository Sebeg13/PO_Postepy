package com.example.po_postepy.model.Wycieczki;


public class TrasaWycieczkiJednodniowej {

    private int id;
    private WycieczkaJednodniowa wycieczkaJednodniowa;
    private Trasa trasa;

    public TrasaWycieczkiJednodniowej(int id, WycieczkaJednodniowa wycieczkaJednodniowa, Trasa trasa) {
        this.id = id;
        this.wycieczkaJednodniowa = wycieczkaJednodniowa;
        this.trasa = trasa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WycieczkaJednodniowa getWycieczkaJednodniowa() {
        return wycieczkaJednodniowa;
    }

    public void setWycieczkaJednodniowa(WycieczkaJednodniowa wycieczkaJednodniowa) {
        this.wycieczkaJednodniowa = wycieczkaJednodniowa;
    }

    public Trasa getTrasa() {
        return trasa;
    }

    public void setTrasa(Trasa trasa) {
        this.trasa = trasa;
    }
}
