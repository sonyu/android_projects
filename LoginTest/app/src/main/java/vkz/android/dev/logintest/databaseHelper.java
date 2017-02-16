package vkz.android.dev.logintest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vkz.android.dev.logintest.bean.note;

/**
 * Created by Administrator on 12/24/2016.
 */
public class databaseHelper extends SQLiteOpenHelper {
    private static final String TAG ="SQLite";
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "login_db";
    private static final String TABLE_NAME = "login_tbl";
    private static final String COLUMN_ID = "login_id";
    private static final String COLUMN_USER = "login_username";
    private static final String COLUMN_PWD = "login_password";


    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,"databaseHelper.onCreate...");
        String script = "CREATE TABLE "+TABLE_NAME +"("
                        + COLUMN_ID +" INTEGER PRIMARY KEY,"
                        + COLUMN_USER + " TEXT,"
                        + COLUMN_PWD + " TEXT)";
        db.execSQL(script);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG,"databaseHelper.onUpgreate...");
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public List<note>getAllnote(){
        Log.i(TAG,"databaseHelper.getAllnote...");
        List<note> noteList = new ArrayList<note>();
        String selectQuerry = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuerry,null);

        if(cursor.moveToFirst()){
            do{
                note nte = new note();
                nte.setLogin_id(Integer.parseInt(cursor.getString(0)));
                nte.setLogin_username(cursor.getString(1));
                nte.setLogin_password(cursor.getString(2));
                noteList.add(nte);
            }while (cursor.moveToNext());
        }
        return noteList;
    }

    public int getNoteCount(){
        Log.i(TAG,"databaseHelper.getNoteCount...");
        String countQuery = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int updateNote(note nte){
        Log.i(TAG,"database Update note ...");
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER,nte.getLogin_username());
        contentValues.put(COLUMN_PWD,nte.getLogin_password());
        return db.update(TABLE_NAME,contentValues,COLUMN_ID + "=?",new String[]{String.valueOf(nte.getLogin_id())} );
    }
    public void deleteNote(note nte){
        Log.i(TAG,"DatabaseNote detele Note function ...");
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME,COLUMN_ID + "=?", new String[]{String.valueOf(nte.getLogin_id())});
    }
    public void createDefaultNote(){
        int count = this.getNoteCount();
        if(count==0){
            note nte1 = new note("admin","123456");
            this.addNote(nte1);
            note nte2 = new note("a","123456");
            this.addNote(nte2);
        }
    }
    public void addNote(note nte){
        Log.i(TAG,"This addNote function is running ...Username ="+ nte.getLogin_username());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER, nte.getLogin_username());
        contentValues.put(COLUMN_PWD,nte.getLogin_password());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();

    }
}
