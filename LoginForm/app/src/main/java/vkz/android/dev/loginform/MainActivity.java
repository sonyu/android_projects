package vkz.android.dev.loginform;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   // String message = intent.getStringExtra(NewActivityName.EXTRA_MESSAGE);
    private static String username ;
    private static String password;
    DBhelper db = new DBhelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db.createDefault();

        Button btlogin = (Button)findViewById(R.id.btnLinkToLoginScreen);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView usernametv = (TextView)findViewById(R.id.name);
                TextView passwordtv = (TextView)findViewById(R.id.password);
                username = usernametv.getText().toString();
                password = passwordtv.getText().toString();
                username=username.toLowerCase();
              login();
            }
        });
    }
    void login(){
//        //String username =
//        if( (username.toLowerCase()).equals("vxkhanh")){
//            Toast.makeText(MainActivity.this, username.toString(), Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(getApplicationContext(),logindone.class);
//            startActivity(intent);
//        }else {
//            Toast.makeText(MainActivity.this, "TEst ok !username ="+ username.toString(), Toast.LENGTH_SHORT).show();
//        }

        Cursor cursor = db.checklogin(username,password);
        if (cursor.moveToFirst())
        {
            do
            {
               // String s1 = cursor.getString(cursor.getColumnIndex());
                String s2 = cursor.getString(cursor.getColumnIndex("login_user"));
                String s3 = cursor.getString(cursor.getColumnIndex("login_pwd"));
                if(s2.equals(username) &&s3.equals(password)){
                    Toast.makeText(MainActivity.this, "s2="+s2, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),logindone.class);
                    startActivity(intent);
                    cursor.close();
                }
                else{
                    Toast.makeText(MainActivity.this, "TEst ok !username ="+ s2, Toast.LENGTH_SHORT).show();
                }

            }while (cursor.moveToNext());
        }

    }
}
