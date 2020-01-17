package com.example.po_postepy.view;


import java.util.ArrayList;


public interface PostepyView{

    public void showBadge();
    public void hideBadge();
    public void showTrips();
    public void hideTrips();
    public void showLoadingIndicator();
    public void hideLoadingIndicator();
    public void setPoints(int currPonts, int maxPoints);
    public void setBadge(String badgeName, int badgeImage);
    public void setTrips(ArrayList<ArrayList<String>> list);

}
