package com.example.po_postepy.model;

import android.util.Log;

import com.example.po_postepy.model.Wycieczki.Trasa;
import com.example.po_postepy.model.Wycieczki.WycieczkaJednodniowa;

import java.util.ArrayList;

public class TripsFormater {

    public ArrayList<ArrayList<String>> formatTrips(ArrayList<WycieczkaJednodniowa> wycieczki){

        ArrayList<ArrayList<String>> resultList = new ArrayList<>();
        for (int ii=0;ii<wycieczki.size();ii++){
            ArrayList<String> sublist = new ArrayList<>(); // index 0 is name of the trip. Next indexes are route names.
            sublist.add(wycieczki.get(ii).toString());
            ArrayList<Trasa> trasy = wycieczki.get(ii).getTrasy();
            Log.d("WycieczkaFT",wycieczki.get(ii).toString());
            for(int jj=0; jj<trasy.size();jj++){
                sublist.add(trasy.get(jj).toString());
                Log.d("trasaFT",trasy.get(jj).toString());
            }
            resultList.add(sublist);
        }


        return resultList;
    }

}
