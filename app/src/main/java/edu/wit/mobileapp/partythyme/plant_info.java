package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

import static edu.wit.mobileapp.partythyme.plant_info_add.getPlantImage;

public class plant_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);

        Bundle bundle = this.getIntent().getExtras();
        //Get what plant this activity will be used for
        final String name = bundle.getString("name");
        final String nick = bundle.getString("nick");
        final Plant plant = new Plant(name, this);

        //Update image based on plant
        ImageView plantImage = (ImageView) findViewById(R.id.plantImage);
        int plantImageId = getPlantImage(this, nick); //I swapped them in an earlier file. Easier this way
        Bitmap plantBitmap;
        if(plantImageId > 0) {
            plantBitmap = BitmapFactory.decodeResource(getResources(), plantImageId);
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

        Button removePlant = (Button) findViewById(R.id.removePlantBtn);
        final Context c = this;
        removePlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlantFileHelper plantFileHelper = new PlantFileHelper(c);
                boolean didIRemove = plantFileHelper.removePlant(name, nick);
                Log.v("myApp", "Removed Plant Status: " + didIRemove);

                Intent intent = new Intent(plant_info.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }



    private String boolToYesOrNo(boolean b){
        if(b){
            return "Yes";
        }
        return "No";
    }


}
