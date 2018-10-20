package com.example.siddharthadmin.jsonproject;

/**
 * Created by imrohan on 13-07-2018.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Main4Activity extends AppCompatActivity {

    private String TAG = Main4Activity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;
    private String url;





    // URL to get contacts JSON


    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        contactList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.list);

        new GetContacts().execute();



        Bundle extras= getIntent().getExtras();
        String str= extras.getString("message");
        String str1= extras.getString("message1");

        url = "https://api.railwayapi.com/v2/live/train/"+str+"/date/"+str1+"/apikey/ck0bzy0875/";

    }


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Main4Activity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            com.example.siddharthadmin.jsonproject.HttpHandler sh = new com.example.siddharthadmin.jsonproject.HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray status = jsonObj.getJSONArray("route");

                    // looping through All Contacts
                    for (int i = 0; i < status.length(); i++) {
                        JSONObject c = status.getJSONObject(i);

                        //String no = c.optString("no");
                        JSONObject station = c.getJSONObject("station");
                        String name =station.optString("name");

                        String has_arrived = c.optString("has_arrived");
                        String has_departed = c.optString("has_departed");
                        String actarr = c.optString("actarr");
                        String actdep = c.optString("actdep");
                        String actarr_date = c.optString("actarr_date");
                        String latemin = c.optString("latemin");
                        //String day = c.getString("day");

                        // Station node is JSON Object

                        //String code = station.getString("code");
                        //String office = phone.getString("office");

                        // tmp hash map for single route
                        HashMap<String, String> status1 = new HashMap<>();

                        // adding each child node to HashMap key => value
                        //status1.put("no","Number :"+ no);
                        status1.put("name","Name Of Station :"+ name);
                        status1.put("has_arrived","Has Arrived :"+ has_arrived);
                        status1.put("has_departed","Has Departed :"+ has_departed);
                        status1.put("actarr","Actual Arrival :"+ actarr);
                        status1.put("actdep","Actual Departure :"+ actdep);
                        status1.put("actarr_date","Actual Arrival Date :"+ actarr_date);
                        status1.put("latemin","Late Minutes :"+ latemin);
                        //contact.put("day", gender);

                        // adding route to route list
                        contactList.add(status1);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    Main4Activity.this, contactList,
                    R.layout.list_item4, new String[]{"name",
                    "has_arrived","has_departed","actarr","actdep", "actarr_date", "latemin"}, new int[]{
                    R.id.name, R.id.has_arrived,R.id.has_departed,R.id.actarr,R.id.actdep,R.id.actarr_date,R.id.latemin});

            lv.setAdapter(adapter);
        }

    }
}