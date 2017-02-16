package vkz.android.dev.caculator;

/**
 * Created by Administrator on 1/11/2017.
 */
public class stand {
    public  mul_operator mul = new mul_operator();
    public  String stand_string(String s){
        int n = s.length();
        String temp="";
        int st,ss;
        String snew="";
        for(int i = 0; i<n;i++){
            st =0;
            ss =0;
            if(i==0){

                if(s.substring(0,1).equals(".")||s.substring(0,1).equals("+")||s.substring(0,1).equals("x")||s.substring(0,1).equals("/")){
                    s = s.substring(1,n);
                }
            }
            if(s.substring(i-1,i).equals("-")||s.substring(i-1,i).equals("+")||s.substring(i-1,i).equals("x")||s.substring(i-1,i).equals("/"))
            {
                for(int j = 0; j<i-1;j++){
                    temp =temp+s.substring(j,j+1);
                }
                temp = mul.remove_rezo(temp);
                snew =temp+s.substring(i-1,i)+snew;
                temp ="";
            }
        }
        return snew;
    }
}
