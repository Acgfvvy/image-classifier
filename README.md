📄 README.md

# 📸 Smart Camera – AI Image Identifier

Smart Camera is a smart, lightweight image classifier web app built using Mobile Net's general recognition model. It is available as a Progressive Web App (PWA) and also as an Android app on the Google Play Store.

---

## 🚀 Features

- 🧠 **AI-Powered Image Recognition** – Identify objects using Mobile Net's model
- 💻 **PWA Support** – Works offline with installable web experience - "https://smart-camera-3-15-2013.web.app"
- 📱 **Android App** – Built with Capacitor and native integrations
- 📊 **Firebase Analytics** – Tracks usage metrics
- 🧩 **Firebase Crashlytics** – Automatically reports crashes
- 💸 **AdMob Banner Ads** – Monetized with Google ads (with user consent)
- 🛡️ **Privacy-First** – GDPR-compliant with consent screen

---

## 🛠️ Tech Stack

- **Frontend**: HTML, JavaScript (Vanilla)
- **AI Model**: Mobile Net's General Image Recognition
- **PWA**: Service Worker + manifest.json
- **Firebase**: Hosting, Analytics, Crashlytics
- **Android**: Capacitor + Java bridge (AdMob, Firebase SDK)

---

## 🔧 Development

### 🔥 Firebase Hosting

To deploy the web app:

firebase deploy

Make sure firebase.json has "public": "www" and points to the correct index.

🤖 Android Build

1. Wrap with Capacitor:

npx cap sync
npx cap open android


2. Build the APK/AAB in Android Studio.


3. Ensure the following Firebase plugins are applied in build.gradle:

apply plugin: 'com.google.gms.google-services'
apply plugin: 'com.google.firebase.crashlytics'


4. Confirm AdMob is initialized after MobileAds.initialize(...).




---

📱 Play Store

The app is available on the Google Play Store (link will be added after launch).


---

📃 Legal

📜 Privacy Policy – required for Firebase and AdMob

📜 Terms & Conditions


User consent is collected before showing ads in compliance with GDPR and AdMob requirements.


---

📦 License

This project is licensed under the MIT License.

---

Let me know if you'd like:

- A **custom app icon** section
- To include **setup steps** for other developers
- Or a localized language version
