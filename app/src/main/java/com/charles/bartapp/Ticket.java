package com.charles.bartapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Ticket extends AppCompatActivity {

    private String url1 = "http://api.bart.gov/api/sched.aspx?cmd=fare&orig=";
    private String url2 = "&dest=";
    private String url3 = "&key=MW9S-E7SL-26DU-VV8V";
    private HandleXML obj;
    Button Fare;
    EditText Origin;
    EditText Dest;
    TextView tFare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        Fare=(Button)findViewById(R.id.bFare);

        Origin=(EditText)findViewById(R.id.eOrigin);
        Dest=(EditText)findViewById(R.id.eDest);
        tFare = (TextView)findViewById(R.id.tFare);

        Fare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ourl = Origin.getText().toString();
                String durl = Dest.getText().toString();
                String finalUrl = url1 + ourl + url2 + durl +url3;
                //ed2.setText(finalUrl);



                obj = new HandleXML(finalUrl);
                obj.fetchXML();


                while(obj.parsingComplete);
                tFare.setText(obj.getFare());

            }
        });
    }


}
