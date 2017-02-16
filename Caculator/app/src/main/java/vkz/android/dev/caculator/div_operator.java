package vkz.android.dev.caculator;

/**
 * Created by Administrator on 1/11/2017.
 */
public class div_operator {
   // public mul_operator mul = new mul_operator();
    public String div_int(String s1, String s2){
        String s ="";
        String result ="";
        String dau ="";
        if(s1.substring(0,1).equals("-")){
            dau ="-";
            s1 = s1.substring(1,s1.length());
        }
        int dem =0;
        float s1a = Float.parseFloat(s1);
        float s2a = Float.parseFloat(s2);
        if(s2a==0){
            s ="Division by zero !";
        }else{
            result =String.valueOf(s1a/s2a);
            s = result;
           // mul.remove_rezo(s);
        }

        return dau+s;
    }
}
