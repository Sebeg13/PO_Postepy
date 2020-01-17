package com.example.po_postepy.model.Odznaki;

public class TypGOT {

    private int id;
    private String rodzaj;
    private int wymaganePunkty;


//    WGoryBrazowa, WGorySrebrna, WGoryZlota,
//    Popularna, MalaBrazowa, MalaSrebrna, MalaZlota,
//    DuzaBrazowa, DuzaSrebrna, DuzaZlota,
//    ZaWytrwaloscMala, ZaWytrwaloscDuza;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public int getWymaganePunkty() {
        return wymaganePunkty;
    }

    public void setWymaganePunkty(int wymaganePunkty) {
        this.wymaganePunkty = wymaganePunkty;
    }
}
