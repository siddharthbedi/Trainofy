package com.example.siddharthadmin.jsonproject;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PNRMiddle extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.middle_pnr);

        final EditText textBox;
        Button passButton;

        textBox= findViewById(R.id.train_pnr);
        passButton= findViewById(R.id.button_pnr);

        passButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String str;
                str = textBox.getText().toString();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("message",str);


                startActivity(intent);
            }

        });
    }
}
