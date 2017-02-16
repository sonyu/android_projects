package vkz.android.dev.caculator;

/**
 * Created by Administrator on 1/7/2017.
 */
public class sub_operator {
    public String sub_string(String s1, String s2) {
        String s = "";
        int n = s1.length();
        int temp = 0;
        //int remember = 0;
        int total1,total2,total;
        int st1 =Integer.parseInt(s1);
        int st2 = Integer.parseInt(s2);
        if(st1>st2){
          for(int i =s1.length();i>0;i--){
              total1 = Integer.parseInt(s1.substring(i-1,i));
              total2 = Integer.parseInt(s2.substring(i-1,i));
              total = total1 -total2 +temp;
              if(total<0){
                  s = String.valueOf(10+total)+s;
                  temp = -1;
              }else{
                  temp = 0;
                  s = String.valueOf(total)+s;

              }
          }
        }else if(st1<st2) {
            for (int i = s2.length(); i > 0; i--) {
                total1 = Integer.parseInt(s2.substring(i - 1, i));
                total2 = Integer.parseInt(s1.substring(i - 1, i));
                total = total1 - total2 + temp;
                if (total < 0) {
                    s = String.valueOf(10 + total) + s;
                    temp = -1;
                } else {
                    temp = 0;
                    s = String.valueOf(total) + s;

                }
            }

            s ="-"+s;
        }else{
            s ="0";
        }
        return s;
    }
    public String sub_int(String s1, String s2){
        String s = "";
        if(s1.length()> s2.length()){
            for(int i = (s1.length()- s2.length());i>0;i--){
                s2 = "0"+s2;
            }
        }else if (s1.length()< s2.length()){
            int test = s2.length()- s1.length();
            for(int i =(s2.length()- s1.length());i>0;i--){
                s1 = "0"+s1;
            }
        }
        s = sub_string(s1,s2);
        return  s;
    }
    public String sub_decimal(String s1, String s2){
        String s="";
        if(s1.length()> s2.length()){
            for(int i = (s1.length()- s2.length());i>0;i--){
                s2 = s2+"0";
            }
        }else if (s1.length()< s2.length()){
            int test = s2.length()- s1.length();
            for(int i =(s2.length()- s1.length());i>0;i--){
                s1 = s1+"0";
            }
        }
        s = sub_string(s1,s2);
        return s;
    }
    public String sub_operator(String s1, String s2){
          String s="";
//        String s1a="";
//        String s1b="";
//        String s2a="";
//        String s2b="";
//        String sf="";
//        String sb="";
//        int n1= s1.length();
//        int n2= s2.length();
//        s1a=s1;
//        s1b="0";
//        for(int i=0;i<n1;i++){
//            if(s1.substring(i,i+1).equals(".")){
//                s1a =s1.substring(0,i);
//                s1b = s1.substring(i+1,n1);
//                i=n1;
//            }
//        }
//        s2a=s2;
//        s2b="0";
//        for(int i=0;i<n2;i++){
//            if(s2.substring(i,i+1).equals(".")){
//                s2a =s2.substring(0,i);
//                s2b = s2.substring(i+1,n2);
//                i=n2;
//            }
//        }
//        if(Integer.parseInt(s1a)>Integer.parseInt(s2a)){
//            sf = sub_int(s1a,s2a);
//            sb = sub_decimal(s1b,s2b);
//            if(sb.substring(0,1).equals("-")){
//                String temp="1";
//                for(int i=0;i<sb.length()-1;i++){
//                    temp = temp+"0";
//                }
//                sb =sub_int(temp,sb.substring(1,sb.length()));
//                sb = sb.substring(1,sb.length());
//                sf =sub_int(sf,"1");
//            }
//        }else if (Integer.parseInt(s1a)<Integer.parseInt(s2a)){
//            sf = sub_int(s2a,s1a);
//            sb = sub_decimal(s2b,s1b);
//            if(sb.substring(0,1).equals("-")){
//                String temp="1";
//                for(int i=0;i<sb.length()-1;i++){
//                    temp = temp+"0";
//                }
//                sb =sub_int(temp,sb.substring(1,sb.length()));
//                sb = sb.substring(1,sb.length());
//                sf = sub_int(sf,"1");
//            }
//            sf ="-"+sf;
//        }else  if (Integer.parseInt(s1a)==Integer.parseInt(s2a)){
//            sf ="0";
//            sb = sub_decimal(s1b,s2b);
//            if(sb.substring(0,1).equals("-")){
//             sb = sb.substring(1,sb.length());
//                sf= "-"+sf;
//            }
//        }
//        s = sf+"."+sb;
        float s1a,s2a;
        s1a = Float.parseFloat(s1);
        s2a = Float.parseFloat(s2);
        s = String.valueOf(s1a-s2a);
        return  s;
    }
}
