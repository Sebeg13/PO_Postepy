package com.example.po_postepy.view;


import java.util.ArrayList;

/**
 * Interfer PostepyView reprezentuje warstwę widoku we wzorcu MVP.
 */
public interface PostepyView{


    /**
     * Metoda showBadge wyświetla na ekranie informacje o odznace.
     */
    public void showBadge();

    /**
     * Metoda hideBadge ukrywa z ekranu informacje o odznacę.
     */
    public void hideBadge();

    /**
     * Metoda showTripsAndRoutes wyświetla na ekranie inforamcje o wycieczkach i ich trasach.
     */
    public void showTripsAndRoutes();

    /**
     * Metoda hideTripsAndRoutes ukrywa z ekranu informacje o wycieczkach i ich trasach.
     */
    public void hideTripsAndRoutes();

    /**
     * Metoda showLoadingIndicator wyświetla na ekranie kontrolkę symbolizującą proces ładowania.
     */
    public void showLoadingIndicator();

    /**
     * Metoda hideLoadingIndicator wyświetla ukrywa z ekranu kontrolkę symbolizującą proces ładowania.
     */
    public void hideLoadingIndicator();

    /**
     * Metoda displayPoints wyświetla na ekranie informacje o liczbie punktów zdobytych na liczbę punktów do zdobycia.
     * @param currPonts liczba punktów zdobytych
     * @param maxPoints liczba punktów do zdobycia
     */
    public void displayPoints(int currPonts, int maxPoints);

    /**
     * Metoda displayBadge wyświetla na ekranie informacje o odznacę
     * @param badgeName nazwa odznaki
     * @param badgeImage numer odpowiadający obrazowi odznaki
     */
    public void displayBadge(String badgeName, int badgeImage);

    /**
     * Metoda generateTripsAndRoutes generuje na ekranie informacje o wycieczkach i ich trasach.
     * @param list lista wycieczek i ich tras
     */
    public void generateTripsAndRoutes(ArrayList<ArrayList<String>> list);

    /**
     * Metoda deleteTripsAndRoutesTextViews usuwa dane widoku związane z wycieczkami i ich trasami.
     */
    public void deleteTripsAndRoutesTextViews();

    /**
     * Metoda showPopup wyświetla na ekranie wiadomość.
     * @param message wiadomość do wyświetlenia
     */
    public void showPopup(String message);

}
