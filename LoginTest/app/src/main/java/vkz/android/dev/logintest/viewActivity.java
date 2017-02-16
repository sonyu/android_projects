package vkz.android.dev.logintest;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import vkz.android.dev.logintest.bean.note;

public class viewActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<note> listViewAdapter;
    private final List<note> noteList = new ArrayList<note>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        listView = (ListView) findViewById(R.id.listView);

        Intent intent = getIntent();

        Bundle extra = getIntent().getBundleExtra("extra");
        List<note> list = (ArrayList<note>) extra.getSerializable("objects");
        this.noteList.addAll(list);
//        ArrayList<String> test = new ArrayList<String>() ;
//        test.add("a");
//        test.add("b");
//        test.add("c");
//        test.add("d");
//        test.add("e");

       // this.listViewAdapter = new ArrayAdapter<test>(this,android.R.layout.simple_expandable_list_item_1,android.R.id.text1,test);
        //listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test));
        listView.setAdapter(new ArrayAdapter<note>(this,android.R.layout.simple_list_item_1, android.R.id.text1, this.noteList));
        //this.listViewAdapter = new ArrayAdapter<note>(this,
        //android.R.layout.simple_list_item_1, android.R.id.text1, (List<note>)this.noteList);

        // Đăng ký Adapter cho ListView.
       // listView.setAdapter( new ArrayAdapter<note>(this,
        //        android.R.layout.simple_list_item_1, android.R.id.text1, this.noteList));
        // Đăng ký Context menu cho ListView.

//        ArrayList<note> results = new ArrayList<note>();
//        if(this.noteList.size()>0) {
//            for (int i = 0; i < this.noteList.size(); i++) {
//                note  nte = new note();
//                nte.setLogin_username("test");
//                nte.setLogin_password("done");
//                results.add(nte);
//               // Toast.makeText(getApplicationContext(),"test done ! user ="+this.noteList.get(i).getLogin_username()
//               //         , Toast.LENGTH_LONG).show();
//            }
//            //listView.setAdapter( new ArrayAdapter<note>(this,android.R.layout.simple_list_item_1, android.R.id.text1,results));
//            this.listViewAdapter = new ArrayAdapter<note>(this,android.R.layout.simple_expandable_list_item_1,android.R.id.text1,results);
//            this.listView.setAdapter(this.listViewAdapter);
//        }
//        registerForContextMenu(listView);
    }


}
