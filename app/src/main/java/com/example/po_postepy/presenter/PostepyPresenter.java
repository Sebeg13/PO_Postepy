package com.example.po_postepy.presenter;

import android.content.Context;
import android.icu.text.LocaleDisplayNames;
import android.os.Handler;
import android.util.Log;

import com.example.po_postepy.model.PostepyManagerModel;
import com.example.po_postepy.model.Wycieczki.PunktPosredni;
import com.example.po_postepy.model.Wycieczki.Trasa;
import com.example.po_postepy.model.Wycieczki.WycieczkaJednodniowa;
import com.example.po_postepy.view.PostepyView;

import java.util.ArrayList;

public class PostepyPresenter {

    private PostepyManagerModel model;
    private PostepyView view;

    private ArrayList<WycieczkaJednodniowa> wycieczkiJednodniowe;

    public PostepyPresenter(PostepyView view, Context context) {
        this.model = new PostepyManagerModel(this, context);
        this.view = view;
    }


    public void updateData() {
        view.hideBadge();
        view.hideTrips();
        view.showLoadingIndicator();


//        //This part is delayed in order to simulate downloading data from database
//        Handler myHandler = new Handler();
//        myHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
        model.downloadTrips();

//            }


//        }, 3000);

    }

    public void updateDataPart2() {
        for (WycieczkaJednodniowa wycieczkaJednodniowa : wycieczkiJednodniowe)
            model.downloadRoutes(wycieczkaJednodniowa.getId());


    }

    public void updateDataPart3() {
        for (WycieczkaJednodniowa wycieczkaJednodniowa : wycieczkiJednodniowe) {
            for (Trasa trasa : wycieczkaJednodniowa.getTrasy()) {
                model.downloadPoints(trasa.getId());
            }
        }
    }

    public void updateDataPart4() {
        view.setBadge(model.getBadgeName(), model.getBadgeImage());
        view.setPoints(model.getCurrentPoints(), model.getMaxPoints());
        view.setTrips(model.getFormattedTrips());

        view.hideLoadingIndicator();
        view.showBadge();
        view.showTrips();
    }

    public void addToWycieczkiJednodniowe(WycieczkaJednodniowa wycieczkaJednodniowa) {
        if (wycieczkiJednodniowe == null)
            wycieczkiJednodniowe = new ArrayList<>();

        wycieczkiJednodniowe.add(wycieczkaJednodniowa);

    }

    public void saveRouteIntoTrip(int idWycieczki, Trasa trasa) {
        WycieczkaJednodniowa wycieczka = findTripOfId(idWycieczki);
        if (wycieczka != null) {
            wycieczka.getTrasy().add(trasa);
        }

//        wycieczkiJednodniowe.get(idWycieczki).getTrasy().add(trasa);
    }

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
        Log.d("Trasa po dodaniu", route.toString());
    }

    public ArrayList<WycieczkaJednodniowa> getWycieczkiJednodniowe() {
        return wycieczkiJednodniowe;
    }


    public WycieczkaJednodniowa findTripOfId(int idWycieczki) {
        WycieczkaJednodniowa wycieczka = null;
        for (WycieczkaJednodniowa wyc : wycieczkiJednodniowe) {
            if (wyc.getId() == idWycieczki) {
                wycieczka = wyc;
                break;
            }
        }
        return wycieczka;
    }

    public Trasa findRouteOfId(int idRoute) {
        Trasa trasa = null;
        for (WycieczkaJednodniowa wyc : wycieczkiJednodniowe) {
            for (Trasa tra : wyc.getTrasy()) {
                if (tra.getId() == idRoute) {
                    trasa = tra;
                    break;
                }
            }
        }
        return trasa;
    }
}
