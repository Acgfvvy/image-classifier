# Keep Capacitor core
-keep class com.getcapacitor.** { *; }

# Keep Firebase and Google Play Services (if used)
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# Keep AdMob classes
-keep class com.google.android.ump.** { *; }
-keep class com.google.android.gms.ads.** { *; }

# Keep Cordova/Plugin interfaces (if you use any Cordova)
-keep class org.apache.cordova.** { *; }

# Optional: Keep models, API classes
-keep class com.gmail.omsjsr.smartcamera.** { *; }

# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
