package com.example.po_postepy.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.po_postepy.Constant;
import com.example.po_postepy.R;
import com.example.po_postepy.presenter.PostepyPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa MainActivity to klasa głównej aktywności
 */
public class MainActivity extends AppCompatActivity implements PostepyView {

    private PopupWindow popupWindow;

    private PostepyPresenter presenter;

    private ProgressBar loadingBar;
    private ProgressBar pointsProgressBar;
    private ImageView badgeImage;
    private TextView badgeName;
    private TextView badgePoints;
    private TextView loadingDataText;
    private ImageView scoringTrips;

    List<TextView> currTripsAndRoutes;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar)findViewById(R.id.activity_main_toolbar));


        presenter = new PostepyPresenter(this, getApplicationContext());

        loadingBar = findViewById(R.id.loadingBar);
        pointsProgressBar = findViewById(R.id.pointsProgressBar);
        badgeImage = findViewById(R.id.badgeImage);
        badgeName = findViewById(R.id.badgeName);
        badgePoints = findViewById(R.id.badgePoints);
        loadingDataText = findViewById(R.id.loadingDataText);
        scoringTrips = findViewById(R.id.wycieczkiPunktujaceImg);
        linearLayout = findViewById(R.id.linearLayout);

        currTripsAndRoutes = new ArrayList<>();

        presenter.downloadData();
    }

    @Override
    public void showBadge() {
        badgeImage.setVisibility(View.VISIBLE);
        badgeName.setVisibility(View.VISIBLE);
        badgePoints.setVisibility(View.VISIBLE);
        pointsProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBadge() {
        badgeImage.setVisibility(View.INVISIBLE);
        badgeName.setVisibility(View.INVISIBLE);
        badgePoints.setVisibility(View.INVISIBLE);
        pointsProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showTripsAndRoutes() {
        for(TextView tv : currTripsAndRoutes)
            tv.setVisibility(View.VISIBLE);
        scoringTrips.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTripsAndRoutes() {
        for(TextView tv : currTripsAndRoutes)
            tv.setVisibility(View.INVISIBLE);
        scoringTrips.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingIndicator() {
        loadingBar.setVisibility(View.VISIBLE);
        loadingDataText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        loadingBar.setVisibility(View.INVISIBLE);
        loadingDataText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void displayPoints(int currPonts, int maxPoints) {
        String textToDisplay = currPonts+"/"+maxPoints;
        badgePoints.setText(textToDisplay);
        pointsProgressBar.setMax(maxPoints);
        pointsProgressBar.setProgress(currPonts);
    }

    @Override
    public void displayBadge(String badgeName, int badgeImage) {
        this.badgeName.setText(badgeName);

        switch (badgeImage){
            case Constant.MALA_BRAZOWA:
                this.badgeImage.setImageResource(R.drawable.mala_brazowa);
                break;
            case Constant.DUZA_BRAZOWA:
                //set duza_brazowa
                break;
        }

    }

    @Override
    public void generateTripsAndRoutes(ArrayList<ArrayList<String>> list) {
        deleteTripsAndRoutesTextViews();
        currTripsAndRoutes = new ArrayList<>();
        for(int ii=0; ii<list.size();ii++){
            List<String> trip = list.get(ii);
            TextView textView = new TextView(this);
            textView.setTypeface(Typeface.MONOSPACE);
            textView.setText(trip.get(0));
            textView.setTextSize((float) 30);
            linearLayout.addView(textView);
            currTripsAndRoutes.add(textView);
            for(int jj=1;jj<trip.size();jj++){
                TextView textView2 = new TextView(this);
                textView2.setTextSize(11);
                textView2.setTypeface(Typeface.MONOSPACE);
                textView2.setText(trip.get(jj));
                currTripsAndRoutes.add(textView2);
                linearLayout.addView(textView2);
            }
        }
    }

    @Override
    public void deleteTripsAndRoutesTextViews() {
        if(currTripsAndRoutes != null) {
            for (TextView tv : currTripsAndRoutes)
                linearLayout.removeView(tv);
        }
        }

    @Override
    public void showPopup(String message){
        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context. LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_window,null);

        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(layout);
        popupWindow.setWidth(800);
        popupWindow.setHeight(600);
        popupWindow.setFocusable(true);

        // prevent clickable background
        popupWindow.setBackgroundDrawable(null);

        popupWindow.showAtLocation(layout, Gravity.CENTER, 1, 1);

        TextView txtMessage = (TextView) layout.findViewById(R.id.messageTextView);
        txtMessage.setText(message);

        Button okButton = (Button) layout.findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                presenter.downloadData();
            }
        });

    }


}
