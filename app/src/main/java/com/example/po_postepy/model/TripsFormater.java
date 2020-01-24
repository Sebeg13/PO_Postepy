package com.example.po_postepy.model;

import com.example.po_postepy.model.Wycieczki.Trasa;
import com.example.po_postepy.model.Wycieczki.WycieczkaJednodniowa;

import java.util.ArrayList;

/**
 * Klasa TripsFormater odpowiada za sformatowanie wycieczek jednodniowych i ich tras w sposób gotowy do wyświetlenia przez wartswę widoku.
 */
public class TripsFormater {

    /**
     * metoda formatTrips formatuje wycieczki jednoniowe i ich trasy do postaci pozwalającej widokowi na ich wyświetlenie.
     * @param trips lista wycieczek do sformatowania
     * @return lista list wycieczek jednodniowych i ich tras
     */
    public ArrayList<ArrayList<String>> formatTrips(ArrayList<WycieczkaJednodniowa> trips){

        ArrayList<ArrayList<String>> resultList = new ArrayList<>();
        for (int ii=0;ii<trips.size();ii++){
            ArrayList<String> sublist = new ArrayList<>(); // index 0 is name of the trip. Next indexes are routes names.
            sublist.add(trips.get(ii).toString());
            ArrayList<Trasa> trasy = trips.get(ii).getTrasy();
            for(int jj=0; jj<trasy.size();jj++){
                sublist.add(trasy.get(jj).toString());
            }
            resultList.add(sublist);
        }

        return resultList;
    }

}
