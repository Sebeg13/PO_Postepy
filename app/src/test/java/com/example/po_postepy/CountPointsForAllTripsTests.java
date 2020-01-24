package com.example.po_postepy;

import com.example.po_postepy.model.Wycieczki.PunktPosredni;
import com.example.po_postepy.model.Wycieczki.Trasa;
import com.example.po_postepy.model.Wycieczki.WycieczkaJednodniowa;
import com.example.po_postepy.presenter.PostepyPresenter;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Klasa CountPointsForAllTripsTests wykonuje testy jednostkowe dla metody getPointsForAllTrips z klasy PostepyPresenter.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CountPointsForAllTripsTests {

    private PunktPosredni pointA = new PunktPosredni(1, "PunktA", -1 );
    private PunktPosredni pointB = new PunktPosredni(2, "PunktB", -1);
    private PunktPosredni pointC = new PunktPosredni(3, "PunktC", -1);
    private PunktPosredni pointD = new PunktPosredni(4, "PunktD", -1);

    private Trasa routeAB = new Trasa(1, "TrasaAB", -1, -1, pointA, pointB, null, null, 5);
    private Trasa routeBA = new Trasa(2, "TrasaBA", -1, -1, pointB, pointA, null, null, 9);
    private Trasa routeBC = new Trasa(3, "TrasaBC", -1, -1, pointB, pointC, null, null, 13);
    private Trasa routeCB = new Trasa(4, "TrasaCB", -1, -1, pointC, pointB, null, null, 7);
    private Trasa routeCD = new Trasa(5, "TrasaCD", -1, -1, pointC, pointD, null, null, 4);


    private WycieczkaJednodniowa trip1 = new WycieczkaJednodniowa(1, "Wycieczka1", null, true, true,null,null,null,null );
    private WycieczkaJednodniowa trip2 = new WycieczkaJednodniowa(2, "Wycieczka2", null, true, true,null,null,null,null );

    /**
     * Test jednostkowy dla przypadku z pustą listą wycieczek
     */
    @Test
    public void countPointsForAllTripsZeroTrips(){
        ArrayList<WycieczkaJednodniowa> trips = new ArrayList<>();
        assertEquals(0, PostepyPresenter.getPointForAllTrips(trips));
    }

    /**
     * Test jednostkowy dla przypadku z listą wycieczek z jedną wycieczką
     */
    @Test
    public void countPointsForAllTripsOneTrip(){
        ArrayList<WycieczkaJednodniowa> trips = new ArrayList<>();

        ArrayList<Trasa> routes1 = new ArrayList<>();
        routes1.add(routeAB);
        routes1.add(routeBC);
        routes1.add(routeCD);
        trip1.setTrasy(routes1);
        trips.add(trip1);

        assertEquals(22, PostepyPresenter.getPointForAllTrips(trips));
    }

    /**
     * Test jednostkowy dla przypadku z listą wycieczek z dwoma wycieczkami.
     */
    @Test
    public void countPointsForAllTripsTwoTrips(){
        ArrayList<WycieczkaJednodniowa> trips = new ArrayList<>();

        ArrayList<Trasa> routes1 = new ArrayList<>();
        routes1.add(routeAB);
        routes1.add(routeBC);
        routes1.add(routeCD);
        trip1.setTrasy(routes1);
        trips.add(trip1);

        ArrayList<Trasa> routes2 = new ArrayList<>();
        routes2.add(routeCB);
        routes2.add(routeBA);
        trip2.setTrasy(routes2);
        trips.add(trip2);
        assertEquals(38, PostepyPresenter.getPointForAllTrips(trips));
    }
}
