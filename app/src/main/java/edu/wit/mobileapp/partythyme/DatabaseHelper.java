package edu.wit.mobileapp.partythyme;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_PATH;
    public static String DATABASE_NAME;
    public static final String TABLE_NAME = "plants";
    public Context mycontext;

    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mycontext=context;
        DATABASE_PATH = "/data/data/" + mycontext.getApplicationContext().getPackageName() + "/databases/";
        DATABASE_NAME = "plants.db";

        boolean dbExists = checkDatabaseExists();
        if(dbExists){
            openDatabase();
        }
        else{
            Log.v("myApp", "Database does not exist");
            try {
                createDatabase();
            } catch (IOException e) {
                Log.v("myApp", "error creating database - " + e.getMessage());
            }
        }
    }

    public void createDatabase() throws IOException {
        boolean dbexist = checkDatabaseExists();
        if(dbexist) {
            Log.v("myApp", "Database exists");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outfilename = DATABASE_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }


    public void openDatabase() throws SQLException {
        //Open the database
        String mypath = DATABASE_PATH + DATABASE_NAME;
        db = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }


    //Shouldn't be called during scope of our project but just in case
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            copydatabase();
        }
        catch (java.io.IOException e){
            Log.v("myApp", "Failed to update database - " + e.getMessage());
        }
    }

    private boolean checkDatabaseExists() {
        //SQLiteDatabase checkdb = null;
        boolean checkdb = false;
        try {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch(SQLiteException e) {
            Log.v("myApp", "Database doesnt exist - " + e.getMessage());
        }
        return checkdb;
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }
}
