package android.app.maryam_tp2;

import android.app.maryam_tp2.ui.AjouterFragment;
import android.app.maryam_tp2.ui.ListContactFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
ListContactFragment list=new ListContactFragment();
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ////////////////////////////////////////////////////
        MySQLiteHelper db=new MySQLiteHelper(this);
        Log.d("JMF", "Insertion de Contact");
        Boolean result1=db.addContact(new Contact("Jo", "9100000000"));
        if(result1==false)  Log.d("JMF", "impossible d'ajouter");
        else  Log.d("JMF", "Contact1 ajouter");
        db.addContact(new Contact("Jack", "9199999999"));
        db.addContact(new Contact("William", "9522222222"));
        db.addContact(new Contact("Averel", "9533333333"));
// Reading all contacts
        Log.d("JMF", "Lecture des Contacts");
        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {
            String log ="id:"+cn.get_id()+"Name: " + cn.getNom() + " ,Phone: "
                    + cn.getNumTelephone();
// Writing Contacts to log
            Log.d("JMF", log);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Ajouter nouveau contact", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Fragment Af=new AjouterFragment();

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frag_content, Af)
                        .commit();


            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_Contact, R.id.nav_ajouter,R.id.nav_modifier,R.id.nav_delete)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    ////////////////////////////////////

}