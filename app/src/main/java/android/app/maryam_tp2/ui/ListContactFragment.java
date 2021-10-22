package android.app.maryam_tp2.ui;

import android.app.maryam_tp2.MySQLiteHelper;
import android.app.maryam_tp2.R;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;


public class ListContactFragment extends Fragment {


    ListView list;
    MySQLiteHelper db;
    String nom;
    Cursor cursor;
    public ListContactFragment() {
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
        View view=inflater.inflate(R.layout.fragment_list_contact, container, false);
        list=view.findViewById(R.id.list);
       db=new MySQLiteHelper(view.getContext());
        SimpleCursorAdapter simpleCursorAdapter= db.populateListViewFromDB(view.getContext());
        list.setAdapter(simpleCursorAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor= (Cursor) simpleCursorAdapter.getItem(position);
                nom=cursor.getString(1);
                Toast.makeText(view.getContext(),nom,Toast.LENGTH_LONG).show();

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Choisir l'opperation :");
                String[] option = {"Modifier", "Supprimer"};

                int checkedItem = 0;
                builder.setSingleChoiceItems(option, checkedItem, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {

                    } });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {

                        Bundle b1=new Bundle();
                        Bundle b2=new Bundle();
                        b1.putString("nom", String.valueOf(R.id.item_nom));
                        b2.putString("tele",String.valueOf(R.id.item_number));
                        if(which==0){
                            Fragment mf=new mdifierFragment();
                            mf.setArguments(b1);
                            mf.setArguments(b2);
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frag_list, mf)
                                    .commit();
                        } else{
                           Fragment Df=new DeleteFragment();
                            Df.setArguments(b1);
                            getChildFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.frag_list, Df)
                                    .commit();
                        } } });
                builder.setNegativeButton("Cancel", null);
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
        return view;
    }
}