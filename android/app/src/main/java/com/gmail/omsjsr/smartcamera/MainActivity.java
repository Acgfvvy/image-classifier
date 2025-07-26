package com.gmail.omsjsr.smartcamera;

import com.getcapacitor.BridgeActivity;
import android.os.Bundle;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import android.widget.FrameLayout;
import android.view.Gravity;
import android.view.ViewGroup;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize MobileAds and only show banner after init is complete
        MobileAds.initialize(this, initializationStatus -> {
            addAdMobBannerSafe();
        });
    }

    private void addAdMobBannerSafe() {
        try {
            // Create AdView
            AdView adView = new AdView(this);
            adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111"); // Test ID
            adView.setAdSize(AdSize.BANNER);

            // Create layout params for the banner at the bottom
            FrameLayout.LayoutParams adParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM
            );

            // Add the AdView to the root view
            FrameLayout rootView = (FrameLayout) this.getWindow().getDecorView().findViewById(android.R.id.content);
            rootView.addView(adView, adParams);

            // Load the ad
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        } catch (Exception e) {
            e.printStackTrace();
            android.util.Log.e("AdMob", "Failed to add AdView: " + e.getMessage());
        }
    }
}

