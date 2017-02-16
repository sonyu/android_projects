package vkz.android.dev.caculator;

/**
 * Created by Administrator on 1/12/2017.
 */
public class operator_string {
    String operator;
    String s;

    public operator_string(String operator,String s){
        this.operator = operator;
        this.s = s;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return this.s;
    }
}
