package com.example.siddharthadmin.jsonproject;



import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TBSMiddle extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.middle_tbs);

        final EditText textBox1,textBox2,textBox3;
        Button passButton;

        textBox1= findViewById(R.id.source);
        textBox2= findViewById(R.id.destination);
        textBox3= findViewById(R.id.date);

        passButton= findViewById(R.id.button_tbs);
        Button getcode=findViewById(R.id.get_code);

        passButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String str;
                String str1;
                String str2;
                str = textBox1.getText().toString();
                str1 = textBox2.getText().toString();
                str2 = textBox3.getText().toString();
                Intent intent = new Intent(TBSMiddle.this,Main3Activity.class);
                intent.putExtra("message",str);
                intent.putExtra("message1",str1);
                intent.putExtra("message2",str2);


                startActivity(intent);
            }

        });

        getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("https://irfca.org/apps/station_codes"));
                startActivity((intent1));
            }
        });
    }
}
