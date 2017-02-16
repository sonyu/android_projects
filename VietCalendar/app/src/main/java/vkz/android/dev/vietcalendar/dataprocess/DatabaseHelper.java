package vkz.android.dev.vietcalendar.dataprocess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2/7/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "descriptionDay";

    // Table Names
    private static final String TABLE_NG_AM = "ng_am";
    private static final String TABLE_NG_DG = "ng_dg";
    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_DAY = "day";
    private static final String KEY_DES = "des";

    private static final String CREATE_TABLE_NG_AM = "CREATE TABLE "
            + TABLE_NG_AM + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DAY
            + " TEXT," + KEY_DES  + " TEXT" + ")";

    private static final String CREATE_TABLE_NG_DG = "CREATE TABLE "
            + TABLE_NG_DG + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DAY
            + " TEXT," + KEY_DES  + " TEXT" + ")";
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NG_AM);
        db.execSQL(CREATE_TABLE_NG_DG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NG_AM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NG_DG);
        onCreate(db);
    }

    public List<Daydetail> getAllnote(){
        Log.i(LOG,"databaseHelper.getAllnote...");
        List<Daydetail> noteList = new ArrayList<Daydetail>();
        String selectQuerry = "SELECT * FROM "+ TABLE_NG_DG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuerry,null);

        if(cursor.moveToFirst()){
            do{
                Daydetail nte = new Daydetail();
                nte.setId(Integer.parseInt(cursor.getString(0)));
                nte.setDay(cursor.getString(1));
                nte.setDes(cursor.getString(2));
                noteList.add(nte);
            }while (cursor.moveToNext());
        }
        return noteList;
    }
    public int getNoteCount(){
        Log.i(LOG,"databaseHelper.getNoteCount...");
        String countQuery = "SELECT * FROM "+ TABLE_NG_DG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int updateNote(Daydetail nte){
        Log.i(LOG,"database Update note ...");
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DES,nte.getDes());
        contentValues.put(KEY_DAY,nte.getDay());
        return db.update(TABLE_NG_DG,contentValues,KEY_ID + "=?",new String[]{String.valueOf(nte.getId())} );
    }
    public void deleteNote(Daydetail nte){
        Log.i(LOG,"DatabaseNote detele Note function ...");
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NG_DG,KEY_ID + "=?", new String[]{String.valueOf(nte.getId())});
    }
    public void addNote(Daydetail nte){
        Log.i(LOG,"This addNote function is running ...Username ="+ nte.getDay());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DAY, nte.getDay());
        contentValues.put(KEY_DES,nte.getDes());
        db.insert(TABLE_NG_DG,null,contentValues);
        db.close();

    }
    public void createDefaultNote(){
        int count = this.getNoteCount();
        if(count==0){
            Daydetail nte24	 = new 	Daydetail("02-13","tết dương lich");	this.addNote(nte24	);
            Daydetail nte1	 = new 	Daydetail("01-01","tết dương lich");	this.addNote(nte1	);
            Daydetail nte2	 = new 	Daydetail("02-03","thành lập Đảng CSVN");	this.addNote(nte2	);
            Daydetail nte3	 = new 	Daydetail("02-15","valentin");	this.addNote(nte3	);
            Daydetail nte4	 = new 	Daydetail("02-27","thầy thuốc Việt Nam");	this.addNote(nte4	);
            Daydetail nte5	 = new 	Daydetail("03-08","quốc tế phụ nữ");	this.addNote(nte5	);
            Daydetail nte6	 = new 	Daydetail("03-26","thành lập Đoàn TNCS HCM");	this.addNote(nte6	);
            Daydetail nte7	 = new 	Daydetail("04-30","giải phóng miền nam");	this.addNote(nte7	);
            Daydetail nte8	 = new 	Daydetail("05-01","quốc tế lao động");	this.addNote(nte8	);
            Daydetail nte9	 = new 	Daydetail("05-07","chiến thắng Điện Biên Phủ");	this.addNote(nte9	);
            Daydetail nte10	 = new 	Daydetail("05-07","của mẹ	");	this.addNote(nte10	);
            Daydetail nte11	 = new 	Daydetail("06-01","quốc tế thiếu nhi");	this.addNote(nte11	);
            Daydetail nte12	 = new 	Daydetail("06-17","của bố");	this.addNote(nte12	);
            Daydetail nte13	 = new 	Daydetail("06-21","báo chí Việt Nam");	this.addNote(nte13	);
            Daydetail nte14	 = new 	Daydetail("06-28","gia đình Việt Nam");	this.addNote(nte14	);
            Daydetail nte15	 = new 	Daydetail("07-27","thương binh, liệt sỹ");	this.addNote(nte15	);
            Daydetail nte16	 = new 	Daydetail("08-19","cách mạng tháng 8(Công an nhân dân)");	this.addNote(nte16	);
            Daydetail nte17	 = new 	Daydetail("09-02","quốc khánh (Ngày chủ tịch Hồ Chí Minh qua đời)");	this.addNote(nte17	);
            Daydetail nte18	 = new 	Daydetail("10-01","quốc tế người cao tuổi");	this.addNote(nte18	);
            Daydetail nte19	 = new 	Daydetail("10-10","giải phóng thủ đô");	this.addNote(nte19	);
            Daydetail nte20	 = new 	Daydetail("10-20","phụ nữ Việt Nam");	this.addNote(nte20	);
            Daydetail nte21	 = new 	Daydetail("11-20","nhà giáo Việt Nam");	this.addNote(nte21	);
            Daydetail nte22	 = new 	Daydetail("12-22","thành lập quân đội nhân dân Việt Nam	");	this.addNote(nte22	);
            Daydetail nte23	 = new 	Daydetail("12-25","Noel");	this.addNote(nte23	);

        }
    }
    //cho lich am
    public List<Daydetail> getAllnoteA(){
        Log.i(LOG,"databaseHelper.getAllnoteA...");
        List<Daydetail> noteListA = new ArrayList<Daydetail>();
        String selectQuerry = "SELECT * FROM "+ TABLE_NG_AM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuerry,null);

        if(cursor.moveToFirst()){
            do{
                Daydetail nte = new Daydetail();
                nte.setId(Integer.parseInt(cursor.getString(0)));
                nte.setDay(cursor.getString(1));
                nte.setDes(cursor.getString(2));
                noteListA.add(nte);
            }while (cursor.moveToNext());
        }
        return noteListA;
    }
    public int getNoteCountA(){
        Log.i(LOG,"databaseHelper.getNoteCount...");
        String countQuery = "SELECT * FROM "+ TABLE_NG_AM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public int updateNoteA(Daydetail nte){
        Log.i(LOG,"database Update note ...");
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DES,nte.getDes());
        contentValues.put(KEY_DAY,nte.getDay());
        return db.update(TABLE_NG_AM,contentValues,KEY_ID + "=?",new String[]{String.valueOf(nte.getId())} );
    }
    public void deleteNoteA(Daydetail nte){
        Log.i(LOG,"DatabaseNote detele Note function ...");
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NG_AM,KEY_ID + "=?", new String[]{String.valueOf(nte.getId())});
    }
    public void addNoteA(Daydetail nte){
        Log.i(LOG,"This addNote function is running ...Username ="+ nte.getDay());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_DAY, nte.getDay());
        contentValues.put(KEY_DES,nte.getDes());
        db.insert(TABLE_NG_AM,null,contentValues);
        db.close();

    }
    public void createDefaultNoteA(){
        int count = this.getNoteCountA();
        if(count==0){
            Daydetail nte1	 = new 	Daydetail("01-17","không có gì đặc biệt");	this.addNoteA(nte1);
            Daydetail nte2	 = new 	Daydetail("01-14","123456");	this.addNoteA(nte2);
            Daydetail nte3	 = new 	Daydetail("01-01","mùng 1 tết");	this.addNoteA(nte3);
            Daydetail nte4	 = new 	Daydetail("01-02","mùng 2 tết");	this.addNoteA(nte4);
            Daydetail nte5	 = new 	Daydetail("01-03","mùng 3 tết");	this.addNoteA(nte5);
            Daydetail nte6	 = new 	Daydetail("01-15","tết Thượng nguyên");	this.addNoteA(nte6);
            Daydetail nte7	 = new 	Daydetail("02-15","rằm tháng hai");	this.addNoteA(nte7);
            Daydetail nte8	 = new 	Daydetail("03-10","giỗ tổ Hùng Vương");this.addNoteA(nte8);
            Daydetail nte9	 = new 	Daydetail("03-03","tết Thanh minh (Lễ tảo mộ)");this.addNoteA(nte9);
            Daydetail nte10	 = new 	Daydetail("05-05","tết Đoan ngọ (Tết Đoan dương, tết Đoan ngũ, tết nửa năm, tết giết sâu bọ)");	this.addNoteA(nte10);
            Daydetail nte11	 = new 	Daydetail("07-07","tết Trung nguyên (Rằm tháng bảy, Vu Lan báo hiếu, ngày xá tội vong nhân)");	this.addNoteA(nte11);
            Daydetail nte12	 = new 	Daydetail("08-15","tết Trung thu");	this.addNoteA(nte12);
            Daydetail nte13	 = new 	Daydetail("10-15","tết Hạ nguyên (Tết cơm mới)");	this.addNoteA(nte13);
            Daydetail nte14	 = new 	Daydetail("12-23","tết Ông Táo");	this.addNoteA(nte14);
            Daydetail nte15	 = new 	Daydetail("04-15","lễ Phật đản");	this.addNoteA(nte15);
        }
    }
}
