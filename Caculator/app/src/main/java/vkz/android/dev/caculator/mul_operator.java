package vkz.android.dev.caculator;

/**
 * Created by Administrator on 1/7/2017.
 */
public class mul_operator {
    public sum_operator sum = new sum_operator();
    public String mul_int(String s1, String s2){
        String s="";
        int n1 = s1.length();
        int n2 = s2.length();
        int  total1 =0;
        int total2 =0;
        String st1="";
        String st2 ="";
        String temp1 ="";
        String temp2 ="";
        for(int i=n1;i>0;i--){
            st1="0";
            temp1 ="";
            for(int j=n2;j>0;j--){
                total1 =Integer.parseInt(s1.substring(i-1,i))*Integer.parseInt(s2.substring(j-1,j));
                st1 =sum.Sum_int(st1,String.valueOf(total1)+temp1);
                temp1 = temp1+"0";
            }
            st2 =sum.Sum_int(st2,st1+temp2);
            temp2=temp2+"0";
        }
        s = st2;
        return s;
    }
    public  String mul_decimal(String s1, String s2){
        String s ="";
//        int n1 = s1.length();
//        int n2 = s2.length();
//        int st1=0;
//        int st2=0;
//        for(int i=n1;i>0;i--){
//
//            if(s1.substring(i-1,i).equals(".")){
//                s1 = s1.substring(0,i-1)+ s1.substring(i,s1.length());
//                st1 =n1 -i;
//                i=0;
//            }
//        }
//        for(int i=n2;i>0;i--){
//
//            if(s2.substring(i-1,i).equals(".")){
//
//                s2 = s2.substring(0,i-1)+ s2.substring(i,s2.length());
//                st2 = n2 -i;
//                i=0;
//            }
//        }
//
//        s = mul_int(s1,s2);
//        s = remove_rezo(s);
//        int comma=0;
//        comma = st1+st2;
//          comma = comma - s.length();
//        if(comma==0){
//            s= "0."+s;
//        }else{
//            if(comma<s.length()){
//                if(comma<0) comma = -comma;
//                s= s.substring(0,comma)+"."+s.substring(comma,s.length());
//            }else {
//                for(int i=comma;i>0;i--){
//                    s = "0"+s;
//                }
//                s = "0."+s;
//            }
//        }
//        if(s.substring(s.length()-1,s.length()).equals(".")){
//            s = s.substring(0,s.length()-1);
//        }
        float s1a,s2a;
        s1a = Float.parseFloat(s1);
        s2a = Float.parseFloat(s2);
        s = String.valueOf(s1a*s2a);
        return s;
    }
    public String remove_rezo(String s){
        if(s.length()>0){
            for(int i=0;i<s.length()-1;i++){
                if(s.substring(i,i+1).equals("0")&&(s.substring(i+1,i+2).equals("1")||s.substring(i+1,i+2).equals("2")||s.substring(i+1,i+2).equals("3")||s.substring(i+1,i+2).equals("4")||s.substring(i+1,i+2).equals("5")||
                        s.substring(i+1,i+2).equals("6")||s.substring(i+1,i+2).equals("7")||s.substring(i+1,i+2).equals("8")||s.substring(i+1,i+2).equals("9"))){
                    s = s.substring(i+1,s.length());
                }

            }
        }
        return s;
    }
}
