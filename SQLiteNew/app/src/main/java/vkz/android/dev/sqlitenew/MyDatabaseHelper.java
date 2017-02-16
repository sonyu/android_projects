package vkz.android.dev.sqlitenew;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vkz.android.dev.sqlitenew.bean.Note;

/**
 * Created by Administrator on 12/14/2016.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "Note_Manager";


    // Tên bảng: Note.
    private static final String TABLE_NOTE = "Note";

    private static final String COLUMN_NOTE_ID ="Note_Id";
    private static final String COLUMN_NOTE_TITLE ="Note_Title";
    private static final String COLUMN_NOTE_CONTENT = "Note_Content";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,"DatabaseHelper.onCreate...");
        String script = "CREATE TABLE " + TABLE_NOTE +"("
                + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NOTE_TITLE +" TEXT,"
                + COLUMN_NOTE_CONTENT + " TEXT"+")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG,"DataBaseHelper.onUpgreate...");
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NOTE);
        onCreate(db);
    }
    public List<Note>getAllNotes(){
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );
        List<Note> noteList = new ArrayList<Note>();

        String selectQuerry = "SELECT * FROM "+ TABLE_NOTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuerry,null);

        if(cursor.moveToFirst()){
            do{
                Note note = new Note();
                note.setNoteId(Integer.parseInt(cursor.getString(0)));
                note.setNoteTitle(cursor.getString(1));
                note.setNoteContent(cursor.getString(2));
                noteList.add(note);
            }while (cursor.moveToNext());
        }

        return  noteList;
    }

    public int getNotesCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... ");
        String countQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int updateNote(Note note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getNoteTitle());
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
        contentValues.put(COLUMN_NOTE_CONTENT,note.getNoteContent());

        return  db.update(TABLE_NOTE,contentValues, COLUMN_NOTE_ID + "=?",new String[]{String.valueOf(note.getNoteId())} );
    }
    public void deleteNote(Note note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getNoteTitle());
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NOTE, COLUMN_NOTE_ID + "=?", new String[]{String.valueOf(note.getNoteId())});
    }

    public void createDefaultNotesIfNeed() {
        int count = this.getNotesCount();
        if(count ==0 ) {
            Note note1 = new Note("Firstly see Android ListView",
                    "See Android ListView Example in o7planning.org");
            Note note2 = new Note("Learning Android SQLite",
                    "See Android SQLite Example in o7planning.org");
            this.addNote(note1);
            this.addNote(note2);
        }
    }
    public void addNote(Note note) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_NOTE, null, values);


        // Đóng kết nối database.
        db.close();
    }

}
