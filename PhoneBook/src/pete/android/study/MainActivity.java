package pete.android.study;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {
    
	private ListView lvPhone;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        lvPhone = (ListView)findViewById(R.id.listPhone);
        
        List<PhoneBook> listPhoneBook = new ArrayList<PhoneBook>();
        listPhoneBook.add(new PhoneBook(
        		BitmapFactory.decodeResource(getResources(), R.drawable.avatar_pete), 
        		"Pete Houston", "010-9817-6331", "pete.houston.17187@gmail.com"));
        listPhoneBook.add(new PhoneBook(
        		BitmapFactory.decodeResource(getResources(), R.drawable.avatar_lina),
        		"Lina Cheng", "046-7764-1142", "lina.cheng011@sunny.com"));
        listPhoneBook.add(new PhoneBook( 
        		BitmapFactory.decodeResource(getResources(), R.drawable.avatar_jenny),
        		"Jenny Nguyen", "0913-223-498", "jenny_in_love98@yahoo.com"));
        PhoneBookAdapter adapter = new PhoneBookAdapter(this, listPhoneBook);
        lvPhone.setAdapter(adapter);
    }
}