// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-app.js";
import { getAnalytics, isSupported, setConsent, setAnalyticsCollectionEnabled } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-analytics.js";

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyD1RsWia-5ZNpAQuVntn7jklWpwYd9ewqc",
  authDomain: "smart-camera-3-15-2013.firebaseapp.com",
  projectId: "smart-camera-3-15-2013",
  storageBucket: "smart-camera-3-15-2013.appspot.com",
  messagingSenderId: "814154291060",
  appId: "1:814154291060:web:20abb882430f8dd84ffe21",
  measurementId: "G-Y7JG807HNS"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Configure analytics based on consent
const consent = localStorage.getItem('cookieConsent');
const analyticsConsent = consent === 'accepted';

// Set analytics consent
isSupported().then((isSupported) => {
  if (isSupported) {
    const analytics = getAnalytics(app);
    
    // Set consent settings
    setConsent({
      analytics_storage: analyticsConsent ? 'granted' : 'denied',
      ad_storage: 'denied',  // We're not using ads, but explicitly deny to be safe
      ad_user_data: 'denied',
      ad_personalization: 'denied',
      personalization_storage: 'denied',
      functionality_storage: 'granted',  // Required for basic functionality
      security_storage: 'granted'        // Required for security features
    });

    // Enable/disable analytics collection
    setAnalyticsCollectionEnabled(analytics, analyticsConsent);
    
    if (analyticsConsent) {
      console.log('Analytics collection is enabled');
    } else {
      console.log('Analytics collection is disabled');
    }
  }
}).catch((error) => {
  console.error('Analytics initialization error:', error);
});