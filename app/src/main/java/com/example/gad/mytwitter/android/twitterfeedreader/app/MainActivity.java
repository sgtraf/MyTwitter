package com.example.gad.mytwitter.android.twitterfeedreader.app;

//http://javapapers.com/android/android-twitter-feed-reader/

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gad.mytwitter.android.util.AndroidNetworkUtility;

public class MainActivity extends ListActivity {

    final static String twitterScreenName = "gadz1000";
    final static String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworkUtility androidNetworkUtility = new AndroidNetworkUtility();
        if (androidNetworkUtility.isConnected(this)) {
            new TwitterAsyncTask().execute(twitterScreenName,this);
        } else {
            Log.v(TAG, "Network not Available!");
        }
    }
}