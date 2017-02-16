package vkz.android.dev.sqlitenew.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 12/14/2016.
 */
public class Note implements Serializable {
    private  int noteId;
    private  String noteTitle;
    private  String noteContent;

    public  Note(){

    }
    public Note (String noteTitle, String noteContent){
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    public Note (int noteId, String noteTitle, String noteContent){
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    @Override
    public String toString() {
        return this.noteTitle;
    }
}
