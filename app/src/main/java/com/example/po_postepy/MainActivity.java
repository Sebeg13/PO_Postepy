package com.example.po_postepy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.po_postepy.presenter.PostepyPresenter;
import com.example.po_postepy.view.PostepyView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements PostepyView {

    private PostepyPresenter presenter;

    private ProgressBar loadingBar;
    private ProgressBar pointsProgressBar;
    private ImageView badgeImage;
    private TextView badgeName;
    private TextView badgePoints;
    private TextView loadingDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        presenter = new PostepyPresenter(this);

        loadingBar = findViewById(R.id.loadingBar);
        pointsProgressBar = findViewById(R.id.pointsProgressBar);
        badgeImage = findViewById(R.id.badgeImage);
        badgeName = findViewById(R.id.badgeName);
        badgePoints = findViewById(R.id.badgePoints);
        loadingDataText = findViewById(R.id.loadingDataText);

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

    }

    @Override
    public void hideTrips() {

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
}
