package com.example.siddharthadmin.jsonproject;

import android.app.ProgressDialog;
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

import static android.widget.Toast.makeText;

public class Main3Activity extends AppCompatActivity {

    private String TAG = Main3Activity.class.getSimpleName();

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
        //assert extras != null;
        String str= extras.getString("message");
        String str1= extras.getString("message1");
        String str2= extras.getString("message2");



         url = "https://api.railwayapi.com/v2/between/source/"+str+"/dest/"+str1+"/date/"+str2+"/apikey/ck0bzy0875/";
    }

    /*
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Main3Activity.this);
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
                    JSONArray trains = jsonObj.getJSONArray("trains");

                    // looping through All Contacts
                    for (int i = 0; i < trains.length(); i++) {
                        JSONObject c = trains.getJSONObject(i);

                        String number = c.optString("number");
                        String name = c.optString("name");
                        String travel_time = c.optString("travel_time");
                        String src_departure_time = c.optString("src_departure_time");
                        String dest_arrival_time = c.optString("dest_arrival_time");

                        // Station node is JSON Object
                        JSONObject from_station = c.getJSONObject("from_station");
                        String code0 = from_station.optString("code");
                        String name0 = from_station.optString("name");


                        JSONObject to_station = c.getJSONObject("to_station");
                        String code1 = to_station.optString("code");
                        String name1 = to_station.optString("name");

                        // tmp hash map for single route
                        HashMap<String, String> trains1 = new HashMap<>();

                        // adding each child node to HashMap key => value
                        trains1.put("number", "Train Number : " + number);
                        trains1.put("name", "Name of the Train : " + name);
                        trains1.put("travel_time", "Estimated time taken   : " + travel_time);
                        trains1.put("src_departure_time", "Departure Time: " + src_departure_time);
                        trains1.put("dest_arrival_time", "Arrival at Destination : " + dest_arrival_time);
                        trains1.put("code0", "Source Station Code : " + code0);
                        trains1.put("name0", "Source Station Name : " + name0);
                        trains1.put("code1", "Destination Station Code : " + code1);
                        trains1.put("name1", "Destination Station Name : " + name1);


                        // adding route to route list
                        contactList.add(trains1);
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
                    Main3Activity.this, contactList,
                    R.layout.list_item3, new String[]{"number", "name",
                    "travel_time", "src_departure_time","dest_arrival_time", "code0","name0","code1","name1"}, new int[]{R.id.number,
                    R.id.name, R.id.travel_time, R.id.src_departure_time, R.id.dest_arrival_time, R.id.code0,R.id.name0,R.id.code1,R.id.name1});

            lv.setAdapter(adapter);
        }

    }
}
