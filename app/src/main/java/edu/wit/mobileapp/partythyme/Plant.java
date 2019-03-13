package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class Plant {

    String name;
    boolean annual;
    boolean biennial;
    boolean perrenial;
    boolean indoor;
    String color;
    int maintenance;
    String waterMaintenance;
    String soil;
    String fertilizer;
    String sunMaintainSummer;
    String sunMaintainWinter;
    String PHMaintain;
    String pruning;
    boolean flowering;
    boolean	poison;
    boolean tubular;
    boolean succulent;
    boolean cactus;
    boolean tree;
    boolean landscaping;
    String hardinessZone;

    public Plant(String n, Context c){
        name = n;

        DatabaseHelper myDb = new DatabaseHelper(c);
        String[] columns = null;
        String where = "name = ?";
        String[] where_args = {name};
        String group_by = null;
        String having = null;
        String order_by = "name";
        Cursor cursor = myDb.db.query("plants", columns, where, where_args, group_by, having, order_by);
        while(cursor.moveToNext()){
            annual = intToBool(cursor.getInt(cursor.getColumnIndex("ANNUAL")));
            biennial = intToBool(cursor.getInt(cursor.getColumnIndex("BIENNIAL")));
            perrenial = intToBool(cursor.getInt(cursor.getColumnIndex("PERENNIAL")));
            indoor = intToBool(cursor.getInt(cursor.getColumnIndex("INDOOR")));
            color = cursor.getString(cursor.getColumnIndex("COLOR"));
            maintenance = cursor.getInt(cursor.getColumnIndex("MAINTENANCE"));
            waterMaintenance = cursor.getString(cursor.getColumnIndex("WATERMAINTAIN"));
            soil = cursor.getString(cursor.getColumnIndex("SOIL"));
            fertilizer = cursor.getString(cursor.getColumnIndex("FERTILIZER"));
            sunMaintainSummer = cursor.getString(cursor.getColumnIndex("SUNMAINTAINSUM"));
            sunMaintainWinter = cursor.getString(cursor.getColumnIndex("SUNMAINTAINWIN"));
            PHMaintain = cursor.getString(cursor.getColumnIndex("PHMAINTAIN"));
            pruning = cursor.getString(cursor.getColumnIndex("PRUNING"));
            flowering = intToBool(cursor.getInt(cursor.getColumnIndex("FLOWERING")));
            poison = intToBool(cursor.getInt(cursor.getColumnIndex("POISON")));
            tubular = intToBool(cursor.getInt(cursor.getColumnIndex("TUBULAR")));
            succulent = intToBool(cursor.getInt(cursor.getColumnIndex("SUCCULENT")));
            cactus = intToBool(cursor.getInt(cursor.getColumnIndex("CACTUS")));
            tree = intToBool(cursor.getInt(cursor.getColumnIndex("TREE")));
            landscaping = intToBool(cursor.getInt(cursor.getColumnIndex("LANDSCAPING")));
            hardinessZone = cursor.getString(cursor.getColumnIndex("HARDINESSZONE"));
        }
    }

    private boolean intToBool(int n){
        if(n == 0){
            return false;
        }
        return true;
    }
}
