package android.app.maryam_tp2.ui;
import android.app.maryam_tp2.Contact;
import android.app.maryam_tp2.MySQLiteHelper;
import android.app.maryam_tp2.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;


public class AjouterFragment extends Fragment  {
    EditText nom ,tele ;
    Button bt1;
    MySQLiteHelper H;
    Contact ct;
    public AjouterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ajouter, container, false);
        bt1 =(Button)view.findViewById( R.id.Ajouter);
        bt1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               nom = view.findViewById(R.id.nom);
               tele = view.findViewById(R.id.editTextPhone2);
               ct = new Contact( nom.getText().toString(), tele.getText().toString());
                H=new MySQLiteHelper(view.getContext());
                Boolean checkinsertdata =H.addContact(ct);
                if(checkinsertdata==true){
                    Log.d("JMF", "Lecture des Contacts");
                    List<Contact> contacts = H.getAllContacts();
                    for (Contact cn : contacts) {
                        String log = "Id: "+cn.get_id()+" ,Name: " + cn.getNom() + " ,Phone: "
                                + cn.getNumTelephone();
// Writing Contacts to log
                        Log.d("JMF", log);}
                    Toast.makeText(getContext(), "Contact ajouter", Toast.LENGTH_SHORT).show();
                    Log.d("JMF", "Contact ajouter");}
                else {
                    Toast.makeText(getContext(), "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                    Log.d("JMF", "Contact non ajouter");
                } }});
        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }



            }
        }





