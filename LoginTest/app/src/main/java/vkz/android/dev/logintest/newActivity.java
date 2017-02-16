package vkz.android.dev.logintest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import vkz.android.dev.logintest.bean.note;

public class newActivity extends AppCompatActivity {
    note notelist;
    String username;
    String password ;
    String repassword ;
    EditText passwordEdit ;
    EditText repasswordEdit;
    databaseHelper db = new databaseHelper(this);
    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;
    private static final int MY_REQUEST_CODE = 1000;
    private static final int MODE_CREATE = 1;
    private static final int MODE_EDIT = 2;
    private int mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        final EditText usernameEdit = (EditText)findViewById(R.id.username);
        final EditText passwordEdit = (EditText)findViewById(R.id.password);
        final EditText repasswordEdit = (EditText)findViewById(R.id.repassword);

        Button regbtn = (Button)findViewById(R.id.btn_register);
        Intent intent = this.getIntent();
        this.notelist = (note) intent.getSerializableExtra("note");
        if(notelist== null)  {
            this.mode = MODE_CREATE;
        } else  {
            this.mode = MODE_EDIT;
            usernameEdit.setText(notelist.getLogin_username());
            passwordEdit.setText(notelist.getLogin_password());
        }
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username =usernameEdit.getText().toString();
                password = passwordEdit.getText().toString();
                repassword = repasswordEdit.getText().toString();
                Toast.makeText(getApplicationContext(),"username ="+username+" pas="+password+" repassword="+repassword, Toast.LENGTH_LONG).show();
               if( checkValid()==true){
                   Toast.makeText(getApplicationContext(),"Register successful", Toast.LENGTH_LONG).show();

                  if(mode == MODE_CREATE){
                      notelist = new note(username,password);
                      db.addNote(notelist);
                      Intent intent = new Intent(getApplicationContext(), testlist.class);
                      startActivity(intent);
                  }else {
                      notelist.setLogin_username(username);
                      notelist.setLogin_password(password);
                      db.updateNote(notelist);
                      Intent intent = new Intent(getApplicationContext(), testlist.class);
                      startActivity(intent);
                  }
               }else {
                   Toast.makeText(getApplicationContext(),"Register failed", Toast.LENGTH_LONG).show();
               }
            }
        });
    }
    boolean checkValid(){
        boolean check = true;
        //String username = usernameEdit.getText().toString();
       // String password = passwordEdit.getText().toString();
       // String repassword = repasswordEdit.getText().toString();
        if(username.isEmpty()|| username.length()<4){
            Toast.makeText(getApplicationContext(),"username cannot empty and the length is greater then 4!", Toast.LENGTH_LONG).show();
            check =false;
        }
        if(password.isEmpty()|| password.length()<6){
            Toast.makeText(getApplicationContext(),"password cannot empty and the length is greater then 6!", Toast.LENGTH_LONG).show();
            check =false;
        }
        if(!password.equals(repassword)){
            Toast.makeText(getApplicationContext(),"password and repassword must be the same !", Toast.LENGTH_LONG).show();
            check =false;
        }
        return  check;
    }
    public void btn_cancel(View view)  {
        // Không làm gì, trở về MainActivity.
        this.onBackPressed();
    }

    // Khi Activity này hoàn thành,
    // có thể cần gửi phản hồi gì đó về cho Activity đã gọi nó.
    @Override
    public void finish() {

        // Chuẩn bị dữ liệu Intent.
        //Intent data = new Intent();
        // Yêu cầu MainActivity refresh lại ListView hoặc không.
       // data.putExtra("needRefresh", needRefresh);

        // Activity đã hoàn thành OK, trả về dữ liệu.
        //0this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }


}
