package vkz.android.dev.vietcalendar;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import vkz.android.dev.vietcalendar.dataprocess.DatabaseHelper;
import vkz.android.dev.vietcalendar.dataprocess.Daydetail;

public class MainActivity extends AppCompatActivity {
    public GregorianCalendar month, itemmonth;// calendar instances.

    public CalendarAdapter adapter;// adapter instance
    public Handler handler;// for grabbing some event values for showing the dot
    // marker.
    public ArrayList<String> items; // container to store calendar items which
    // needs showing the event marker
    ArrayList<String> event;
    LinearLayout rLayout;
    ArrayList<String> date;
    ArrayList<String> desc;
    private final List<Daydetail> noteList = new ArrayList<Daydetail>();
    private final List<Daydetail> noteListA = new ArrayList<Daydetail>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Locale.setDefault(Locale.CHINESE);
        //String NgayDuong ="table_dg";
        DatabaseHelper db = new DatabaseHelper(this);
        db.createDefaultNote();

        List<Daydetail> list = db.getAllnote();
        noteList.addAll(list);
        db.createDefaultNoteA();
        List<Daydetail> listA = db.getAllnoteA();
        noteListA.addAll(listA);

        rLayout = (LinearLayout) findViewById(R.id.text);
        month = (GregorianCalendar) GregorianCalendar.getInstance();
        itemmonth = (GregorianCalendar) month.clone();

        items = new ArrayList<String>();

        adapter = new CalendarAdapter(this, month);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(adapter);

        //handler = new Handler();
       //handler.post(calendarUpdater);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
        TextView ngayduong = (TextView)findViewById(R.id.ngayDuong);
        String dl = (String) android.text.format.DateFormat.format("dd-MM",GregorianCalendar.getInstance().getTime());

        TextView ngayam = (TextView)findViewById(R.id.ngayAm) ;
        NgayAD ngayad = new NgayAD((String) android.text.format.DateFormat.format("yyyy-MM-dd",GregorianCalendar.getInstance().getTime()));
        ngayduong.setText(ngayad.converD());
        //ngayduong.setText(dl+": DL");
        ngayam.setText(ngayad.converA());
        TextView event = (TextView)findViewById(R.id.txt_event);
        String event_string = checkDay(ngayad.Duong);
        event.setText(event_string);
        RelativeLayout previous = (RelativeLayout) findViewById(R.id.previous);

        previous.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setPreviousMonth();
                refreshCalendar();
            }
        });

        RelativeLayout next = (RelativeLayout) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setNextMonth();
                refreshCalendar();

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // removing the previous view if added
                if (((LinearLayout) rLayout).getChildCount() > 0) {
                    ((LinearLayout) rLayout).removeAllViews();
                }
                desc = new ArrayList<String>();
                date = new ArrayList<String>();
                ((CalendarAdapter) parent.getAdapter()).setSelected(v, position);
                String selectedGridDate = CalendarAdapter.dayString
                        .get(position);
                String[] separatedTime = selectedGridDate.split("-");
                String gridvalueString = separatedTime[2].replaceFirst("^0*",
                        "");// taking last part of date. ie; 2 from 2012-12-02.
                int gridvalue = Integer.parseInt(gridvalueString);
                // navigate to next or previous month on clicking offdays.
                if ((gridvalue > 10) && (position < 8)) {
                    setPreviousMonth();
                    refreshCalendar();
                } else if ((gridvalue < 7) && (position > 28)) {
                    setNextMonth();
                    refreshCalendar();
                }
                ((CalendarAdapter) parent.getAdapter()).setSelected(v,position);

                for (int i = 0; i < Utility.startDates.size(); i++) {
                    if (Utility.startDates.get(i).equals(selectedGridDate)) {
                        desc.add(Utility.nameOfEvent.get(i));
                    }
                }

                if (desc.size() > 0) {
                    for (int i = 0; i < desc.size(); i++) {
                        TextView rowTextView = new TextView(MainActivity.this);

                        // set some properties of rowTextView or something
                        rowTextView.setText("Event:" + desc.get(i));
                        rowTextView.setTextColor(Color.BLACK);

                        // add the textview to the linearlayout
                        rLayout.addView(rowTextView);

                    }

                }

                desc = null;

            }

        });
    }
    public String checkDay(String ngay){
       String event="";
        String eventD="";
        String eventA="";
        ChuyenDoi cd = new ChuyenDoi();
        int  ds =  Integer.parseInt(ngay.substring(8,10));
        int ms = Integer.parseInt(ngay.substring(5,7));
        int ns = Integer.parseInt(ngay.substring(0,4));
        int jd = cd.jdFromDate(ds,ms, ns);
        int []s = cd.jdToDate(jd);
        int  []l = cd.convertSolar2Lunar(s[0],s[1],s[2],7.0);
        if(noteList.size()>0){

            for (int i = 0; i < this.noteList.size(); i++) {
                String sd =this.noteList.get(i).getDay();
               if(sd != null){
                   if(sd.substring(0,5).equals(ngay.substring(5,10))) {
                       eventD =this.noteList.get(i).getDes();
                       i=this.noteList.size();
                   }
               }else {
                   eventD="";
               }
             }

         }
        if(noteListA.size()>0){
            for (int i = 0; i < this.noteListA.size(); i++) {
                String sa =this.noteListA.get(i).getDay();
                if(sa != null){
                    if(Integer.parseInt(sa.substring(3,5))==l[0]&&Integer.parseInt(sa.substring(0,2))==l[1]) {
                        eventA =this.noteListA.get(i).getDes();
                        i=this.noteListA.size();
                    }
                }else {
                    eventA="";
                }
            }

        }
         if(eventD.equals("")) {
                if(eventA.equals("")){
                    event="";
                }else{
                    event = "Ngày "+eventA;
                }
            }else{
                if(eventA.equals("")){
                    event="Ngày "+eventD;
                }else{
                    event = "Ngày "+eventD+" ,"+eventA;
                }
            }

        return event;
    }
    protected void setNextMonth() {
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMaximum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) + 1),
                    month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) + 1);
        }

    }

    protected void setPreviousMonth() {
        if (month.get(GregorianCalendar.MONTH) == month
                .getActualMinimum(GregorianCalendar.MONTH)) {
            month.set((month.get(GregorianCalendar.YEAR) - 1),
                    month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            month.set(GregorianCalendar.MONTH,
                    month.get(GregorianCalendar.MONTH) - 1);
        }

    }

    protected void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();

    }

    public void refreshCalendar() {
        TextView title = (TextView) findViewById(R.id.title);

        adapter.refreshDays();
        adapter.notifyDataSetChanged();
        //handler.post(calendarUpdater); // generate some calendar items

        title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
    }

    public Runnable calendarUpdater = new Runnable() {

        @Override
        public void run() {
            items.clear();

            // Print dates of the current week
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            String itemvalue;
            event = Utility.readCalendarEvent(MainActivity.this);
            Log.d("=====Event====", event.toString());
            Log.d("=====Date ARRAY====", Utility.startDates.toString());

            for (int i = 0; i < Utility.startDates.size(); i++) {
                itemvalue = df.format(itemmonth.getTime());
                itemmonth.add(GregorianCalendar.DATE, 1);
                items.add(Utility.startDates.get(i).toString());
            }
            adapter.setItems(items);
            adapter.notifyDataSetChanged();
        }
    };
}