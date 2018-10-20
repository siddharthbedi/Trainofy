package com.example.siddharthadmin.jsonproject;

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

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

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

         url = "https://api.railwayapi.com/v2/route/train/"+str+"/apikey/ck0bzy0875/";

    }


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray route = jsonObj.getJSONArray("route");

                    // looping through All Contacts
                    for (int i = 0; i < route.length(); i++) {
                        JSONObject c = route.getJSONObject(i);

                        String no = c.getString("no");
                        String scharr = c.getString("scharr");
                        String schdep = c.getString("schdep");
                        String distance = c.getString("distance");
                        //String day = c.getString("day");

                        // Station node is JSON Object
                        JSONObject station = c.getJSONObject("station");
                        String name = station.getString("name");
                        String code = station.getString("code");
                        //String office = phone.getString("office");

                        // tmp hash map for single route
                        HashMap<String, String> route1 = new HashMap<>();

                        // adding each child node to HashMap key => value
                        route1.put("no","Number :"+ no);
                        route1.put("scharr","Arrival :"+ scharr);
                        route1.put("schdep","Departure :"+ schdep);
                        route1.put("distance","Route Distance :"+ distance);
                        route1.put("name","Name of station :"+ name);
                        route1.put("code","Station Code :"+ code);
                        //contact.put("day", gender);

                        // adding route to route list
                        contactList.add(route1);
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
                    MainActivity.this, contactList,
                    R.layout.list_item, new String[]{"no", "scharr",
                    "schdep","distance","name","code"}, new int[]{R.id.no,
                    R.id.scharr, R.id.schdep,R.id.distance,R.id.name,R.id.code});

            lv.setAdapter(adapter);
        }

    }
}