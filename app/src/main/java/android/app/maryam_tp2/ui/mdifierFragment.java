package android.app.maryam_tp2.ui;

import android.app.maryam_tp2.Contact;
import android.app.maryam_tp2.MySQLiteHelper;
import android.app.maryam_tp2.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class mdifierFragment extends Fragment {


    EditText nom ,tele ;
    Button bt1;
    MySQLiteHelper H;
    public mdifierFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
    Boolean checkinsertdata;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mdifier, container, false);
        nom=view.findViewById(R.id.nom);
        tele=view.findViewById(R.id.editTextPhone2);

        Bundle b1=this.getArguments();
        Bundle b2=this.getArguments();
        if(b1!=null && b2!=null) {
            nom.setText(b1.getString("nom"));
            tele.setText(b2.getString("tele"));
        }
        bt1 =(Button)view.findViewById( R.id.nav_modifier);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom = view.findViewById(R.id.nom);
                tele = view.findViewById(R.id.editTextPhone2);

                Contact ct = new Contact(nom.getText().toString(), tele.getText().toString());
                H=new MySQLiteHelper(v.getContext());
                checkinsertdata =H.updateContact(ct);
                if(checkinsertdata)
                    Toast.makeText(getContext(), "Contact modifier",
                            Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(getContext(),
                            "Ce contact n'existe pas !!!!", Toast.LENGTH_SHORT).show();
                }

            }        });
        return view;
}}