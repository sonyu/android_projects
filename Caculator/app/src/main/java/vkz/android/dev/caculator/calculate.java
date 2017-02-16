package vkz.android.dev.caculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 1/4/2017.
 */
public class calculate {
    private String Result;
    private String Temp1,Temp2;
    private String Operator;
    public  sum_operator sum = new sum_operator();
    public  sub_operator sub = new sub_operator();
    public  mul_operator mul = new mul_operator();
    public div_operator divo = new div_operator();
    public  Reformatdata refor = new Reformatdata();
    public String calculate(String Result){
        int n = Result.length();
        for(int i =1; i<n;i++){
            if(Result.substring(i-1,i).equals("x")||Result.substring(i-1,i).equals("\u00F7")||Result.substring(i-1,i).equals("+")||Result.substring(i-1,i).equals("-")){
                Temp1 = Result.substring(0,i-1);
                Temp2 = Result.substring(i,n);
                Operator = Result.substring(i-1,i);
                i=n;
            }
        }
        switch (Operator){
            case "+":
                Result = sum.Operator_sum(Temp1,Temp2);
                break;
            case "-":
                Result = sub.sub_operator(Temp1,Temp2);
                break;
            case "x":
                Result = mul.mul_decimal(Temp1,Temp2);
                break;
            case "\u00F7":
                Result = divo.div_int(Temp1,Temp2);
                break;
            default:
                break;
        }
        //Result = String.valueOf(Integer.parseInt(Temp1)+Integer.parseInt(Temp2));
        n=0;
        return  Result;
    }
    public String gencalculate(String s){
        int n=s.length();
        String s1 ="";
        String s2 ="";
        String s12 =s;
        String dau ="";
        int dem =n;
        int total = 0;
        List<operator_string> opst = new ArrayList<operator_string>();
        if(s.substring(0,1).equals("-")){
            dau = "-";
            s = s.substring(1,s.length());
            n--;
        }
        for(int i =1;i<=n;i++){
            s1=s1+s.substring(i-1,i);
            if(i==1){
                s1 =dau+s1;
            }
            if(s.substring(i-1,i).equals("+")||s.substring(i-1,i).equals("-")||s.substring(i-1,i).equals("x")||s.substring(i-1,i).equals("\u00F7")){
                total++;
                s1 = s1.substring(0,s1.length()-1);
                opst.add( new operator_string(s.substring(i-1,i),s1));
                s1="";
            }
            if(i==n){
                opst.add( new operator_string("",s1));
            }
        }
        while (total>0){
            int ns=opst.size();
            for(int j=0;j<ns;j++){
                for (int i=0; i<ns;i++){
                    if(opst.get(i).getOperator().equals("x")||opst.get(i).getOperator().equals("\u00F7")){
                        total--;

                        if(opst.get(i).getOperator().equals("x")){
                            opst.get(i).setS(mul.mul_decimal(opst.get(i).getS(),opst.get(i+1).getS()));
                        }else{
                            opst.get(i).setS(divo.div_int(opst.get(i).getS(),opst.get(i+1).getS()));
                        }
                        opst.get(i).setOperator(opst.get(i+1).getOperator());
                        opst.remove(i+1);
                        i=ns;
                        ns --;
                    }
                }
            }

            for(int j=0;j<ns;j++){
                for (int i=0; i<ns;i++){
                    if(opst.get(i).getOperator().equals("+")||opst.get(i).getOperator().equals("-")){
                        total--;

                        if(opst.get(i).getOperator().equals("+")){
                            opst.get(i).setS(sum.Operator_sum(opst.get(i).getS(),opst.get(i+1).getS()));
                        }else {
                            opst.get(i).setS(sub.sub_operator(opst.get(i).getS(),opst.get(i+1).getS()));
                        }
                        opst.get(i).setOperator(opst.get(i+1).getOperator());
                        opst.remove(i+1);
                        i=ns;
                        ns --;
                    }
                }
            }

        }
        //s  =opst.toString();
        //s = String.valueOf(total);
        s = opst.get(0).getS();
        return  s;
    }
}
