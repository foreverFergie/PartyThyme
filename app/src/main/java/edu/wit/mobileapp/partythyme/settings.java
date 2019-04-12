package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //final String filename = this.getFilesDir().getPath() + this.getApplicationContext().getPackageName() + "/settings.txt";
    private List<Integer> cal= new ArrayList<>();
    public int calendarLifespan;
    private int sel=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Spinner calendar = (Spinner) findViewById(R.id.calendarLife);
        calendar.setOnItemSelectedListener(this);
        popCalArray();

        ArrayAdapter<Integer> calAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,cal);
        calAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        calendar.setAdapter(calAdapter);

        Switch vibrate=(Switch) findViewById(R.id.vibrate);
        Switch sound=(Switch)findViewById(R.id.sound);

        vibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Vibrator v=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(400);
            }
        });

        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int days=Integer.parseInt(parent.getItemAtPosition(position).toString());
        sel=position;
        calendarLifespan=days;


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        parent.setSelection(sel);

    }

    private void popCalArray(){
        for(int i=21;i<42;i++){
            cal.add(i);
        }
        for(int j=50;j<90;j+=10){
            cal.add(j);
        }
        for(int k=90;k<365;k+=30){
            cal.add(k);
        }

    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_help:
                Intent settingsPage =  new Intent();
                settingsPage.setClass(settings.this,help.class);
                startActivity(settingsPage);
                return true;
            case R.id.action_home:
                Intent homePage = new Intent();
                homePage.setClass(settings.this,MainActivity.class);
                startActivity(homePage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
