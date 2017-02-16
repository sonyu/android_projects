package vkz.android.dev.caculator;

import android.widget.Toast;

/**
 * Created by Administrator on 1/4/2017.
 */
public class Reformatdata {
    //private String rawData;
    public final String operator_sum = "+";
    public final String operator_sub = "-";
    public final String operator_mul = "x";
    public final String operator_div = "\u00F7";
    public final String operator_equals = "=";

    public String CheckRawData( String Raw){
        int n = Raw.length();
        String check1;
        String check2;
//       if(i>=2&&(!(Raw.substring(0,1).equals("+")||Raw.substring(0,1).equals("\u00F7")||Raw.substring(0,1).equals("x")))){
        check1 = Raw.substring(n-2,n-1);
        check2 = Raw.substring(n-1,n);
//           if((check1.equals("+")||check1.equals(".")||check1.equals("x")||check1.equals("\u00F7"))&&(check2.equals("+")||check2.equals("x")||check2.equals("\u00F7"))){
//               Raw = Raw.substring(0,i-2)+check2;
//           }
//       }else{
//           Raw="";
//       }
        if(n==1){
            Raw= "";
        }else {
            if(n==2&& Raw.substring(0,1).equals("-")){
                Raw="-";
            }else if(n>2) {
                //if(Raw.substring(n-2,n-1).equals("-")||Raw.substring(n-2,n-1).equals("+")||Raw.substring(n-2,n-1).equals("x")||Raw.substring(n-2,n-1).equals("\u00F7")||Raw.substring(n-2,n-1).equals(".")){

                //}

                if(Raw.substring(n-2,n-1).equals("-")&&(Raw.substring(n-3,n-2).equals("x")||Raw.substring(n-3,n-2).equals("\u00F7"))){
                    Raw = Raw.substring(0,n-3)+check2;

                }else if(Raw.substring(n-2,n-1).equals("-")||Raw.substring(n-2,n-1).equals("+")||Raw.substring(n-2,n-1).equals("x")||Raw.substring(n-2,n-1).equals("\u00F7")||Raw.substring(n-2,n-1).equals(".")){
                    Raw = Raw.substring(0,n-2)+check2;

                }
            }
        }
        return  Raw;
    }
    public  String check_btndot(String Raw){
        if(Raw.equals("-.")){
            Raw = "-0.";
        }
        else {
            int n = Raw.length();
            for(int i = n-1 ; i>1;i--){

                if(Raw.substring(i-1,i).equals("+")||Raw.substring(i-1,i).equals("-")||Raw.substring(i-1,i).equals("\u00F7")||Raw.substring(i-1,i).equals("x"))
                {
                    if(i==n-1){
                        Raw = Raw.substring(0,n-1)+"0.";
                    }
                    i=1;
                }
                if(Raw.substring(i-1,i).equals(".")){
                    Raw = Raw.substring(0,n-1);
                    i=1;
                }

            }

        }

        return  Raw;
    }
    public String check_sub(String Raw){
        int n = Raw.length();
        if(Raw.length()==2&&Raw.substring(0,1).equals("-")) Raw = Raw.substring(0,1);
        if(Raw.length()>2&&(Raw.substring(n-2,n-1).equals("+")||Raw.substring(n-2,n-1).equals("-")||Raw.substring(n-2,n-1).equals("."))){
            Raw = Raw.substring(0,n-2)+ Raw.substring(n-1,n);
        }
        return Raw;
    }
    public String check_zero(String Raw){
        int n = Raw.length();
        String temp1="";
        String temp2="";
        String operator="";
        if(n==2){
            if(Raw.substring(0,1).equals("0")&&Raw.substring(1,2).equals("0")) Raw ="0";
        }else if(n>2) {
            for(int i =n;i>2;i--){
                if(Raw.substring(n-2,n-1).equals("0"))
                { if(Raw.substring(n-3,n-2).equals("+")||Raw.substring(n-3,n-2).equals("-")||Raw.substring(n-3,n-2).equals("x")||Raw.substring(n-3,n-2).equals("\u00F7"))
                    Raw = Raw.substring(0,n-2)+Raw.substring(n-1,n) ;
                    i=2;
                }
            }
        }

        return  Raw;
    }
}
