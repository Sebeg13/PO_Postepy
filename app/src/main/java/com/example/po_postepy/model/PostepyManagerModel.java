package com.example.po_postepy.model;


import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.po_postepy.model.Uzytkownicy.Uzytkownik;
import com.example.po_postepy.model.Wycieczki.PunktPosredni;
import com.example.po_postepy.model.Wycieczki.Trasa;
import com.example.po_postepy.model.Wycieczki.WycieczkaJednodniowa;
import com.example.po_postepy.model.Wycieczki.WycieczkaWielodniowa;
import com.example.po_postepy.presenter.PostepyPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostepyManagerModel {

    private ArrayList<WycieczkaJednodniowa> wycieczkiJednodniowe;
    private ArrayList<WycieczkaWielodniowa> wycieczkiWielodniowe;


    private int maxPoints = 120;
    private int currentPoints = 36;
    private String badgeName = "Mała brązowa";
    private int badgeImage = 1;
    private final String URL_TRIPS = "http://192.168.0.103/po/wycieczki.php";
    private final String URL_ROUTES = "http://192.168.0.103/po/trasy.php";
    private final String URL_POINTS = "http://192.168.0.103/po/punkty.php";

    private PostepyPresenter presenter;
    private Context context;



    private TripsFormater tripsFormater;

    public PostepyManagerModel(PostepyPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    public void downloadTrips() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TRIPS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("wycieczki");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    int id = object.getInt("IdWJ");
                                    String nazwa = object.getString("NazwaWJ").trim();
                                    String data = object.getString("DataWycieczki").trim();
                                    Boolean odbyta = object.getString("OdbytaWJ").equals("1");
                                    Boolean zatwierdzona = object.getString("ZatwierdzonaWJ").equals(1);
                                    int idGot = object.getInt("IdGOT");
                                    int idZdo = object.getInt("IdZdo");
                                    int idPrzo = object.getInt("IdPrzo");

                                    presenter.addToWycieczkiJednodniowe(new WycieczkaJednodniowa(id, nazwa, null, zatwierdzona, odbyta, new ArrayList<Trasa>(), null, null, null));
                                }
                                presenter.updateDataPart2();
                            } else {
//                                presenter.onLoginInfo(jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            presenter.onLoginInfo("Logowanie nie powiodło się! " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        presenter.onLoginInfo("Brak dostępu do bazy danych");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userid", "1");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void downloadRoutes(final int idWycieczki) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_ROUTES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("trasy");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    int id = object.getInt("IdT");
                                    String nazwa = object.getString("NazwaT").trim();
                                    int punkty = object.getInt("Punkty");
                                    presenter.saveRouteIntoTrip(idWycieczki, new Trasa(id,nazwa,330,0,null,null,null,null,punkty));

                                }
                                presenter.findTripOfId(idWycieczki).updatePoints();
                                presenter.updateDataPart3();
                            } else {
//                                presenter.onLoginInfo(jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            presenter.onLoginInfo("Logowanie nie powiodło się! " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        presenter.onLoginInfo("Brak dostępu do bazy danych");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idwycieczki", String.valueOf(idWycieczki));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void downloadPoints(final int idTrasy) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POINTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("punkty");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    int id = object.getInt("IdPP");
                                    String nazwa = object.getString("NazwaPP").trim();

                                    presenter.savePointIntoRoute(idTrasy, new PunktPosredni(id, nazwa, 0));

                                }
                                presenter.updateDataPart4();
                            } else {
//                                presenter.onLoginInfo(jsonObject.getString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            presenter.onLoginInfo("Logowanie nie powiodło się! " + e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        presenter.onLoginInfo("Brak dostępu do bazy danych");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idtrasy", String.valueOf(idTrasy));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    public void downloadData() {
        //Here will be downloading data from database through web server instead of hardcoded data

        wycieczkiJednodniowe = new ArrayList<>();

        Uzytkownik zdobywajacy = new Uzytkownik();
        Uzytkownik przodownik = new Uzytkownik();

//        TypGOT typGOT = new TypGOT(1,"Mała brązowa", 120);
//
//        GOT got = new GOT(1, 36,typGOT,new Uzytkownik());
//
//        PunktPosredni pp1 = new PunktPosredni(1, "Punkt1", 1200);
//        PunktPosredni pp2 = new PunktPosredni(2, "Punkt2", 1754);
//        Trasa trasa1 = new Trasa(1, "Trasa1",240,800,pp1,pp2, GrupaGorska.BW_A, TerenGorski.BeskidyWschodnie,7);
//        TrasaWycieczkiJednodniowej trasaWJ1 = new TrasaWycieczkiJednodniowej(1, trasa1);
//        PunktPosredni pp3 = new PunktPosredni(3, "Punkt3", 1654);
//        Trasa trasa2 = new Trasa(2, "Trasa1",645,70,pp2,pp3, GrupaGorska.BW_A, TerenGorski.BeskidyWschodnie,12);
//        TrasaWycieczkiJednodniowej trasaWJ2 = new TrasaWycieczkiJednodniowej(2, trasa2);
//        ArrayList<TrasaWycieczkiJednodniowej> trasy1 = new ArrayList<>(2);
//        trasy1.add(trasaWJ1);
//        trasy1.add(trasaWJ2);
//
//        WycieczkaJednodniowa wycieczka1 =
//                new WycieczkaJednodniowa(1,"wycieczka1",new Date(2019,6,12),true,true,trasy1,got,zdobywajacy,przodownik);
//
//        wycieczkiJednodniowe.add(wycieczka1);
//
//
//        PunktPosredni pp4 = new PunktPosredni(1, "Punkt1", 1200);
//        PunktPosredni pp5 = new PunktPosredni(2, "Punkt2", 1754);
//        Trasa trasa3 = new Trasa(1, "Trasa1",240,800,pp4,pp5, GrupaGorska.BW_A, TerenGorski.BeskidyWschodnie,8);
//        TrasaWycieczkiJednodniowej trasaWJ3 = new TrasaWycieczkiJednodniowej(1, trasa3);
//        PunktPosredni pp6 = new PunktPosredni(3, "Punkt3", 1654);
//        Trasa trasa4 = new Trasa(2, "Trasa1",645,70,pp5,pp6, GrupaGorska.BW_A, TerenGorski.BeskidyWschodnie,9);
//        TrasaWycieczkiJednodniowej trasaWJ4 = new TrasaWycieczkiJednodniowej(2, trasa4);
//        ArrayList<TrasaWycieczkiJednodniowej> trasy2 = new ArrayList<>(2);
//        trasy2.add(trasaWJ3);
//        trasy2.add(trasaWJ4);
//
//        WycieczkaJednodniowa wycieczka2 =
//                new WycieczkaJednodniowa(2,"wycieczka2",new Date(2019,6,12),true,true,trasy2,got,zdobywajacy,przodownik);
//
//        wycieczkiJednodniowe.add(wycieczka2);
//
//        Log.d("w1:",String.valueOf(wycieczka1.getTrasy().size()));
//
//
//        maxPoints = typGOT.getWymaganePunkty();
//        currentPoints = got.getPunktyZdobyte();
//        badgeName = typGOT.getRodzaj();
//        badgeImage = typGOT.getId();

//        maxPoints = 120;
//        currentPoints = 36;
//        badgeName = "Mała brązowa";
//        badgeImage = 1;


    }


    public ArrayList<ArrayList<String>> getFormattedTrips() {
        tripsFormater = new TripsFormater();
        return tripsFormater.formatTrips(presenter.getWycieczkiJednodniowe());
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

    public int getBadgeImage() {
        return badgeImage;
    }

    public void setBadgeImage(int badgeImage) {
        this.badgeImage = badgeImage;
    }

    public ArrayList<WycieczkaJednodniowa> getWycieczkiJednodniowe() {
        return wycieczkiJednodniowe;
    }
}
