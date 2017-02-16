package vkz.android.dev.vietcalendar;

/**
 * Created by Administrator on 2/3/2017.
 */
public class NgayAD {
    //String Am;
    String Duong;
    NgayAD(String Duong){
       // this.Am = Am;
        this.Duong= Duong;
    }
    public String converA(){
       // am =  Am;
        //String thang = am.substring(5,7);
        ChuyenDoi cd = new ChuyenDoi();
        int thang;
        int  ds =  Integer.parseInt(Duong.substring(8,10));
        int ms = Integer.parseInt(Duong.substring(5,7));
        int ns = Integer.parseInt(Duong.substring(0,4));
        int jd = cd.jdFromDate(ds,ms, ns);
        int []s = cd.jdToDate(jd);
        int  []l = cd.convertSolar2Lunar(s[0],s[1],s[2],7.0);
        thang = l[1];
        String thgam="";
        switch(thang){
            case 1:
                thgam ="riêng";
                break;
            case 2:
                thgam ="hai";
                break;
            case 3:
                thgam ="ba";
                break;
            case 4:
                thgam ="bốn";
                break;
            case 5:
                thgam ="năm";
                break;
            case 6:
                thgam ="sáu";
                break;
            case 7:
                thgam ="bảy";
                break;
            case 8:
                thgam ="tám";
                break;
            case 9:
                thgam ="chín";
                break;
            case 10:
                thgam ="mười";
                break;
            case 11:
                thgam ="mười một";
                break;
            case 12:
                thgam ="chạp";
                break;
            default:
        }
        return  l[0]+" tháng "+ thgam+" Â.L";
    }
    public String converD(){
        int ms = Integer.parseInt(Duong.substring(5,7));
        int  ds =  Integer.parseInt(Duong.substring(8,10));
        int ns = Integer.parseInt(Duong.substring(0,4));
        return ds+"."+" thg "+ms;
    }
}
