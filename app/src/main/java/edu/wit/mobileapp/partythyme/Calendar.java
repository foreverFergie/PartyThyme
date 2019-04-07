package edu.wit.mobileapp.partythyme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        CalendarView cal = (CalendarView)findViewById(R.id.calendarView);

        cal.getDate();

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                Intent settingsPage = new Intent();
                settingsPage.setClass(Calendar.this, settings.class);
                startActivity(settingsPage);
                return true;

            case R.id.action_help:
                Intent helpPage = new Intent();
                helpPage.setClass(Calendar.this, help.class);
                startActivity(helpPage);
                return true;
            case R.id.action_home:
                Intent homePage = new Intent();
                homePage.setClass(Calendar.this, MainActivity.class);
                startActivity(homePage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}