package ejbm.mac.com.bighw.beans.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ejbm.mac.com.bighw.beans.Contact;

/*
import com.google.api.services.datastore.DatastoreV1.*;
import com.google.api.services.datastore.client.Datastore;
import com.google.api.services.datastore.client.DatastoreException;
import com.google.api.services.datastore.client.DatastoreFactory;
import com.google.api.services.datastore.client.DatastoreHelper;
import com.google.protobuf.ByteString;
*/

/**
 * Created by admin on 2/6/2016.
 */
public class ContactBDH extends SQLiteOpenHelper {

    private static final String CLASS_TAG = "EJBM DB" ; 
    
    // All Static variables
    // Database Version

    private static final int DATABASE_VERSION = 1 ;

    // Database Name

    private static final String DATABASE_NAME = "BigHW" ;

    // Contactes table name

    private static final String TABLE_CONTACTS = "CONTACTS";

    // Contactes Table Columns names

    private static final String KEY_ID = "id";

    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";


    public ContactBDH (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //super();

    }

    @Override
    public  void onCreate (SQLiteDatabase db) {
        String CREATE_Contactes_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_PHONE + " TEXT "
                + ")";

        try {
            db.execSQL(CREATE_Contactes_TABLE);

            Log.d(CLASS_TAG, "Table created: " + TABLE_CONTACTS );
        } catch ( Exception ex ) {
            Log.d( CLASS_TAG, ex.getMessage() );
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate (db);
    }


    // Adding new Contact
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PHONE, contact.getPhone());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection

        Log.d(CLASS_TAG, "\t\t\t> Insertado - " + contact) ;
    }

    // Getting single Contact
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID, KEY_NAME, KEY_PHONE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return loadContact(cursor);
    }

    private Contact loadContact ( Cursor cursor  ) {

        Contact contact = new Contact(  cursor.getInt( 0 ) ,
                                        cursor.getString(1)
                                        , cursor.getString(2)
                                    );

        return contact ;
    }

    // Getting All Contactes
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Contact Contact = null ;

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact = loadContact(cursor) ;

                // Adding Contact to list
                contactList.add(Contact);

            } while (cursor.moveToNext());
        }

        // return Contact list
        return contactList;
    }

    // Getting Contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int r = cursor.getCount() ;
        cursor.close();

        // return count
        return r ;
    }


    // Updating single Contact
    public int updateContact(Contact Contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put ( KEY_NAME, Contact.getName() );
        values.put(KEY_PHONE, Contact.getPhone());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?", new String[]{String.valueOf(Contact.getId())});
    }

    // Deleting single Contact
    public void deleteContact(Contact Contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?", new String[]{String.valueOf(Contact.getId())});
        db.close();
    }

    // Deleting single Contact
    public void deleteAllContacts ( ) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, null, null ) ;
        db.close();
    }



    public String sendContacts2Cloud ( ) {
        Connection con = null;
        int cont = 0 ;

/*        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager .getConnection("jdbc:mysql://107.178.222.162/a01?"
                    + "user=root&password=MobileApp$");

            String query = " INSERT INTO CONTACTS VALUES ( ?, ? , ? ) " ;
            for ( Contact contact : getAllContacts() ) {
                PreparedStatement ps = null ;

                try {
                    ps = con.prepareStatement(query);
                        ps.setInt(1, contact.getId());
                    ps.setString(2, contact.getName());
                    ps.setString(3, contact.getPhone());

                    ps.executeUpdate();

                    Log.d(CLASS_TAG, " \t>> " + contact + " uploaded 2 cloud");

                    cont ++ ;

                } catch ( Exception ex ) {
                    ex.getMessage() ;

                } finally {
                    ps.close();
                }
        }

        } catch (Exception ex) {
            ex.getMessage();

        } finally {
            try {
                con.close();
            } catch ( Exception ex ) { ex.getMessage() ; }
        }
*/
        return getContactsCount() + " contacts will be uploaded to cloud DB...";
    }


}
