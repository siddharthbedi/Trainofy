package com.example.siddharthadmin.jsonproject;

/**
 * Created by imrohan on 13-07-2018.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StatusMiddle extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.middle_status);


        // final EditText textBox;
        //Button passButton;

        final EditText textBox=(EditText)findViewById(R.id.train_status);
        final EditText textBox1=(EditText)findViewById(R.id.date);
        Button passButton=(Button)findViewById(R.id.button_status);


        passButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String str = textBox.getText().toString()  ;
                String str1 = textBox1.getText().toString()  ;
                //String str="12446";
                Intent intent = new Intent(StatusMiddle.this,Main4Activity.class);
                intent.putExtra("message",str);
                intent.putExtra("message1",str1);
                startActivity(intent);
            }

        });
    }
}
