package vkz.android.dev.caculator;

/**
 * Created by Administrator on 1/6/2017.
 */
public class sum_operator {
    public String Operator_sum(String s1, String s2){
        String s="";
//        int n1= s1.length();
//        int n2= s2.length();
//        String s1a="";
//        String s1b="";
//        String s2a="";
//        String s2b="";
//        String sf="";
//        String sb="";
//        for(int i=n1;i>1;i--){
//            if(s1.substring(i-1,i).equals(".")){
//                s1a =s1.substring(0,i-1);
//                s1b = s1.substring(i,n1);
//                i=1;
//            }
//        }
//        for(int i=n2;i>1;i--){
//            if(s2.substring(i-1,i).equals(".")){
//                s2a =s2.substring(0,i-1);
//                s2b = s2.substring(i,n2);
//                i=1;
//            }
//        }
//        if(s1a.length()>0&&s2a.length()>0){
//            sf =Sum_int(s1a,s2a);
//            sb =sum_decimal(s1b,s2b);
//            String temp ="";
//            int max = ((s1b.length()>s2b.length())?s1b.length():s2b.length());
//            if(sb.length()-max>0){
//                temp =sb.substring(0,1);
//                sb = sb.substring(1,sb.length());
//                s = Sum_int(sf,temp)+"."+sb;
//            }else{
//                s = sf+"."+sb;
//            }
//
//        }else if(s1a.length()==0&&s2a.length()>0){
//            sf = Sum_int(s1,s2a);
//            sb = s2b;
//            s = sf +"."+sb;
//        }else if(s1a.length()>0&&s2a.length()==0){
//            sf = Sum_int(s1a,s2);
//            sb = s1b;
//            s = sf +"."+sb;
//        }else {
//            s = Sum_int(s1,s2);
//        }
//
//        return s;
//    }
//    public String sum_decimal(String x, String y){
//        String Sum_dec="";
//        int n1 = x.length();
//        int n2 = y.length();
//        String tail ="";
//        String header ="";
//        if(n1>n2){
//           for(int i=n1 ; i>n1-n2;i--){
//               y =y+"0";
//           }
//            Sum_dec = Sum_string(x,y);
//
//        }else if(n2>n1){
//            for(int i=n2 ; i>n2-n1;i--){
//                x =x+"0";
//            }
//            Sum_dec = Sum_string(y,x);
//        }else {
//            Sum_dec = Sum_string(y,x);
//        }
        float s1a,s2a;
        s1a = Float.parseFloat(s1);
        s2a = Float.parseFloat(s2);
        s = String.valueOf(s1a-s2a);
        return  String.valueOf(s1a+s2a);
    }
    public  String Sum_string(String s1, String s2){
        String sum_s ="";
        int n = s1.length();
        int temp =0;
        int remember =0;
        for(int i=n;i>0;i--){
            int s = Integer.parseInt(s1.substring(i-1,i))+Integer.parseInt(s2.substring(i-1,i))+remember;
            if(s>9){
                temp =s-10;
                remember =s/10;
                sum_s =String.valueOf(temp)+sum_s;
            }else {
                temp =0;
                remember=0;
                sum_s =String.valueOf(s)+sum_s;
            }
            if(i==1&& remember>0){
                sum_s = String.valueOf(remember)+sum_s;
            }


        }
        return  sum_s;
    }
    public String Sum_int(String s1, String s2){
        String Sum_i="";
        int n1 = s1.length();
        int n2 = s2.length();
        //String tail ="";
        //String header ="";
        String temp ="";
        String result ="";
        // int Sum_i_header;
        if(n1>n2){
            for(int i =0; i<n1-n2;i++){
                temp = temp+"0";
            }
            s2 = temp+s2;
           result =Sum_string(s1,s2);
        }else if(n1<n2){
            for(int i =0; i<n2-n1;i++){
                temp = temp+"0";
            }
            s1 = temp+s1;
            result =Sum_string(s2,s1);
        }else{
            result =Sum_string(s2,s1);
        }


        return result;
    }
}
