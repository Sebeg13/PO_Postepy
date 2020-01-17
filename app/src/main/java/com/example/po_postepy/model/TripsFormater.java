package com.example.po_postepy.model;

import android.util.Log;

import com.example.po_postepy.model.Wycieczki.TrasaWycieczkiJednodniowej;
import com.example.po_postepy.model.Wycieczki.WycieczkaJednodniowa;

import java.util.ArrayList;
import java.util.List;

public class TripsFormater {

    public ArrayList<ArrayList<String>> formatTrips(ArrayList<WycieczkaJednodniowa> wycieczki){

        ArrayList<ArrayList<String>> resultList = new ArrayList<>();
        for (int ii=0;ii<wycieczki.size();ii++){
            ArrayList<String> sublist = new ArrayList<>(); // index 0 is name of the trip. Next indexes are route names.
            sublist.add(wycieczki.get(ii).toString());
            ArrayList<TrasaWycieczkiJednodniowej> trasy = wycieczki.get(ii).getTrasy();
            Log.d("Wycieczka:",wycieczki.get(ii).toString());
            for(int jj=0; jj<trasy.size();jj++){
                sublist.add(trasy.get(jj).getTrasa().toString());
                Log.d("trasa:",trasy.get(jj).getTrasa().toString());
            }
            resultList.add(sublist);
        }


        return resultList;
    }

}
