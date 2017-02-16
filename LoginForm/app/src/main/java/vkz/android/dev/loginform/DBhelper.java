package vkz.android.dev.loginform;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 12/22/2016.
 */
public class DBhelper extends SQLiteOpenHelper{
    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "login_manage";
    private static final String TABLE_LOGIN = "Logintb";
    private static final String COLUMN_LOGIN_ID ="login_Id";
    private static final String COLUMN_LOGIN_USER ="login_user";
    private static final String COLUMN_LOGIN_PWD = "login_pwd";


    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,"DatabaseHelper.onCreate...");
        String script = "CREATE TABLE "+ TABLE_LOGIN +
                "("+COLUMN_LOGIN_ID + " INTEGER PRIMARY KEY, "+
                COLUMN_LOGIN_USER+" TEXT,"+COLUMN_LOGIN_PWD+" TEXT)";
        db.execSQL(script);
        String querry =  "INSERT INTO "+TABLE_LOGIN+ " VALUES(1,'vxkhanh','123456')";
        db.execSQL(querry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG,"DataBaseHelper.onUpgreate...");
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_LOGIN);
        onCreate(db);
    }

    public Cursor checklogin(String name, String pwd){
        Cursor cursor;
        SQLiteDatabase db = this.getWritableDatabase();
        String script = "SELECT * FROM "+ TABLE_LOGIN +
                " WHERE "+COLUMN_LOGIN_USER + " ='"+ name
                +"' AND "+COLUMN_LOGIN_PWD + " = '"+ pwd+"'";


       // Cursor cursor= db.rawQuery(script,null);
        try {
            cursor = db.rawQuery(script,null);
            return cursor;
        } catch (SQLException e) {
            return null;
        }
    }
    public void createDefault(){
        SQLiteDatabase db = this.getWritableDatabase();
        String querry =  "INSERT INTO "+TABLE_LOGIN+ " VALUES(1,'vxkhanh','123456')";
        try {
            db.execSQL(querry);
            //Toast.makeText(this, "insert success", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
           // Toast.makeText(this, "insert failed", Toast.LENGTH_SHORT).show();
        }

    }
}
