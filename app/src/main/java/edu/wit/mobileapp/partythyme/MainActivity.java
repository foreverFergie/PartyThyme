package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import com.roomorama.caldroid.CaldroidFragment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String filename = "/data/data/" + this.getApplicationContext().getPackageName() + "/MyPlants.txt";

        Log.v("filename", filename);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Bitmap defaultImage;
        defaultImage = BitmapFactory.decodeResource(getResources(), R.drawable.default_plant);

        final List<plant_temp> plants = new ArrayList<>();
        List<String> dropDown=new ArrayList<>();
        dropDown.add("");
        dropDown.add("Edit");
        dropDown.add("Info");
        dropDown.add("Remove Plant");

        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader bufferedReader;
        context = getApplicationContext();

        TextView plantFact = (TextView) findViewById(R.id.plantFact);
        String[] possibleFacts = {
                "Brazil is named after a tree!",
                "Over 85% of plant life is found in the ocean!",
                "Bananas contain a natural chemical which can make people feel happy!",
                "The Amazon rainforest produces half the world's oxygen supply!",
                "Caffeine serves the function of a pesticide in coffee plants!",
                "Apple is 25% air, that's why it floats on water!",
                "The tears during cutting an onion are caused by sulfuric acid!",
                "The first potatoes were cultivated in Peru about 7,000 years ago!",
                "The first type of aspirin, painkiller and fever reducer came from the tree bark of a willow tree!",
                "Peaches, Pears, apricots, quinces, strawberries, and apples are members of the rose family!",
                "Around 2000 different types of plants are used by humans to make food!",
                "Cabbage has 91% water content!",
                "Banana is an Arabic word for fingers!",
                "The California redwood (coast redwood and giant sequoia) are the tallest and largest living organism in the world!",
                "Carrots were originally purple in color!",
                "Oak trees are struck by lightning more than any other tree!",
                "There are over 300,000 identified plant species!",
                "Vanilla flavoring comes from the pod of an orchid, Vanilla planifolia!"
        };
        int index = (int)(Math.random() * possibleFacts.length - 1);
        plantFact.setText(possibleFacts[index]);

        try{
            fis = new FileInputStream(new File(filename));
            isr = new InputStreamReader(fis);
            bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();

            String line;
            while((line = bufferedReader.readLine()) != null){
                plant_temp plant = new plant_temp();
                //Get nickname from line
                String pName = "";
                int i = 0;
                sb.append(line);
                while(sb.toString().charAt(i) != ','){
                    pName = pName+ sb.toString().charAt(i);
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
                plant.name = pNick;
                int plantImage = getPlantImage(this, pName);
                if(plantImage == 0){
                    plant.image = BitmapFactory.decodeResource(getResources(), R.drawable.default_plant);
                }
                else
                {
                    plant.image = BitmapFactory.decodeResource(getResources(), plantImage);
                }
                plants.add(plant);
                sb = new StringBuilder();
                Log.v("plant", plant.name + " added to plants list");
            }
            fis.close();
            isr.close();
            bufferedReader.close();
        }
        catch(Exception e){
            //e.printStackTrace();
        }

        final plantItemAdapter adapter;
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.plant_list);
        adapter = new plantItemAdapter(plants,this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        Button goToFullList = (Button) findViewById(R.id.view_full_list_btn);
        goToFullList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, result_page.class);
                startActivity(intent);
            }
        });




        CardView addAPlant = (CardView) findViewById(R.id.main_add_plant);
        addAPlant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, RealSearch.class);
                startActivity(intent);
            }
        });


//        Button calendarPage=(Button)findViewById(R.id.expandCalendar);
//        calendarPage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(MainActivity.this,Calendar.class);
//                startActivity(intent);
//            }
//        });

        //Calendar - uses caldroid so we can make events appear
        CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();


        //This time its our calendar file
        filename = "/data/data/" + this.getApplicationContext().getPackageName() + "/calendarPlants.txt";
        try {
            fis = new FileInputStream(new File(filename));
            isr = new InputStreamReader(fis);
            bufferedReader = new BufferedReader(isr);
            StringBuilder sb;

            String line;
            while((line = bufferedReader.readLine()) != null) {
                sb = new StringBuilder();
                //Get nickname from line
                String pNick = "";
                int i = 0;
                sb.append(line);
                while (sb.toString().charAt(i) != ',') {
                    pNick = pNick + sb.toString().charAt(i);
                    i++;
                }
                i++; //Move i past the comma
                //Get Date
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                String dateString = "";
                while(i < sb.toString().length()){
                    dateString = dateString + sb.toString().charAt(i);
                    i++;
                }
                Log.v("dateString", dateString);
                Date dateRepresentation = (Date)sdf.parse(dateString);
                ColorDrawable eventColor = new ColorDrawable(getResources().getColor(R.color.colorAccent));

                caldroidFragment.setBackgroundDrawableForDate(eventColor, dateRepresentation);
                caldroidFragment.refreshView();

            }
            fis.close();
            isr.close();
            bufferedReader.close();


        }catch(Exception e){
            e.printStackTrace();
        }







    }


    private void setCalenderWeek(){

        CalendarView calendar=(CalendarView) findViewById(R.id.calendar1);



    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_help:
                Intent helpPage =  new Intent();
                helpPage.setClass(MainActivity.this,help.class);
                startActivity(helpPage);
                return true;
            case R.id.action_settings:
                Intent settingsPage=new Intent();
                settingsPage.setClass(MainActivity.this,settings.class);
                startActivity(settingsPage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

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
}


