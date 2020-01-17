package com.example.po_postepy.model;


import android.os.Handler;

import com.example.po_postepy.model.Wycieczki.WycieczkaJednodniowa;
import com.example.po_postepy.model.Wycieczki.WycieczkaWielodniowa;

import java.util.ArrayList;
import java.util.Date;

public class PostepyManagerModel {

    private ArrayList<WycieczkaJednodniowa> wycieczkiJednodniowe;
    private ArrayList<WycieczkaWielodniowa> wycieczkiWielodniowe;


    private int maxPoints;
    private int currentPoints;
    private String badgeName;
    private int bagdeImage; //images of badges will be stored in app as files, and in
                            // database there will be only id of image which should be used


    public void downloadData(){
        //Here will be downloading data from database through web server instead of hardcoded data

        wycieczkiJednodniowe = new ArrayList<>();

//        wycieczkiJednodniowe.add(new WycieczkaJednodniowa(1,"wycieczka1",new Date(2019,6,12),true,true, ));

        maxPoints = 120;
        currentPoints = 36;
        badgeName = "Mała brązowa";
        bagdeImage = 1;

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

}
