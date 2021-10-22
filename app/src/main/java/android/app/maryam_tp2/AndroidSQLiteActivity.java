package android.app.maryam_tp2;

import android.app.Activity;
import android.os.Bundle;

public class AndroidSQLiteActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySQLiteHelper db = new MySQLiteHelper(this);
/**
 * CRUD Operations
 * */
// Inserting Contacts


    }
}
