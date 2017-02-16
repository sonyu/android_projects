package vkz.android.dev.logintest;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import vkz.android.dev.logintest.bean.note;


public class LoginActivity extends AppCompatActivity {
    private static String username;
    private static String password;
    private final List<note> noteList = new ArrayList<note>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btlg = (Button)findViewById(R.id.btn_login);
        final EditText e_username = (EditText)findViewById(R.id.username_edit);
        final EditText e_password = (EditText)findViewById(R.id.password_edit);
        databaseHelper db = new databaseHelper(this);
        db.createDefaultNote();
        List<note> list = db.getAllnote();
        noteList.addAll(list);
        btlg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = e_username.getText().toString();
                password = e_password.getText().toString();
                //Toast.makeText(getApplicationContext(),"test done ! user ="+ username +" pass="+password, Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(LoginActivity.this,viewActivity.class);
               // startActivity(intent);


                Bundle bundle = new Bundle();
               if( checkLogin(username,password)==true){
                   //Intent intent = new Intent(getApplicationContext(), viewActivity.class);

                  // Bundle extra = new Bundle();
                  // extra.putSerializable("objects", (Serializable) noteList);

                   // Intent intent = new Intent(getBaseContext(), ShowSpread.class);
                  // intent.putExtra("extra", extra);

                   // intent.putExtra("test", (Serializable) noteList);
                   Intent intent = new Intent(getApplicationContext(), testlist.class);
                   startActivity(intent);
               }else{
                   Toast.makeText(getApplicationContext(),"Login failed !", Toast.LENGTH_LONG).show();
               }
            }
        });
        TextView createNew = (TextView)findViewById(R.id.link_to_register);
        createNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentnew = new Intent(getApplicationContext(),newActivity.class);
                startActivity(intentnew);
            }
        });
    }
    public  boolean checkLogin(String username, String password){

        boolean result=false;
        if(noteList.size()>0){
            for (int i = 0; i < this.noteList.size(); i++) {
                if(this.noteList.get(i).getLogin_username().equals(username)&& this.noteList.get(i).getLogin_password().equals(password)) {

                    result= true;
                    i=this.noteList.size();
                }else
                    result= false;
            }

        }
        return  result;
    }

}
