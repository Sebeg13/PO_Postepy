package com.example.po_postepy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.po_postepy.presenter.PostepyPresenter;
import com.example.po_postepy.view.PostepyView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostepyView {

    private PostepyPresenter presenter;

    private ProgressBar loadingBar;
    private ProgressBar pointsProgressBar;
    private ImageView badgeImage;
    private TextView badgeName;
    private TextView badgePoints;
    private TextView loadingDataText;
    private ImageView wycieczkiPunktujaceImage;

    ArrayAdapter arrayAdapter;

    List<TextView> currTripsAndRoutes;

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar)findViewById(R.id.activity_main_toolbar));


        presenter = new PostepyPresenter(this);

        loadingBar = findViewById(R.id.loadingBar);
        pointsProgressBar = findViewById(R.id.pointsProgressBar);
        badgeImage = findViewById(R.id.badgeImage);
        badgeName = findViewById(R.id.badgeName);
        badgePoints = findViewById(R.id.badgePoints);
        loadingDataText = findViewById(R.id.loadingDataText);
        wycieczkiPunktujaceImage = findViewById(R.id.wycieczkiPunktujaceImg);
        linearLayout = findViewById(R.id.linearLayout);

        currTripsAndRoutes = new ArrayList<>();




        presenter.updateData();
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
    public void showTrips() {
        for(TextView tv : currTripsAndRoutes)
            tv.setVisibility(View.VISIBLE);
        wycieczkiPunktujaceImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideTrips() {
        for(TextView tv : currTripsAndRoutes)
            tv.setVisibility(View.INVISIBLE);
        wycieczkiPunktujaceImage.setVisibility(View.INVISIBLE);
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
    public void setPoints(int currPonts, int maxPoints) {
        String textToDisplay = currPonts+"/"+maxPoints;
        badgePoints.setText(textToDisplay);
        pointsProgressBar.setMax(maxPoints);
        pointsProgressBar.setProgress(currPonts);
    }

    @Override
    public void setBadge(String badgeName, int badgeImage) {
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
    public void setTrips(ArrayList<ArrayList<String>> list) {
        deleteTripTextViews();
        currTripsAndRoutes = new ArrayList<>();
        for(int ii=0; ii<list.size();ii++){
            List<String> trip = list.get(ii);
            TextView textView = new TextView(this);
            textView.setText(trip.get(0));
            textView.setTextSize((float) 30);
            linearLayout.addView(textView);
            currTripsAndRoutes.add(textView);
            for(int jj=1;jj<trip.size();jj++){
                TextView textView2 = new TextView(this);
                textView2.setText(trip.get(jj));
                currTripsAndRoutes.add(textView2);
                linearLayout.addView(textView2);
            }
        }
    }

    @Override
    public void deleteTripTextViews() {
        if(currTripsAndRoutes != null) {
            for (TextView tv : currTripsAndRoutes)
                linearLayout.removeView(tv);
        }
        }


}
