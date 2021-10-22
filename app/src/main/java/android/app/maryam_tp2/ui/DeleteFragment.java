package android.app.maryam_tp2.ui;

import android.app.AlertDialog;
import android.app.maryam_tp2.MySQLiteHelper;
import android.app.maryam_tp2.R;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class DeleteFragment extends Fragment {

    Button bt1 ,btn2;
    MySQLiteHelper H;
    EditText nom  ;


    public DeleteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        nom=view.findViewById(R.id.nom);
        Bundle b1=this.getArguments();
        if(b1!=null) {
            nom.setText(b1.getString("nom"));
        }
        bt1 =(Button)view.findViewById( R.id.nav_delete);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nom = view.findViewById(R.id.nom);
                if(nom.getText().toString()!=null) {
                    H = new MySQLiteHelper(v.getContext());
                    Boolean checkinsertdata = H.deleteContact(
                            nom.getText().toString());
                    if (checkinsertdata == true)
                        Toast.makeText(getContext(), "Contact supprimer",
                                Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(getContext(), "Ce Contact n'existe pas",
                                Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(getContext(), "Veuillez saisir un contact !!!!",
                        Toast.LENGTH_SHORT).show();}
                 });
        btn2 =(Button)view.findViewById( R.id.nav_delete2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        H=new MySQLiteHelper(view.getContext());
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                H.clearContact();

                                 Toast.makeText(getContext(), "Tous les Contacts sont supprimer !!!!", Toast.LENGTH_SHORT).show();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                Toast.makeText(getContext(), "Aucun contact n'a été supprimer !!!!", Toast.LENGTH_SHORT).show();

                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Etes vous sur d'inisialiser la liste des contacts???").setPositiveButton("Oui", dialogClickListener)
                        .setNegativeButton("Non", dialogClickListener).show();
            }        });
        return view;
    }
}