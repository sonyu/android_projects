package vkz.android.dev.vietcalendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

/**
 * @author Mukesh Y
 */
public class CalendarAdapter extends BaseAdapter {
	private Context mContext;

	private java.util.Calendar month;
	public GregorianCalendar pmonth; // calendar instance for previous month
	/**
	 * calendar instance for previous month for getting complete view
	 */
	public GregorianCalendar pmonthmaxset;
	private GregorianCalendar selectedDate;
	int firstDay;
	int maxWeeknumber;
	int maxP;
	int calMaxP;
	int lastWeekDay;
	int leftDays;
	int mnthlength;
	String itemvalue, curentDateString;
	DateFormat df;

	private ArrayList<String> items;
	public static List<String> dayString;
	//public static List<NgayAD> ngayADList;
	private View previousView;
	private int positions;

	public CalendarAdapter(Context c, GregorianCalendar monthCalendar) {
		CalendarAdapter.dayString = new ArrayList<String>();
		//CalendarAdapter.ngayADList = new ArrayList<NgayAD>();
		Locale.setDefault(Locale.US);
		month = monthCalendar;
		selectedDate = (GregorianCalendar) monthCalendar.clone();
		mContext = c;
		month.set(GregorianCalendar.DAY_OF_MONTH, 1);
		this.items = new ArrayList<String>();
		df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		curentDateString = df.format(selectedDate.getTime());
		refreshDays();
	}

	public void setItems(ArrayList<String> items) {
		for (int i = 0; i != items.size(); i++) {
			if (items.get(i).length() == 1) {
				items.set(i, "0" + items.get(i));
			}
		}
		this.items = items;
	}

	public int getCount() {
		return dayString.size();
	}

	public Object getItem(int position) {
		return dayString.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new view for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		TextView dayView;
		TextView ngayamView;

		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.calendar_item, null);

		}
		dayView = (TextView) v.findViewById(R.id.date);
		ngayamView =(TextView) v.findViewById(R.id.ngayam);


		// separates daystring into parts.
		String s1 =dayString.get(position);
		String[] separatedTime = dayString.get(position).split("-");
		// taking last part of date. ie; 2 from 2012-12-02
		String gridvalue = separatedTime[2].replaceFirst("^0*", "");
		// checking whether the day is in current month or not.
		if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
			// setting offdays to white color.
			dayView.setTextColor(Color.parseColor("#611e26"));
			v.setBackgroundResource(R.drawable.calendar_cellm);
			dayView.setClickable(false);
			dayView.setFocusable(false);
		} else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
			dayView.setTextColor(Color.parseColor("#611e26"));
			dayView.setClickable(false);
			dayView.setFocusable(false);

		} else {
			// setting curent month's days in blue color.
			dayView.setTextColor(Color.BLACK);
		}


		if(dayString.get(position).equals(curentDateString)){
			v.setBackgroundResource(R.drawable.calendar_cel_selected);
 			//previousView = v;
		}else {
			if(position%7==0){
					v.setBackgroundResource(R.drawable.calendar_bg_orange);

				//previousView = v;
			} else {
				if(((Integer.parseInt(gridvalue) < 7) && (position > 28)||((Integer.parseInt(gridvalue) > 1) && (position < firstDay)))){
					v.setBackgroundResource(R.drawable.calendar_cellm);
				}else {

					v.setBackgroundResource(R.drawable.list_item_background);
				}

			}
		}


		dayView.setText(gridvalue);
		ChuyenDoi cd = new ChuyenDoi();

		int  ds =  Integer.parseInt(s1.substring(8,10));
		int ms = Integer.parseInt(s1.substring(5,7));
		int ns = Integer.parseInt(s1.substring(0,4));
		int jd = cd.jdFromDate(ds,ms, ns);
		int []s = cd.jdToDate(jd);
		int  []l = cd.convertSolar2Lunar(s[0],s[1],s[2],7.0);
		if(l[0]==1){
			ngayamView.setText(String.valueOf(l[0])+"/"+String.valueOf(l[1]));
		}else{
			ngayamView.setText(String.valueOf(l[0]));
		}

		// create date string for comparison
		String date = dayString.get(position);

		if(date.length() == 1) {
			date = "0" + date;
		}
		String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
		if (monthStr.length() == 1) {
			monthStr = "0" + monthStr;
		}

		// show icon if date is not empty and it exists in the items array
		ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
		if (date.length() > 0 && items != null && items.contains(date)) {
			iw.setVisibility(View.VISIBLE);
		} else {
			iw.setVisibility(View.INVISIBLE);
		}
		return v;
	}

	public View setSelected(View view,int position) {
		curentDateString = df.format(selectedDate.getTime());

		if (previousView != null) {
			previousView.setBackgroundResource(R.drawable.list_item_background);
			if(dayString.get(positions).equals(curentDateString)) {
				previousView.setBackgroundResource(R.drawable.calendar_cel_selected);
			}else	if((positions>-1) &&positions%7==0){
				previousView.setBackgroundResource(R.drawable.calendar_bg_orange);
			}
		}

		previousView = view;
		positions = position;
		view.setBackgroundResource(R.drawable.calendar_cel_selected);

		return view;
	}

	public void refreshDays() {
		// clear items
		items.clear();
		dayString.clear();
		//ngayADList.clear();
		Locale.setDefault(Locale.US);
		pmonth = (GregorianCalendar) month.clone();
		// month start day. ie; sun, mon, etc
		firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
		// finding number of weeks in current month.
		maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
		// allocating maximum row number for the gridview.
		mnthlength = maxWeeknumber * 7;
		maxP = getMaxP(); // previous month maximum day 31,30....
		calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
		/**
		 * Calendar instance for getting a complete gridview including the three
		 * month's (previous,current,next) dates.
		 */
		pmonthmaxset = (GregorianCalendar) pmonth.clone();
		/**
		 * setting the start date as previous month's required date.
		 */
		pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

		/**
		 * filling calendar gridview.
		 */

		for (int n = 0; n < mnthlength; n++) {
			itemvalue = df.format(pmonthmaxset.getTime());

			pmonthmaxset.add(GregorianCalendar.DATE, 1);
			dayString.add(itemvalue);
		}
	}

	private int getMaxP() {
		int maxP;
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMinimum(GregorianCalendar.MONTH)) {
			pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			pmonth.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}
		maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		return maxP;
	}

}