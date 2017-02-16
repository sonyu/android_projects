package vkz.android.dev.caculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public String rawData="";
    public boolean calStatus =true;
    public Reformatdata format = new Reformatdata();
    public calculate calc   = new calculate();
    public  stand  standar = new stand();
    public mul_operator mul = new mul_operator();

    //public
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void buttonClicked(View view){
//        rawData = rawData+"abcdae";
        TextView tv_rawData = (TextView)findViewById(R.id.tv_rawData);

//        Toast.makeText(getApplicationContext(),tv_rawData.getText().toString(),Toast.LENGTH_LONG).show();
//        if(calStatus==false){
//            rawData ="";
//            calStatus=true;
//        }
//        if(rawData.length()>1&& rawData.substring(0,1).equals("0")){
//           if(!rawData.substring(0,2).equals("0.")){
//               rawData = rawData.substring(1,rawData.length());
//           }
//        }
        switch(view.getId()){
            case R.id.bt_one :
                rawData = rawData+ "1";
                rawData = format.check_zero(rawData);
                break;
            case R.id.bt_two :
                rawData = rawData+ "2";
                rawData = format.check_zero(rawData);
                break;
            case R.id.bt_three :
                rawData = rawData+ "3";
                rawData = format.check_zero(rawData);
                break;
            case R.id.bt_four :
                rawData = rawData+ "4";
                rawData = format.check_zero(rawData);
                break;
            case R.id.bt_five :
                rawData = rawData+ "5";
                rawData = format.check_zero(rawData);
                break;
            case R.id.bt_six :
                rawData = rawData+ "6";
                rawData = format.check_zero(rawData);
                break;
            case R.id.bt_seven :
                rawData = rawData+ "7";
                rawData = format.check_zero(rawData);
                break;
            case R.id.bt_eight :
                rawData = rawData+ "8";
                rawData = format.check_zero(rawData);
                break;
            case R.id.bt_nine :
                rawData = rawData+ "9";
                rawData = format.check_zero(rawData);
                break;
            case R.id.bt_zero :
                    rawData = rawData+ "0";
                    rawData = format.check_zero(rawData);
                break;
            case R.id.bt_sum :
                rawData = rawData+ "+";
               rawData = format.CheckRawData(rawData);
                break;
            case R.id.bt_sub :
                rawData = rawData+ "-";
                rawData = format.check_sub(rawData);
               //rawData = format.CheckRawData(rawData);

                break;
            case R.id.bt_mul :
                rawData = rawData+ "x";
                rawData = format.CheckRawData(rawData);
                break;
            case R.id.btn_div :
                rawData = rawData+ "\u00F7";
                rawData = format.CheckRawData(rawData);
                break;
            case R.id.bt_c :
                rawData = " ";
            case R.id.btn_back :
               if(rawData.length()>0){
                   rawData = rawData.substring(0,rawData.length()-1);
               }
                break;
            case R.id.bt_dot :
                if(rawData.length()==0){
                    rawData = rawData+ "0.";
                }else {
                    rawData = rawData +".";
                    rawData = format.check_btndot(rawData);

                }
                break;
            case R.id.btn_equals :
               //rawData= calc.calculate(rawData);
               // calStatus= false;
                //rawData = standar.stand_string(rawData);
                rawData = calc.gencalculate(rawData);
                rawData = mul.remove_rezo(rawData);
                break;
            default :
                break;
        }

        tv_rawData.setText(rawData);
       // Toast.makeText(getApplicationContext(),rawData,Toast.LENGTH_LONG).show();
    }

}
