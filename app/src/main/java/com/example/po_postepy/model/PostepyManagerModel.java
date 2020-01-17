package com.example.po_postepy.model;


import android.os.Handler;
import android.util.Log;

import com.example.po_postepy.model.Obszary.GrupaGorska;
import com.example.po_postepy.model.Obszary.TerenGorski;
import com.example.po_postepy.model.Odznaki.GOT;
import com.example.po_postepy.model.Odznaki.TypGOT;
import com.example.po_postepy.model.Uzytkownicy.Uzytkownik;
import com.example.po_postepy.model.Wycieczki.PunktPosredni;
import com.example.po_postepy.model.Wycieczki.Trasa;
import com.example.po_postepy.model.Wycieczki.TrasaWycieczkiJednodniowej;
import com.example.po_postepy.model.Wycieczki.WycieczkaJednodniowa;
import com.example.po_postepy.model.Wycieczki.WycieczkaWielodniowa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostepyManagerModel {

    private ArrayList<WycieczkaJednodniowa> wycieczkiJednodniowe;
    private ArrayList<WycieczkaWielodniowa> wycieczkiWielodniowe;


    private int maxPoints;
    private int currentPoints;
    private String badgeName;
    private int bagdeImage; //images of badges will be stored in app as files, and in
                            // database there will be only id of image which should be used

    private TripsFormater tripsFormater;

    public void downloadData(){
        //Here will be downloading data from database through web server instead of hardcoded data

        wycieczkiJednodniowe = new ArrayList<>();

        Uzytkownik zdobywajacy = new Uzytkownik();
        Uzytkownik przodownik = new Uzytkownik();

        TypGOT typGOT = new TypGOT(1,"Mała brązowa", 120);

        GOT got = new GOT(1, 36,typGOT,new Uzytkownik());

        PunktPosredni pp1 = new PunktPosredni(1, "Punkt1", 1200);
        PunktPosredni pp2 = new PunktPosredni(2, "Punkt2", 1754);
        Trasa trasa1 = new Trasa(1, "Trasa1",240,800,pp1,pp2, GrupaGorska.BW_A, TerenGorski.BeskidyWschodnie,7);
        TrasaWycieczkiJednodniowej trasaWJ1 = new TrasaWycieczkiJednodniowej(1, trasa1);
        PunktPosredni pp3 = new PunktPosredni(3, "Punkt3", 1654);
        Trasa trasa2 = new Trasa(2, "Trasa1",645,70,pp2,pp3, GrupaGorska.BW_A, TerenGorski.BeskidyWschodnie,12);
        TrasaWycieczkiJednodniowej trasaWJ2 = new TrasaWycieczkiJednodniowej(2, trasa2);
        ArrayList<TrasaWycieczkiJednodniowej> trasy1 = new ArrayList<>(2);
        trasy1.add(trasaWJ1);
        trasy1.add(trasaWJ2);

        WycieczkaJednodniowa wycieczka1 =
                new WycieczkaJednodniowa(1,"wycieczka1",new Date(2019,6,12),true,true,trasy1,got,zdobywajacy,przodownik);

        wycieczkiJednodniowe.add(wycieczka1);


        PunktPosredni pp4 = new PunktPosredni(1, "Punkt1", 1200);
        PunktPosredni pp5 = new PunktPosredni(2, "Punkt2", 1754);
        Trasa trasa3 = new Trasa(1, "Trasa1",240,800,pp4,pp5, GrupaGorska.BW_A, TerenGorski.BeskidyWschodnie,8);
        TrasaWycieczkiJednodniowej trasaWJ3 = new TrasaWycieczkiJednodniowej(1, trasa3);
        PunktPosredni pp6 = new PunktPosredni(3, "Punkt3", 1654);
        Trasa trasa4 = new Trasa(2, "Trasa1",645,70,pp5,pp6, GrupaGorska.BW_A, TerenGorski.BeskidyWschodnie,9);
        TrasaWycieczkiJednodniowej trasaWJ4 = new TrasaWycieczkiJednodniowej(2, trasa4);
        ArrayList<TrasaWycieczkiJednodniowej> trasy2 = new ArrayList<>(2);
        trasy2.add(trasaWJ3);
        trasy2.add(trasaWJ4);

        WycieczkaJednodniowa wycieczka2 =
                new WycieczkaJednodniowa(2,"wycieczka2",new Date(2019,6,12),true,true,trasy2,got,zdobywajacy,przodownik);

        wycieczkiJednodniowe.add(wycieczka2);

        Log.d("w1:",String.valueOf(wycieczka1.getTrasy().size()));


        maxPoints = typGOT.getWymaganePunkty();
        currentPoints = got.getPunktyZdobyte();
        badgeName = typGOT.getRodzaj();
        bagdeImage = typGOT.getId();

//        maxPoints = 120;
//        currentPoints = 36;
//        badgeName = "Mała brązowa";
//        bagdeImage = 1;

    }

    public ArrayList<ArrayList<String>> getFormattedTrips(){
        tripsFormater = new TripsFormater();
        return tripsFormater.formatTrips(wycieczkiJednodniowe);
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public int getBagdeImage() {
        return bagdeImage;
    }

    public void setBagdeImage(int bagdeImage) {
        this.bagdeImage = bagdeImage;
    }

    public ArrayList<WycieczkaJednodniowa> getWycieczkiJednodniowe() {
        return wycieczkiJednodniowe;
    }
}
