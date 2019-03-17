package edu.wit.mobileapp.partythyme;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Bitmap defaultImage;
        defaultImage = BitmapFactory.decodeResource(getResources(), R.drawable.default_plant);

        List<plant_temp> plants = new ArrayList<>();
        List<String> dropDown=new ArrayList<>();
        dropDown.add("");
        dropDown.add("Edit");
        dropDown.add("Info");
        dropDown.add("Remove Plant");

        for (int i = 1; i < 15; i++) {
            plant_temp plant = new plant_temp();
            plant.image = defaultImage;
            plant.name = "plant" + i;
            plants.add(plant);




        }

        plantItemAdapter adapter;
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.plant_list);
        adapter = new plantItemAdapter(plants,dropDown,this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        //Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);


    }



}


