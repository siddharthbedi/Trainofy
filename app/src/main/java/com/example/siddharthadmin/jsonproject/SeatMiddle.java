package com.example.siddharthadmin.jsonproject;

/**
 * Created by imrohan on 14-07-2018.
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SeatMiddle extends AppCompatActivity{

    String[] listItems,listItems1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.middle_seat);

        listItems = getResources().getStringArray(R.array.class_item);
        listItems1 = getResources().getStringArray(R.array.quota_item);


        // final EditText textBox;
        //Button passButton;


        final EditText textBox1=(EditText)findViewById(R.id.train_seat);
        final EditText textBox2=(EditText)findViewById(R.id.src_code);
        final EditText textBox3=(EditText)findViewById(R.id.dest_code);
        final EditText textBox4=(EditText)findViewById(R.id.date);
        final EditText textBox5=(EditText)findViewById(R.id.class_code);
        final EditText textBox6=(EditText)findViewById(R.id.quota_code);
        Button passButton=(Button)findViewById(R.id.button_seat);
        Button getcode3=findViewById(R.id.codes3);


        textBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SeatMiddle.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textBox5.setText(listItems[i]);
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        textBox6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SeatMiddle.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setSingleChoiceItems(listItems1, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textBox6.setText(listItems1[i]);
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        passButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String str1 = textBox1.getText().toString()  ;
                String str2 = textBox2.getText().toString()  ;
                String str3 = textBox3.getText().toString()  ;
                String str4 = textBox4.getText().toString()  ;
                String str5 = textBox5.getText().toString()  ;
                String str6 = textBox6.getText().toString()  ;
                //String str="12446";
                Intent intent = new Intent(SeatMiddle.this,Main6Activity.class);

                intent.putExtra("message1",str1);
                intent.putExtra("message2",str2);
                intent.putExtra("message3",str3);
                intent.putExtra("message4",str4);
                intent.putExtra("message5",str5);
                intent.putExtra("message6",str6);
                startActivity(intent);
            }

        });

        getcode3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("https://irfca.org/apps/station_codes"));
                startActivity((intent1));
            }
        });
    }
}
