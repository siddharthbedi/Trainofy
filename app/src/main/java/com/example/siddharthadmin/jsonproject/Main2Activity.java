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

public class Main2Activity extends AppCompatActivity {

    private String TAG = Main2Activity.class.getSimpleName();

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

        lv =  findViewById(R.id.list);

        new GetContacts().execute();


        Bundle extras= getIntent().getExtras();
        //assert extras != null;
        String str= extras.getString("message");
        url = "https://api.railwayapi.com/v2/pnr-status/pnr/"+str+"/apikey/ck0bzy0875/";

    }

    /*
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Main2Activity.this);
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
                    JSONArray passengers = jsonObj.getJSONArray("passengers");

                    // looping through All Contacts
                    for (int i = 0; i < passengers.length(); i++) {
                        JSONObject c = passengers.getJSONObject(i);

                        String no1 = c.getString("no");
                        String current_status = c.getString("current_status");
                        String booking_status = c.getString("booking_status");


                        // Station node is JSON Object
                        JSONObject to_station = c.getJSONObject("to_station");
                        String name0 = to_station.getString("name");
                        String code0 = to_station.getString("code");
                        //String office = phone.getString("office");

                        JSONObject boarding_station = c.getJSONObject("boarding_station");
                        String name1 = boarding_station.getString("name");
                        String code1 = boarding_station.getString("code");

                        JSONObject reservation_upto = c.getJSONObject("reservation_upto");
                        String name2 = reservation_upto.getString("name");
                        String code2 = reservation_upto.getString("code");

                        JSONObject train = c.getJSONObject("train");
                        String name3 = train.getString("name");
                        String number3 = train.getString("number");

                        JSONObject journey_class = c.getJSONObject("journey_class");
                        String name4 = journey_class.getString("name");
                        String code4 = journey_class.getString("code");


                        // tmp hash map for single route
                        HashMap<String, String> pnr1 = new HashMap<>();

                        // adding each child node to HashMap key => value
                        pnr1.put("no1", "Number : " + no1);
                        pnr1.put("current_status", "Current Status : " + current_status);
                        pnr1.put("booking_status", "Booking Status : " + booking_status);

                        pnr1.put("name0", "Boarding Station  : " + name0);
                        pnr1.put("code0", "Code : " + code0);

                        pnr1.put("name1", "Reservation Upto : " + name1);
                        pnr1.put("code1", "Code : " + name1);

                        pnr1.put("name2", "Train : " + name2);
                        pnr1.put("code2", "Number : " + name2);

                        pnr1.put("name3", "Name of the Class : " + name3);
                        pnr1.put("code3", "Code of the Class : " + number3);



                        // adding route to route list
                        contactList.add(pnr1);
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
            /*
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    Main2Activity.this, contactList,
                    R.layout.list_item2, new String[]{"no", "current_status",
                    "booking_status", " name0", "code0", " name1", "code1"," name2", "code2"," name3", "number3","name4","code4"}, new int[]{R.id.no,
                    R.id.current_status ,R.id.booking_status , R.id.name0, R.id.code0, R.id.name1, R.id.code1,R.id.name2, R.id.code2,R.id.name3, R.id.number3,R.id.name4, R.id.code4});

            lv.setAdapter(adapter);
        }

    }
}
