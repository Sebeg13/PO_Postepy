package com.example.po_postepy.presenter;

import android.os.Handler;

import com.example.po_postepy.model.PostepyManagerModel;
import com.example.po_postepy.view.PostepyView;

public class PostepyPresenter {

    private PostepyManagerModel model;
    private PostepyView view;

    public PostepyPresenter(PostepyView view) {
        this.model = new PostepyManagerModel();
        this.view = view;
    }


    public void updateData() {
        view.hideBadge();
        view.hideTrips();
        view.showLoadingIndicator();


        //This part is delayed in order to simulate downloading data from database
        Handler myHandler = new Handler();
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                model.downloadData();
                view.setBadge(model.getBadgeName(), model.getBagdeImage());
                view.setPoints(model.getCurrentPoints(), model.getMaxPoints());

                view.hideLoadingIndicator();
                view.showBadge();
                view.showTrips();
            }
        }, 3000);



    }

}
