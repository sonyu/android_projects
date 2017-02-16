package vkz.android.dev.showcontact;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button btnshowallcontact;
    Button btnaccesscalllog;
    Button btnaccessmediastore;
    Button btnaccessbookmarks;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnshowallcontact = (Button) findViewById(R.id.btnshowallcontact);
        btnshowallcontact.setOnClickListener(this);
        btnaccesscalllog = (Button) findViewById(R.id.btnaccesscalllog);
        btnaccesscalllog.setOnClickListener(this);
        btnaccessmediastore = (Button) findViewById(R.id.btnmediastore);
        btnaccessmediastore.setOnClickListener(this);
        btnaccessbookmarks = (Button) findViewById(R.id.btnaccessbookmarks);
        btnaccessbookmarks.setOnClickListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v == btnshowallcontact) {
            intent = new Intent(this, ShowAllContactActivity.class);
            startActivity(intent);
        } else if (v == btnaccesscalllog) {
            accessTheCallLog();
        } else if (v == btnaccessmediastore) {
            accessMediaStore();
        } else if (v == btnaccessbookmarks) {
          //  accessBookmarks();
        }
    }

    /**
     * hàm lấy danh sách lịch sử cuộc gọi
     * với thời gian nhỏ hơn 30 giây và sắp xếp theo ngày gọi
     */
    public void accessTheCallLog() {
        String[] projection = new String[]{
                Calls.DATE,
                Calls.NUMBER,
                Calls.DURATION
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor c = getContentResolver().query(
                Calls.CONTENT_URI,
                projection,
                Calls.DURATION + "<?", new String[]{"30"},
                Calls.DATE + " Asc");
        c.moveToFirst();
        String s = "";
        while (c.isAfterLast() == false) {
            for (int i = 0; i < c.getColumnCount(); i++) {
                s += c.getString(i) + " - ";
            }
            s += "\n";
            c.moveToNext();
        }
        c.close();
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    /**
     * hàm đọc danh sách các Media trong SD CARD
     */
    public void accessMediaStore() {
        String[] projection = {
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATE_ADDED,
                MediaStore.MediaColumns.MIME_TYPE
        };
        CursorLoader loader = new CursorLoader
                (this, Media.EXTERNAL_CONTENT_URI,
                        projection, null, null, null);
        Cursor c = loader.loadInBackground();
        c.moveToFirst();
        String s = "";
        while (!c.isAfterLast()) {
            for (int i = 0; i < c.getColumnCount(); i++) {
                s += c.getString(i) + " - ";
            }
            s += "\n";
            c.moveToNext();
        }
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
        c.close();
    }

    /**
     * hàm đọc danh sách Bookmark trong trình duyệt
     */
    /*public void accessBookmarks() {

        String[] projection = {
                Browser.BookmarkColumns.TITLE,
                Browser.BookmarkColumns.URL,
        };
        Cursor c = getContentResolver()
                .query(Browser.BOOKMARKS_URI, projection,
                        null, null, null);
        c.moveToFirst();
        String s = "";
        int titleIndex = c.getColumnIndex
                (Browser.BookmarkColumns.TITLE);
        int urlIndex = c.getColumnIndex
                (Browser.BookmarkColumns.URL);
        while (!c.isAfterLast()) {
            s += c.getString(titleIndex) + " - " +
                    c.getString(urlIndex);
            c.moveToNext();
        }
        c.close();
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
*/
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://vkz.android.dev.showcontact/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://vkz.android.dev.showcontact/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
