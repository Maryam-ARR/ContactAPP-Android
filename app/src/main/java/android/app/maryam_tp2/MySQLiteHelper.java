package android.app.maryam_tp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.cursoradapter.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteHelper  extends SQLiteOpenHelper {

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOM = "nom";
    public static final String COLUMN_NUM = "numTelephone";

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;
    Context context1;

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE =
            "CREATE TABLE contacts  (_id INTEGER PRIMARY KEY," +
                    " nom TEXT, numTelephone TEXT )";


    public MySQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null,DATABASE_VERSION);
        context=this.context1;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.delete(TABLE_CONTACTS, null, null);
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }
    public Boolean addContact(Contact contact) {
        long result;
         SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
// Contact Name
            values.put(COLUMN_NOM, contact.getNom());
// Contact Phone
            values.put(COLUMN_NUM, contact.getNumTelephone());
// Inserting Row
            result = db.insert("contacts", null, values);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public List<Contact> getAllContacts() {
        List<Contact> ContactList = new ArrayList<Contact>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase   db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                 Contact Contact = new Contact();
                 Contact.set_id(cursor.getInt(0));
                 Contact.setNom(cursor.getString(1));
                 Contact.setNumTelephone(cursor.getString(2));
// Adding Contact to list
                ContactList.add(Contact);

            } while (cursor.moveToNext());
        }
// return Contact list
        return ContactList;
    }

    public Boolean updateContact( Contact Contact) {
        SQLiteDatabase  db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOM, Contact.getNom());
        values.put(COLUMN_NUM, Contact.getNumTelephone());
// updating row
        Cursor cursor = db.rawQuery("Select * from contacts where nom= ?",
                new String[]{String.valueOf(Contact.getNom())});
        if (cursor.getCount() > 0) {
            long result = db.update(TABLE_CONTACTS, values, COLUMN_NOM + " = ?",
                    new String[]{String.valueOf(Contact.getNom())});

        if(result==-1){
            return false;
        }else{
            return true;
        }

        } else {
            return false;
        }}

    // Deleting single Contact
    public Boolean deleteContact(String nom) {
        SQLiteDatabase  db = this.getWritableDatabase();
        long result=db.delete(TABLE_CONTACTS, COLUMN_NOM+ " = ?",
                new String[] { String.valueOf(nom) });
        if(result==-1){
            return false;
        }else{
            return true;
        }


    }
    public void clearContact() {
        SQLiteDatabase   db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, null, null);


    }

       public SimpleCursorAdapter populateListViewFromDB( Context context)
        {
            MySQLiteHelper H=new MySQLiteHelper(context);
            SQLiteDatabase  db = this.getWritableDatabase();
            String Culns[] = { MySQLiteHelper.COLUMN_ID,
                    MySQLiteHelper.COLUMN_NOM,MySQLiteHelper.COLUMN_NUM };
            Cursor cursor=db.query(MySQLiteHelper.TABLE_CONTACTS,
                    Culns,null,null,null,null,null);
            if(cursor.getCount()==0){
                Toast.makeText(context, "No Entry Exists", Toast.LENGTH_SHORT).show();

            }
                String[] fromFieldNames = new String[]{
                        MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NOM, MySQLiteHelper.COLUMN_NUM
                };
                int[] toViewIDs = new int[]{R.id.item_id, R.id.item_nom, R.id.item_number};

                SimpleCursorAdapter Adapter = new SimpleCursorAdapter(
                        context,
                        R.layout.fragment_contact,
                        cursor,
                        fromFieldNames,
                        toViewIDs
                );
                return Adapter;
        }
    
}
