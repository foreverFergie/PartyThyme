package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RealSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Context context = this;

        Button searchButton = (Button) findViewById(R.id.search_plants_button);

        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText searchBar = (EditText) findViewById(R.id.search_plants_bar);
                String search = searchBar.getText().toString();
                Log.v("myApp", "search text: " + search);


                DatabaseHelper myDb = new DatabaseHelper(RealSearch.this);
                String[] columns = {"name"};
                String where = "name LIKE '%" + search + "%'";
                String[] where_args = null;
                String group_by = null;
                String having = null;
                String order_by = "name";
                Cursor cursor = myDb.db.query("plants", columns, where, where_args, group_by, having, order_by);

                Bitmap defaultImage = BitmapFactory.decodeResource(getResources(), R.drawable.default_plant);
                List<ListItem> list = new ArrayList<ListItem>();


                //Add plants by name
                while(cursor.moveToNext()) {
                    ListItem myItem = new ListItem();
                    Plant p = new Plant(cursor.getString(cursor.getColumnIndex("NAME")), RealSearch.this);
                    myItem.name = p.name;
                    int plantImageNum = getPlantImage(RealSearch.this, p.name);
                    if(plantImageNum > 0) {
                        myItem.image = BitmapFactory.decodeResource(getResources(), plantImageNum);
                    }
                    else
                    {
                        myItem.image = BitmapFactory.decodeResource(getResources(), R.drawable.default_plant);
                    }
                    list.add(myItem);
                    Log.v("myApp", p.name);
                }

                final ListItemAdapter adapter;
                adapter = new ListItemAdapter(RealSearch.this, 0, list);

                ListView plantList = (ListView) findViewById(R.id.search_list_result);
                plantList.setAdapter(adapter);

                plantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(RealSearch.this, plant_info_add.class);
                        Bundle bundle = new Bundle();
                        try {
                            bundle.putString("name", adapter.getItem(position).name);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }catch(java.lang.NullPointerException e){
                            //Just do nothing
                            Log.v("myApp", "Not in list error");
                        }
                    }
                });
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
                settingsPage.setClass(RealSearch.this,help.class);
                startActivity(settingsPage);
                return true;
            case R.id.action_home:
                Intent homePage = new Intent();
                homePage.setClass(RealSearch.this,MainActivity.class);
                startActivity(homePage);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
