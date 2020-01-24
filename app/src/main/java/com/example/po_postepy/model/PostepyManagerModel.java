package com.example.po_postepy.model;


import android.content.Context;

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

/**
 * Klasa PostepyManagerModel reprezentuję warstwę modelu we wzorcu MVP. Jest odpowiedzialna za komunikację z bazą danych.
 */
public class PostepyManagerModel {

    private int maxPoints = 120;
    private String badgeName = "Mała brązowa";
    private int badgeImage = 1;

    private final String URL_TRIPS = "http://192.168.0.103/po/wycieczki.php";
    private final String URL_ROUTES = "http://192.168.0.103/po/trasy.php";
    private final String URL_POINTS = "http://192.168.0.103/po/punkty.php";

    private PostepyPresenter presenter;
    private Context context;

    /**
     * Kontruktor klasy PostepyManagerModel
     *
     * @param presenter obiekt PostepyPresenter, odpowiedzialny za warstwę prezentacji
     * @param context   kontekst aplikacji
     */
    public PostepyManagerModel(PostepyPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    /**
     * Metoda downloadTrips pobiera wycieczki jednodniowe z bazy danych oraz przekazuje prezenterowi informację o możliwości rozpoczęcia drugiego kroku pobierania danych.
     */
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
                                    String name = object.getString("NazwaWJ").trim();
                                    boolean completed = object.getString("OdbytaWJ").equals("1");
                                    boolean confirmed = object.getString("ZatwierdzonaWJ").equals(1);
                                    presenter.addToOneDayTrips(new WycieczkaJednodniowa(id, name, null, confirmed, completed, new ArrayList<Trasa>(), null, null, null));
                                }
                                presenter.downloadDataPart2();
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

    /**
     * Metoda downloadRoutes pobiera trasy dotyczące wycieczki o podanym id z bazy danych oraz przekazuje prezenterowi informację o możliwości rozpoczęcia trzeciego kroku pobierania danych.
     *
     * @param idTrip id wycieczki, której trasy nalezy pobrać
     */
    public void downloadRoutes(final int idTrip) {

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
                                    String name = object.getString("NazwaT").trim();
                                    int points = object.getInt("Punkty");
                                    presenter.saveRouteIntoTrip(idTrip, new Trasa(id, name, 330, 0, null, null, null, null, points));

                                }
                                presenter.findTripOfId(idTrip).updatePoints();
                                presenter.downloadDataPart3();
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
                params.put("idwycieczki", String.valueOf(idTrip));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    /**
     * Metoda downloadPoints pobiera punkty dotyczące trasy o podanym id z bazy danych oraz przekazuje prezenterowi informację o możliwości rozpoczęcia czwartego kroku pobierania danych.
     *
     * @param idRoute id trasy, której punkty nalezy pobrać
     */
    public void downloadPoints(final int idRoute) {

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
                                    String name = object.getString("NazwaPP").trim();

                                    presenter.savePointIntoRoute(idRoute, new PunktPosredni(id, name, 0));

                                }
                                presenter.downloadDataPart4();
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
                params.put("idtrasy", String.valueOf(idRoute));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public String getBadgeName() {
        return badgeName;
    }


    public int getBadgeImage() {
        return badgeImage;
    }

}
