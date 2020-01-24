package com.example.po_postepy.presenter;

import android.content.Context;
import android.util.Log;

import com.example.po_postepy.model.PostepyManagerModel;
import com.example.po_postepy.model.TripsFormater;
import com.example.po_postepy.model.Wycieczki.PunktPosredni;
import com.example.po_postepy.model.Wycieczki.Trasa;
import com.example.po_postepy.model.Wycieczki.WycieczkaJednodniowa;
import com.example.po_postepy.view.PostepyView;

import java.util.ArrayList;

/**
 *  Klasa PostepyPresenter reprezentuję warstwę prezentacji we wzorcu MVP.
 */
public class PostepyPresenter {

    private PostepyManagerModel model;
    private PostepyView view;

    private ArrayList<WycieczkaJednodniowa> oneDayTrips;

    public PostepyPresenter(PostepyView view, Context context) {
        this.model = new PostepyManagerModel(this, context);
        this.view = view;
    }

    /**
     * Metoda downloadData rozpoczyna, poprzez model, pobieranie wycieczek, tras i punktów pośrednich z bazy danych.
     */
    public void downloadData() {
        view.hideBadge();
        view.hideTrips();
        view.showLoadingIndicator();

        model.downloadTrips();
    }

    /**
     * Metoda downloadDataPart2 jest drugim krokiem pobierania wycieczek, tras i punktów pośrednich z bazy danych.
     */
    public void downloadDataPart2() {
        for (WycieczkaJednodniowa oneDayTrip : oneDayTrips)
            model.downloadRoutes(oneDayTrip.getId());
    }

    /**
     * Metoda downloadDataPart3 jest trzecim krokiem pobierania wycieczek, tras i punktów pośrednich z bazy danych.
     */
    public void downloadDataPart3() {
        for (WycieczkaJednodniowa oneDayTrip : oneDayTrips) {
            for (Trasa route : oneDayTrip.getTrasy()) {
                model.downloadPoints(route.getId());
            }
        }
    }

    /**
     * Metoda downloadDataPart4 jest czwartym krokiem pobierania wycieczek, tras i punktów pośrednich z bazy danych.
     * Po pobraniu danych, przekazuje je do modelu i informuje o konieczności wyświetlenia.
     */
    public void downloadDataPart4() {
        view.displayBadge(model.getBadgeName(), model.getBadgeImage());
        view.displayPoints(getPointForAllTrips(), model.getMaxPoints());

        TripsFormater tripsFormater = new TripsFormater();
        view.displayTripsAndRoutes(tripsFormater.formatTrips(getOneDayTrips()));

        view.hideLoadingIndicator();
        view.showBadge();
        view.showTrips();
    }

    /**
     * Metoda addToOneDayTrips dodaje wycieczkę jednodniową do listy wycieczek jednodniowych.
     * @param oneDayTrip wycieczka jednodniowa do dodania
     */
    public void addToOneDayTrips(WycieczkaJednodniowa oneDayTrip) {
        if (oneDayTrips == null)
            oneDayTrips = new ArrayList<>();

        oneDayTrips.add(oneDayTrip);

    }

    /**
     * Metoda saveRouteIntoTrip zapisuje trasę w wycieczce jednodniowej.
     * @param idTrip id wycieczki jednoniowej, w której ma być zapisana trasa
     * @param route trasa do zapisania
     */
    public void saveRouteIntoTrip(int idTrip, Trasa route) {
        WycieczkaJednodniowa trip = findTripOfId(idTrip);
        if (trip != null) {
            trip.getTrasy().add(route);
        }
    }

    /**
     * Metoda savePointIntroRoute zapisuje punkt pośredni w trasie.
     * @param idRoute id trasy, w której ma być zapsiany punkt
     * @param intermediatePoint punkt pośredni do zapisana
     */
    public void savePointIntoRoute(int idRoute, PunktPosredni intermediatePoint) {
        Trasa route = findRouteOfId(idRoute);
        if (route != null) {
            if (route.getPunktA() == null) {
                route.setPunktA(intermediatePoint);
                route.setNazwa(intermediatePoint.getNazwa());
            } else if (route.getPunktB() == null){
                route.setPunktB(intermediatePoint);
                route.setNazwa(route.getNazwa() + " - " + intermediatePoint.getNazwa());
            }
        }
    }

    public ArrayList<WycieczkaJednodniowa> getOneDayTrips() {
        return oneDayTrips;
    }


    /**
     * Metoda findTripOfId znajduje w liscie wycieczek wycieczkę o podanym id.
     * @param idWycieczki id wycieczki, która ma być znaleziona
     * @return znaleziona wycieczka
     */
    public WycieczkaJednodniowa findTripOfId(int idWycieczki) {
        WycieczkaJednodniowa wycieczka = null;
        for (WycieczkaJednodniowa wyc : oneDayTrips) {
            if (wyc.getId() == idWycieczki) {
                wycieczka = wyc;
                break;
            }
        }
        return wycieczka;
    }

    /**
     * Metoda findRouteOfId znajduje w liscie tras wycieczek, trasę o podanym id/
     * @param idRoute id trasy, która ma być znaleziona
     * @return znaleziona trasa
     */
    public Trasa findRouteOfId(int idRoute) {
        Trasa trasa = null;
        for (WycieczkaJednodniowa wyc : oneDayTrips) {
            for (Trasa tra : wyc.getTrasy()) {
                if (tra.getId() == idRoute) {
                    trasa = tra;
                    break;
                }
            }
        }
        return trasa;
    }

    /**
     * Metoda getPointsForAllTrips zwraca sumę punktów zdobytych podczas wszystkich wycieczek.
     * @return suma punktów zdobytych podczas wszystkich wycieczek
     */
    public int getPointForAllTrips(){
        int points = 0;
        for(WycieczkaJednodniowa trip : oneDayTrips){
            points+= trip.getLiczbaPunktow();
        }
        return points;
    }

    /**
     * Metoda showPopup przekazuje widokowi wiadomość, którą ma wyświetlić.
     * @param message wiadomość do wyświetlenia
     */
    public void showPopup(String message){
        view.showPopup(message);
        view.hideLoadingIndicator();
    }
}
