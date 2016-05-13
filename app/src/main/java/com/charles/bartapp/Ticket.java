package com.charles.bartapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Ticket extends AppCompatActivity {

    static final HashMap<String, String> stationmap = new HashMap<String, String>() {
        {
            put("12th St. Oakland City Center", "12th");put("16th St. Mission (SF)", "16th");put("19th St. Oakland", "19th");
            put("24th St. Mission (SF)", "24th");put("Ashby (Berkeley)", "ashb");put("Balboa Park (SF)", "balb");put("Bay Fair (San Leandro)", "bayf");
            put("Castro Valley", "cast");put("Civic Center (SF)", "civc");put("Coliseum/Oakland Airport", "cols");put("Colma", "colm");
            put("Concord", "conc");put("Daly City", "daly");put("Downtown Berkeley", "dbrk");put("Dublin/Pleasanton", "dubl");
            put("El Cerrito del Norte", "deln");put("El Cerrito Plaza", "plza");put("Embarcadero (SF)", "embr");put("Fremont", "frmt");
            put("Fruitvale (Oakland)", "ftvl");put("Glen Park (SF)", "glen");put("Hayward", "hayw");put("Lafayette", "lafy");
            put("Lake Merritt (Oakland)", "lake");put("MacArthur (Oakland)", "mcar");put("Millbrae", "mlbr");put("Montgomery St. (SF)", "mont");
            put("North Berkeley", "nbrk");put("North Concord/Martinez", "ncon");put("Orinda", "orin");put("Pittsburg/Bay Point", "pitt");
            put("Pleasant Hill", "phil");put("Powell St. (SF)", "powl");put("Richmond", "rich");put("Rockridge (Oakland)", "rock");
            put("San Bruno", "sbrn");put("San Francisco International Airport SFO", "sfia");put("San Leandro", "sanl");put("South Hayward", "shay");
            put("South San Francisco", "ssan");put("Union City", "ucty");put("Walnut Creek", "wcrk");put("West Dublin/Pleasanton","wdub");
            put("West Oakland", "woak");
        }
    };

    private String url1 = "http://api.bart.gov/api/sched.aspx?cmd=fare&orig=";
    private String url2 = "&dest=";
    private String url3 = "&key=MW9S-E7SL-26DU-VV8V";
    private HandleXML obj;
    Button Fare;
    Spinner ospinner;
    Spinner dspinner;
    ArrayAdapter<CharSequence> oadapter;
    ArrayAdapter<CharSequence> dadapter;
    TextView tFare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        Fare = (Button)findViewById(R.id.bFare);
        ospinner = (Spinner)findViewById(R.id.oSpinner);
        dspinner = (Spinner)findViewById(R.id.dSpinner);

        oadapter = ArrayAdapter.createFromResource(this,R.array.stations,android.R.layout.simple_spinner_item);
        oadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ospinner.setAdapter(oadapter);
        ospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getBaseContext(), adapterView.getItemIdAtPosition(1)+"Origin station selected",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        dadapter = ArrayAdapter.createFromResource(this,R.array.stations,android.R.layout.simple_spinner_item);
        dadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dspinner.setAdapter(dadapter);
        dspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getBaseContext(), adapterView.getItemIdAtPosition(1)+"Destination station selected",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tFare = (TextView)findViewById(R.id.tFare);

        Fare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ourl = stationmap.get(ospinner.getSelectedItem().toString());
                String durl = stationmap.get(dspinner.getSelectedItem().toString());
                String finalUrl = url1 + ourl + url2 + durl +url3;

                obj = new HandleXML(finalUrl);
                obj.fetchXML();

                while(obj.parsingComplete);
                tFare.setText(obj.getFare());
            }
        });
    }


}
