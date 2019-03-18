package edu.wit.mobileapp.partythyme;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class plant_info_add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info_add);

        Bundle bundle = this.getIntent().getExtras();
        //Get what plant this activity will be used for
        String name = bundle.getString("name");
        Plant plant = new Plant(name, this);

        //Update image based on plant
        ImageView plantImage = (ImageView) findViewById(R.id.plantImage);
        Bitmap plantBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(plant + "/" + name + ".png", "mipmap", this.getPackageName()));
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
                //TO DO - Bring up menu where you can add nickname and calendar lifespan...
                //Add plant to my plants

                //Go back to activity_main
                Intent intent = new Intent();
                intent.setClass(plant_info_add.this, MainActivity.class);
                //Don't think we need to send anything to the main page since it will just
                //grab its information from files
                //Bundle bundle = new Bundle();
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
