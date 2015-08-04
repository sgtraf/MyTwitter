package com.example.gad.mytwitter.android.twitterfeedreader.app;

import android.app.ListActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.gad.mytwitter.social.twitter.TwitterAPI;
import com.example.gad.mytwitter.social.twitter.TwitterTweet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TwitterAsyncTask extends AsyncTask<Object, Void, ArrayList<TwitterTweet>> {
    ListActivity callerActivity;

    final static String TWITTER_API_KEY = "RBeRZavPuvv5UOhouf2C0WTbo";
    final static String TWITTER_API_SECRET = "hzsovNPYeCNw6VPsd61a5Mqr2lBkVNtARb7VuGADQDJPXs20nz";

    @Override
    protected ArrayList<TwitterTweet> doInBackground(Object... params) {
        ArrayList<TwitterTweet> twitterTweets = null;
        callerActivity = (ListActivity) params[1];
        if (params.length > 0) {
            TwitterAPI twitterAPI = new TwitterAPI(TWITTER_API_KEY,TWITTER_API_SECRET);
            twitterTweets = twitterAPI.getTwitterTweets(params[0].toString());
        }
        return twitterTweets;
    }

    @Override
    protected void onPostExecute(ArrayList<TwitterTweet> twitterTweets) {
        ArrayAdapter<TwitterTweet> adapter =
                new ArrayAdapter<TwitterTweet>(callerActivity, R.layout.twitter_tweets_list,
                        R.id.listTextView, twitterTweets);



        callerActivity.setListAdapter(adapter);
        ListView lv = callerActivity.getListView();
        lv.setDividerHeight(0);
        //lv.setDivider(this.getResources().getDrawable(android.R.color.transparent));
        lv.setBackgroundColor(callerActivity.getResources().getColor(R.color.Twitter_blue));
        Log.e("log_tag", " data1 " +twitterTweets.get(0).getCreatedAt());


    //    String str = "Fri Jul 31 06:28:53 +0000 2015";
        String str =  twitterTweets.get(0).getCreatedAt();
        try {
            Date date = new SimpleDateFormat("E MMM d k:m:s Z yy", Locale.ENGLISH).parse(str);
            Log.e("log_tag", " data2 " + date.getDate() + " " + date.getMonth());



            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("E MMMM d k:m:s Z yy", Locale.ENGLISH);
            cal.setTime(sdf.parse(str));// all done
           Log.e("log_tag", " data3 " + " " + cal.getDisplayName(cal.MONTH, Calendar.LONG, Locale.ENGLISH));
            Log.d("log_tag", " data4 " + " " + (String) new SimpleDateFormat("EEEE, d MMMM k:m:s yyyy г.", Locale.getDefault()).format(new Date(cal.getTimeInMillis())));


        } catch (ParseException e) {
            Log.e("log_tag", " err"+ e);
        }




    }
}
