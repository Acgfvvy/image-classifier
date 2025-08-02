package com.gmail.omsjsr.smartcamera;

import com.getcapacitor.BridgeActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.view.Gravity;
import android.view.ViewGroup;

// Google Mobile Ads and UMP imports
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.MobileAds;
import com.google.android.ump.*;

public class MainActivity extends BridgeActivity {
    private static final String TAG = "SmartCamera";
    private ConsentForm consentForm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Initialize UMP consent form
        requestConsentForm();
        
        // Initialize MobileAds (will be called after consent is obtained)
        MobileAds.initialize(this, initializationStatus -> {
            Log.d(TAG, "MobileAds initialized");
            // We'll show ads after consent is obtained
        });
    }

    private void requestConsentForm() {
        ConsentRequestParameters params = new ConsentRequestParameters
            .Builder()
            .setTagForUnderAgeOfConsent(false) // Set to true if your app is for users under age of consent
            .build();

        ConsentInformation consentInformation = UserMessagingPlatform.getConsentInformation(this);
        
        consentInformation.requestConsentInfoUpdate(
            this,
            params,
            () -> {
                if (consentInformation.isConsentFormAvailable()) {
                    loadConsentForm();
                } else if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.NOT_REQUIRED) {
                    // Consent not required, show ads immediately
                    showBannerAd();
                }
            },
            formError -> {
                // Handle error
                Log.e(TAG, "Error requesting consent info update: " + formError.getMessage());
                // Even if there's an error, we'll try to show ads (with limited ad serving)
                showBannerAd();
            }
        );
    }

    private void loadConsentForm() {
        UserMessagingPlatform.loadConsentForm(
            this,
            consentForm -> {
                this.consentForm = consentForm;
                ConsentInformation consentInformation = UserMessagingPlatform.getConsentInformation(this);
                if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.REQUIRED) {
                    consentForm.show(this, formError -> {
                        // Consent form was dismissed, now we can show ads
                        showBannerAd();
                    });
                } else {
                    // Consent not required, show ads
                    showBannerAd();
                }
            },
            formError -> {
                // Handle form load error
                Log.e(TAG, "Error loading consent form: " + formError.getMessage());
                // Even if there's an error, we'll try to show ads (with limited ad serving)
                showBannerAd();
            }
        );
    }

    private void showBannerAd() {
        runOnUiThread(() -> {
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
                FrameLayout rootView = getWindow().getDecorView().findViewById(android.R.id.content);
                rootView.addView(adView, adParams);

                // Create an ad request
                AdRequest adRequest = new AdRequest.Builder().build();
                
                // Load the ad
                adView.loadAd(adRequest);
                Log.d(TAG, "Banner ad loading...");
                
            } catch (Exception e) {
                Log.e(TAG, "Failed to show banner ad: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}

