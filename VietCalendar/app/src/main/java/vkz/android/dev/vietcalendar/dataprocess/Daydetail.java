package vkz.android.dev.vietcalendar.dataprocess;

/**
 * Created by Administrator on 2/7/2017.
 */
public class Daydetail {
    int Id;
    String Day;
    String Des;
    public Daydetail(){

    }
    public Daydetail(String Day, String Des){
        this.Day = Day;
        this.Des = Des;
    }
    public Daydetail( int Id,String Day, String Des ){
        this.Id = Id;
        this.Day = Day;
        this.Des = Des;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
