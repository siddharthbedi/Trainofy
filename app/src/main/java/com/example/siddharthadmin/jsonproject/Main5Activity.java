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

public class Main5Activity extends AppCompatActivity {

    private String TAG = Main5Activity.class.getSimpleName();

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
        String str3= extras.getString("message3");
        String str7= extras.getString("message7");
        String str1= extras.getString("message1");
        String str4= extras.getString("message4");
        String str5= extras.getString("message5");
        String str6= extras.getString("message6");
        String str2= extras.getString("message2");





        url = "https://api.railwayapi.com/v2/fare/train/"+str1+"/source/"+str2+"/dest/"+str3+"/age/"+str4+"/pref/"+str5+"/quota/"+str6+"/date/"+str7+"/apikey/ck0bzy0875/";
    }

    /*
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Main5Activity.this);
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
                   //JSONArray fare = jsonObj.getJSONArray("fare");
                    String fare2 = jsonObj.getString("fare");

                    JSONObject to_station = jsonObj.getJSONObject("to_station");
                    String code1 = to_station.optString("code");
                    String name1 = to_station.optString("name");

                    JSONObject from_station = jsonObj.getJSONObject("from_station");
                    String code0 = from_station.optString("code");
                    String name0 = from_station.optString("name");

                    JSONObject train = jsonObj.getJSONObject("train");
                    String name2 = train.optString("name");
                    String number2 = train.optString("number");

                    JSONObject quota = jsonObj.getJSONObject("quota");
                    String code3 = quota.optString("code");
                    String name3 = quota.optString("name");

                    JSONObject journey_class = jsonObj.getJSONObject("journey_class");
                    String code4 = journey_class.optString("code");
                    String name4 = journey_class.optString("name");


                    HashMap<String, String> fare1 = new HashMap<>();

                    // adding each child node to HashMap key => value
                    fare1.put("code0", "Source Station Code : " + code0);
                    fare1.put("name0", "Source Station Name : " + name0);

                    fare1.put("code1", "Destination Station Code : " + code1);
                    fare1.put("name1", "Destination Station Name : " + name1);

                    fare1.put("name2", "Name of the Train : " + name2);
                    fare1.put("number2", "Train Number : " + number2);


                    fare1.put("code3", "Quota code   : " + code3);
                    fare1.put("name3", "Quota: " + name3);

                    fare1.put("code4", "Class code   : " + code4);
                    fare1.put("name4", "Class: " + name4);


                    fare1.put("fare", "Fare : " + fare2);

                    contactList.add(fare1);


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
            // Dismiss the progress  dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    Main5Activity.this, contactList,
                    R.layout.list_item5, new String[]{ "code0","name0","code1","name1","name2","number2","code3","name3","code4","name4","fare"}, new int[]{R.id.code0,
                    R.id.name0, R.id.code1, R.id.name1, R.id.name2, R.id.number2,R.id.code3,R.id.name3,R.id.code4,R.id.name4,R.id.fare2});

            lv.setAdapter(adapter);
        }

    }
}
