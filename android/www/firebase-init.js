// Import the functions you need from the SDKs you need
import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.12.2/firebase-analytics.js";

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
const analytics = getAnalytics(app);