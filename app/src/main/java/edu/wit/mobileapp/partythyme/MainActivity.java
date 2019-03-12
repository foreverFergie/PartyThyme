package edu.wit.mobileapp.partythyme;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);



        myDb = new DatabaseHelper(this);
        String[] columns = {"name", "annual", "biennial"};
        String where = "name = ?";
        String[] where_args = {"Basil"};
        String group_by = null;
        String having = null;
        String order_by = "name";
        Cursor cursor = myDb.db.query("plants", columns, where, where_args, group_by, having, order_by);
        while(cursor.moveToNext()){
              String name = cursor.getString(cursor.getColumnIndex("NAME"));
              int annual = cursor.getInt(cursor.getColumnIndex("ANNUAL")) ;
              int biennial = cursor.getInt(cursor.getColumnIndex("BIENNIAL"));
              Log.v("myApp", "name: " + name + ", annual: " + annual + ", biennial: " + biennial);
              System.out.println(name);
        }
    }
}
