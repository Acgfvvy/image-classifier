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

// UMP SDK imports
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.UserMessagingPlatform;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.FormError;
import com.google.android.ump.ConsentDebugSettings.DebugGeography;
import androidx.annotation.Nullable;
import android.util.Log;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // --- UMP Debug Consent Logic for EEA Simulation ---
        ConsentDebugSettings debugSettings = new ConsentDebugSettings.Builder(this)
            .setDebugGeography(DebugGeography.DEBUG_GEOGRAPHY_EEA)
            .addTestDeviceHashedId("94B9687EFC60F3A3BE7A091D73CB6C79")
            .build();

        ConsentRequestParameters params = new ConsentRequestParameters.Builder()
            .setConsentDebugSettings(debugSettings)
            .setTagForUnderAgeOfConsent(false)
            .build();

        ConsentInformation consentInformation = UserMessagingPlatform.getConsentInformation(this);
        consentInformation.requestConsentInfoUpdate(
            MainActivity.this,
            params,
            new ConsentInformation.OnConsentInfoUpdateSuccessListener() {
                @Override
                public void onConsentInfoUpdateSuccess() {
                    Log.d("UMP", "Consent info update success.");
                    if (consentInformation.isConsentFormAvailable()) {
                        UserMessagingPlatform.loadAndShowConsentFormIfRequired(
                            MainActivity.this,
                            new ConsentForm.OnConsentFormDismissedListener() {
                                @Override
                                public void onConsentFormDismissed(FormError formError) {
                                    if (formError != null) {
                                        Log.w("UMP", "Consent form error: " + formError.getMessage());
                                    } else {
                                        Log.d("UMP", "Consent form dismissed successfully.");
                                    }
                                    startAdMob(); // Call ads only after consent
                                }
                            }
                        );
                    } else {
                        Log.d("UMP", "Consent form not available.");
                        startAdMob(); // Consent not required, start ads
                    }
                }
            },
            new ConsentInformation.OnConsentInfoUpdateFailureListener() {
                @Override
                public void onConsentInfoUpdateFailure(FormError formError) {
                    Log.w("UMP", "Consent info update failed: " + formError.getMessage());
                    startAdMob(); // Fallback
                }
            }
        );
    }

    private void startAdMob() {
        // Initialize MobileAds and only show banner after consent handled
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

