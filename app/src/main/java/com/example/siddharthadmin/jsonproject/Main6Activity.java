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

public class Main6Activity extends AppCompatActivity {

    private String TAG = Main6Activity.class.getSimpleName();

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

        String str1= extras.getString("message1");
        String str2= extras.getString("message2");
        String str3= extras.getString("message3");
        String str4= extras.getString("message4");
        String str5= extras.getString("message5");
        String str6= extras.getString("message6");

        url = "https://api.railwayapi.com/v2/check-seat/train/"+str1+"/source/"+str2+"/dest/"+str3+"/date/"+str4+"/pref/"+str5+"/quota/"+str6+"/apikey/ck0bzy0875/";

    }


    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Main6Activity.this);
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

                    HashMap<String, String> seat1 = new HashMap<>();

                    JSONObject quota = jsonObj.getJSONObject("quota");
                    String name0 = quota.optString("name");
                    String code0 = quota.optString("code");
                    seat1.put("name0","Name Of quota :"+ name0);
                    seat1.put("code0","code Of quota :"+ code0);

                    JSONObject from_station = jsonObj.getJSONObject("from_station");
                    String name1 = from_station.optString("name");
                    String code1 = from_station.optString("code");
                    seat1.put("name1","Source Station :"+ name1);
                    seat1.put("code1","Source Station Code :"+ code1);

                    JSONObject to_station = jsonObj.getJSONObject("to_station");
                    String name2 = to_station.optString("name");
                    String code2 = to_station.optString("code");
                    seat1.put("name2","Destination Station :"+ name2);
                    seat1.put("code2","Destination Station Code :"+ code2);

                    JSONObject journey_class = jsonObj.getJSONObject("journey_class");
                    String name3 = journey_class.optString("name");
                    String code3 = journey_class.optString("code");
                    seat1.put("name3","Journey Class :"+ name3);
                    seat1.put("code3","Journey Class Code  :"+ code3);


                    JSONArray seat = jsonObj.getJSONArray("availability");
                    for (int i = 0; i < 1; i++) {
                        JSONObject c = seat.getJSONObject(i);

                        String status = c.optString("status");
                        String date = c.optString("date");

                        seat1.put("date","Date :"+ date);
                        seat1.put("status","Availability Status :"+ status);


                    }

                    JSONObject train = jsonObj.getJSONObject("train");
                    String train_no = train.optString("number");
                    String train_name = train.optString("name");
                    seat1.put("train_no","Train Number :"+ train_no);
                    seat1.put("train_name","Train Name :"+ train_name);


                    // Getting JSON Array node


                    JSONArray classes = train.getJSONArray("classes");


                        // looping through All Contacts
                        JSONObject c = classes.getJSONObject(0);


                        String name4 = c.optString("name");
                        String available4 = c.optString("available");
                        seat1.put("name4", "Name of Class :" + name4);
                        seat1.put("available4", "Availability of Class :" + available4);

                        JSONObject c1 = classes.getJSONObject(1);


                         String name5 = c1.optString("name");
                         String available5 = c1.optString("available");
                         seat1.put("name5", "Name of Class :" + name5);
                         seat1.put("available5", "Availability of Class :" + available5);

                         JSONObject c2 = classes.getJSONObject(2);


                         String name6 = c2.optString("name");
                         String available6 = c2.optString("available");
                         seat1.put("name6", "Name of Class :" + name6);
                         seat1.put("available6", "Availability of Class :" + available6);

                         JSONObject c3 = classes.getJSONObject(3);


                         String name7 = c3.optString("name");
                         String available7 = c3.optString("available");
                         seat1.put("name7", "Name of Class :" + name7);
                         seat1.put("available7", "Availability of Class :" + available7);

                         JSONObject c4 = classes.getJSONObject(4);


                         String name8 = c4.optString("name");
                         String available8 = c4.optString("available");
                         seat1.put("name8", "Name of Class :" + name8);
                         seat1.put("available8", "Availability of Class :" + available8);

                         JSONObject c5 = classes.getJSONObject(5);


                         String name9 = c5.optString("name");
                         String available9 = c5.optString("available");
                         seat1.put("name9", "Name of Class :" + name9);
                         seat1.put("available9", "Availability of Class :" + available9);

                         JSONObject c6 = classes.getJSONObject(6);


                         String name10 = c6.optString("name");
                         String available10 = c6.optString("available");
                         seat1.put("name10", "Name of Class :" + name10);
                         seat1.put("available10", "Availability of Class :" + available10);

                         JSONObject c7 = classes.getJSONObject(7);


                         String name11 = c7.optString("name");
                         String available11 = c7.optString("available");
                         seat1.put("name11", "Name of Class :" + name11);
                         seat1.put("available11", "Availability of Class :" + available11);




                        // adding route to route list
                        contactList.add(seat1);

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
                    Main6Activity.this, contactList,
                    R.layout.list_item6, new String[]{"name0",
                    "code0","name1","code1","name2", "code2", "name3", "code3", "status", "date","train_no", "name4", "available4", "name5", "available5", "name6", "available6", "name7", "available7", "name8", "available8", "name9", "available9", "name10", "available10", "name11", "available11", "train_name"}, new int[]{
                    R.id.name0, R.id.code0,R.id.name1,R.id.code1,R.id.name2,R.id.code2,R.id.name3,R.id.code3,R.id.status,R.id.date,R.id.train_no,R.id.name4,R.id.available4,R.id.name5,R.id.available5,R.id.name6,R.id.available6,R.id.name7,R.id.available7,R.id.name8,R.id.available8,R.id.name9,R.id.available9,R.id.name10,R.id.available10,R.id.name11,R.id.available11,R.id.train_name});

            lv.setAdapter(adapter);
        }

    }
}