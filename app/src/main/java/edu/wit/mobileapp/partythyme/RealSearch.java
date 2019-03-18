package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class RealSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_search);

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

                final ArrayAdapter<String> result = new ArrayAdapter<String>(RealSearch.this, android.R.layout.simple_list_item_1);

                //Add plants by name
                while(cursor.moveToNext()) {
                    Plant p = new Plant(cursor.getString(cursor.getColumnIndex("NAME")), RealSearch.this);
                    result.add(p.name);
                    Log.v("myApp", p.name);
                }


                ListView plantList = (ListView) findViewById(R.id.search_list_result);
                plantList.setAdapter(result);

                plantList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(RealSearch.this, plant_info_add.class);
                        Bundle bundle = new Bundle();
                        try {
                            bundle.putString("name", result.getItem(position));
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
}
