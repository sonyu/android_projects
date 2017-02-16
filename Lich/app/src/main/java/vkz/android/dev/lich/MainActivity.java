package vkz.android.dev.lich;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;



import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Calendar dateandtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateandtime = Calendar.getInstance(Locale.US);
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View arg0) {

                DatePickerDailog dp = new DatePickerDailog(MainActivity.this,
                        dateandtime, new DatePickerDailog.DatePickerListner() {

                    @Override
                    public void OnDoneButton(Dialog datedialog, Calendar c) {
                        datedialog.dismiss();
                        dateandtime.set(Calendar.YEAR, c.get(Calendar.YEAR));
                        dateandtime.set(Calendar.MONTH,
                                c.get(Calendar.MONTH));
                        dateandtime.set(Calendar.DAY_OF_MONTH,
                                c.get(Calendar.DAY_OF_MONTH));
                        ((Button)arg0).setText(new SimpleDateFormat("MMMM dd yyyy")
                                .format(c.getTime()));
                    }

                    @Override
                    public void OnCancelButton(Dialog datedialog) {
                        // TODO Auto-generated method stub
                        datedialog.dismiss();
                    }
                });
                dp.show();




            }
        });
    }


}