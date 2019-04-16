package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class result_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        Toolbar toolbar = findViewById(R.id.resultToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        String filename = "/data/data/" + this.getApplicationContext().getPackageName() + "/MyPlants.txt";
        List<ListItemWithNick> list = new ArrayList<ListItemWithNick>();
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader bufferedReader;
        Context context = getApplicationContext();
        final List<ListItemWithNick> plant_list = new ArrayList<ListItemWithNick>();

        try {
            fis = new FileInputStream(new File(filename));
            isr = new InputStreamReader(fis);
            bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();

            String line;
            while((line = bufferedReader.readLine()) != null){
                ListItemWithNick plant = new ListItemWithNick();
                //Get nickname from line
                String pName = "";
                int i = 0;
                sb.append(line);
                while(sb.toString().charAt(i) != ','){
                    pName = pName + sb.toString().charAt(i);
                    i++;
                }
                i++; //Move i past the comma
                String pNick = "";
                //Get plant name from line
                while(i < sb.toString().length()){
                    pNick = pNick + sb.toString().charAt(i);
                    i++;
                }
                Log.v("plant Name", pName);


                //Messed this up and this is a simple quick fix
                plant.nick = pName;
                plant.name = pNick;


                int plantImage = getPlantImage(this, pName);
                if(plantImage == 0){
                    plant.image = BitmapFactory.decodeResource(getResources(), R.drawable.default_plant);
                }
                else
                {
                    plant.image = BitmapFactory.decodeResource(getResources(), plantImage);
                }


                ListItemWithNick li = new ListItemWithNick();
                plant_list.add(plant);
                li.name = plant.name;
                li.nick = plant.nick;
                li.image = plant.image;

                list.add(li);
                sb = new StringBuilder();
                Log.v("plant", plant.name + " added to plants list");
            }
            fis.close();
            isr.close();
            bufferedReader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }



        final ListItemWithNickAdapter adapter;
        adapter = new ListItemWithNickAdapter(result_page.this, 0, list);

        ListView plantList = (ListView) findViewById(R.id.my_plants_list);
        plantList.setAdapter(adapter);

        //Set on click listener to go to plant_info page
        plantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(result_page.this, plant_info.class);
                Bundle bundle = new Bundle();
                try {
                    bundle.putString("name", adapter.getItem(position).name);
                    bundle.putString("nick", adapter.getItem(position).nick);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }catch(java.lang.NullPointerException e){
                    //Just do nothing
                    Log.v("myApp", "Not in list error");
                }
            }
        });
    }

    public static int getPlantImage(Context context, String name){
        String nameNoSpaces = "";
        for(int i = 0; i < name.length(); i++)
        {
            if (name.charAt(i) == ' '){
                nameNoSpaces = nameNoSpaces + "_";
            }
            else
            {
                nameNoSpaces = nameNoSpaces + name.charAt(i);
            }
        }
        nameNoSpaces = nameNoSpaces.toLowerCase();
        return context.getResources().getIdentifier(nameNoSpaces, "drawable", context.getPackageName());
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
                settingsPage.setClass(result_page.this,help.class);
                startActivity(settingsPage);
                return true;

            case R.id.action_settings:
                Intent settings = new Intent();
                settings.setClass(result_page.this, SettingsPage.class);
                startActivity(settings);
                return true;
            case R.id.action_home:
                Intent homePage = new Intent();
                homePage.setClass(result_page.this,MainActivity.class);
                startActivity(homePage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
