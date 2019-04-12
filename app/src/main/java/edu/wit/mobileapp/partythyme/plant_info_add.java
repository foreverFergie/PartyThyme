package edu.wit.mobileapp.partythyme;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.CalendarContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class plant_info_add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info_add);

        Toolbar toolbar = findViewById(R.id.plantToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final Context mContext = getApplicationContext();

        final String filename = "/data/data/" + this.getApplicationContext().getPackageName() + "/MyPlants.txt";

        Bundle bundle = this.getIntent().getExtras();
        //Get what plant this activity will be used for
        final String name = bundle.getString("name");
        final Plant plant = new Plant(name,this);

        //Update image based on plant
        ImageView plantImage = (ImageView) findViewById(R.id.plantImage);
        int plantImageId = getPlantImage(this, name);
        Bitmap plantBitmap;
        if(plantImageId > 0) {
            plantBitmap = BitmapFactory.decodeResource(getResources(), getPlantImage(this, name));
        }
        else {
            plantBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_plant);
        }
        plantImage.setImageBitmap(plantBitmap);

        //Update displayed plant name based on plant
        TextView dispName = (TextView) findViewById(R.id.plantName);
        dispName.setText(name);

        //List all plant facts in database
        ListView plantFacts =(ListView) findViewById(R.id.factList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        adapter.add("Annual: " + boolToYesOrNo(plant.annual));
        adapter.add("Biennial: " + boolToYesOrNo(plant.biennial));
        adapter.add("Perennial: " + boolToYesOrNo(plant.perrenial));
        adapter.add("Indoor: " + boolToYesOrNo(plant.indoor));
        adapter.add("Maintenance (1-3): " + plant.maintenance);
        adapter.add("Water Maintenance: " + plant.waterMaintenance);
        adapter.add("Soil: " + plant.soil);
        adapter.add("Fertilizer: " + plant.fertilizer);
        adapter.add("Sun Level Summer: " + plant.sunMaintainSummer);
        adapter.add("Sun Level Winter: " + plant.sunMaintainWinter);
        adapter.add("PH Level: " + plant.PHMaintain);
        adapter.add("Pruning: " + plant.pruning);
        adapter.add("Flowering: " + boolToYesOrNo(plant.flowering));
        adapter.add("Poisonous: " + boolToYesOrNo(plant.poison));
        adapter.add("Tubular: " + boolToYesOrNo(plant.tubular));
        adapter.add("Cactus: " + boolToYesOrNo(plant.cactus));
        adapter.add("Tree: " + boolToYesOrNo(plant.tree));
        adapter.add("Can be used for landscaping: " + boolToYesOrNo(plant.landscaping));
        adapter.add("USDA Hardiness Zone: " + plant.hardinessZone);
        plantFacts.setAdapter(adapter);


        Button addToPlants = (Button) findViewById(R.id.addToMyPlantsBtn);
        addToPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Custom Dialog
                final Dialog dialog = new Dialog(plant_info_add.this);
                dialog.setContentView(R.layout.customize_plant);
                //dialog.setTitle("Customize Plant");

                //Set the custom dialog components
                Button finish = (Button) dialog.findViewById(R.id.add_plant);
                final EditText nickName = (EditText) dialog.findViewById(R.id.nickname);
                nickName.setText(name);
                finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FileOutputStream outputStream;
                        try{
                            outputStream = new FileOutputStream(filename, true);
                            String nameAndNickName = name + "," + nickName.getText() + "\n";
                            outputStream.write(nameAndNickName.getBytes());
                            outputStream.close();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }


                        setCalenderEvents(nickName.getText().toString());

                        dialog.dismiss();
                        //Go back to activity_main
                        Intent intent = new Intent();
                        intent.setClass(plant_info_add.this, MainActivity.class);
                        //Don't think we need to send anything to the main page since it will just
                        //grab its information from files
                        //Bundle bundle = new Bundle();
                        startActivity(intent);
                    }
                });
                dialog.show();
            }
        });
    }

    private void setCalenderEvents(String nickName){

        CalendarView calendarView=(CalendarView)findViewById(R.id.calendarView);

        Calendar b = Calendar.getInstance();
        int currentMonth=b.get(Calendar.MONTH)+1;
        int currentDay=b.get(Calendar.DAY_OF_MONTH);
        int currentYear=b.get(Calendar.YEAR);

        for(int i=0;i<21;i+=3){

            if(currentMonth%2==1 && currentDay<=28){
                currentDay+=3;
            }else if(currentMonth%2==1 && currentDay>28){
                int diff = 31-currentDay;

                currentDay=3-diff;
                currentMonth++;
            }else if(currentMonth==2 && currentDay<=25){
                currentDay+=3;
            }else if(currentMonth==2 && currentDay>25){
                int diff = 28-currentDay;

                currentDay=3-diff;
                currentMonth++;
            }else if(currentMonth%2==0 && currentDay<=27){
                currentDay++;
            }else if(currentMonth!=12 && currentDay>27){
                int diff = 30-currentDay;

                currentDay=3-diff;
                currentMonth++;
            }else{
                int diff = 30-currentDay;

                currentDay=3-diff;
                currentMonth++;
                currentYear++;
            }

            b.set(currentYear,currentMonth,currentDay);

            Intent intent=new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.Events.DTSTART,b)
                    .putExtra(CalendarContract.Events.ALL_DAY,1)
                    .putExtra(CalendarContract.Events.TITLE,"Water your plant "+nickName+"!")
                    .putExtra(CalendarContract.Events.ALLOWED_REMINDERS,1)
                    .putExtra(CalendarContract.Events.CALENDAR_ID,4);


            startActivity(intent);
        }






    }

    private String boolToYesOrNo(boolean b){
        if(b){
            return "Yes";
        }
        return "No";
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
                settingsPage.setClass(plant_info_add.this,help.class);
                startActivity(settingsPage);
                return true;

            case R.id.action_settings:
                Intent settings = new Intent();
                settings.setClass(plant_info_add.this,settings.class);
                startActivity(settings);
                return true;
            case R.id.action_home:
                Intent homePage = new Intent();
                homePage.setClass(plant_info_add.this,MainActivity.class);
                startActivity(homePage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
