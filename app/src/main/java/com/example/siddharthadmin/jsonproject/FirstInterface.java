package com.example.siddharthadmin.jsonproject;

/**
 * Created by imrohan on 12-07-2018.
 */
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstInterface extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Indian Railway Helpline Number: 139", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        Button route= (Button) findViewById(R.id.route);

        route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent routeI= new Intent(FirstInterface.this,RouteMiddle.class);
                startActivity(routeI);
            }
        });


        Button pnr= (Button) findViewById(R.id.pnr);

        pnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pnrI= new Intent(FirstInterface.this,PNRMiddle.class);
                startActivity(pnrI);
            }
        });



        Button tbs= (Button) findViewById(R.id.tbs);

        tbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tbsI= new Intent(FirstInterface.this,TBSMiddle.class);
                startActivity(tbsI);
            }
        });




        Button status= (Button) findViewById(R.id.status);

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent status1= new Intent(FirstInterface.this,StatusMiddle.class);
                startActivity(status1);
            }
        });


        Button fare= (Button) findViewById(R.id.fare);

        fare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fare1= new Intent(FirstInterface.this,FareMiddle.class);
                startActivity(fare1);
            }
        });

        Button seat= (Button) findViewById(R.id.seat);

        seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seat1= new Intent(FirstInterface.this,SeatMiddle.class);
                startActivity(seat1);
            }
        });




    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent set1= new Intent(FirstInterface.this,Secondact.class);
            startActivity(set1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


        @SuppressWarnings("StatementWithEmptyBody")
        @Override

    public boolean onNavigationItemSelected(MenuItem menuitem) {
            // Handle navigation view item clicks here.
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);

            int id = menuitem.getItemId();
            //FragmentManager fragmentManager = getFragmentManager();

            if (id == R.id.nav_first_layout) {
                startActivity(new Intent(FirstInterface.this, FirstFragment.class));
                drawer.closeDrawers();
                return true;
            } else if (id == R.id.nav_send) {
                Toast.makeText(getApplicationContext(),"This is under maintainence",Toast.LENGTH_LONG).show();
            }



            return true;
        }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                })
                .show();

    }



}
