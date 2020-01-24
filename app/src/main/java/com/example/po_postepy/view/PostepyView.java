package com.example.po_postepy.view;


import java.util.ArrayList;


public interface PostepyView{

    public void showBadge();
    public void hideBadge();
    public void showTrips();
    public void hideTrips();
    public void showLoadingIndicator();
    public void hideLoadingIndicator();
    public void displayPoints(int currPonts, int maxPoints);
    public void displayBadge(String badgeName, int badgeImage);
    public void displayTripsAndRoutes(ArrayList<ArrayList<String>> list);
    public void deleteTripTextViews();
    public void showPopup(String message);

}
