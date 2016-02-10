package ejbm.mac.com.bighw.async;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.os.AsyncTask;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import ejbm.mac.com.bighw.beans.Contact;
import ejbm.mac.com.bighw.beans.bd.ContactBDH;

/**
 * Created by admin on 2/5/2016.
 */
public class ContactDBTasks extends AsyncTask<String, Void, String>  {

    private final static String CLASS_TAG =  "EJBM-AsTk" ;

    private Activity activity ;


    public ContactDBTasks (  ){ }

    public ContactDBTasks ( Activity act ) {
        this.activity = act ;

        //getSupportLoaderManager().initLoader(1, null, this);
    }



    @Override
    protected String doInBackground(String... urls) {
        String msg = loadContacts() ;
        //Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        return msg ;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    public String moveDeviceContacts2DB() {

        return "" ;
    }



    private String loadContacts ( ) {

        Cursor cursor = getContactsFromDevice();

        ArrayList <Contact> contacts = new  ArrayList () ;
        Contact contact = null ;

        ContactBDH cbdh = new ContactBDH( this.activity ) ;

        while (cursor.moveToNext()) {
            if ( cursor.getString (  cursor.getColumnIndex( ContactsContract.Data.DISPLAY_NAME) ).equals( "0") )
                continue ;

            contact = new Contact ( Integer.parseInt( cursor.getString ( cursor.getColumnIndex( ContactsContract.Contacts._ID ) ) )
                                    , cursor.getString ( cursor.getColumnIndex( ContactsContract.Data.DISPLAY_NAME ) )
                                    , getPhonesFromDevice( cursor.getString ( cursor.getColumnIndex( ContactsContract.Data._ID ) ) )
                                );

            // Filter contacts with no phone #
            if ( contact.getPhone() == null || contact.getPhone().equals( "" ) )
                continue ;

            cbdh.addContact( contact );
            // contacts.add( contact ) ;

            // Escribir contactos a otra BD

            Log.d( CLASS_TAG , "\t>" + contact ) ;
        }

        return cursor.getCount() + " contacts read" ;

    }


    private Cursor /*Loader<Cursor>*/ getContactsFromDevice () {
        // Run query
        Uri uri = ContactsContract.RawContacts.CONTENT_URI /*.Contacts.CONTENT_URI*/;
        String[] projection = new String[] { ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.Contacts.HAS_PHONE_NUMBER /*, ContactsContract.CommonDataKinds.Phone.NUMBER*/ };
        String selection = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '" + ("1") + "'";
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME+ " COLLATE LOCALIZED ASC";

        Cursor cursor = null ;

        try {
            cursor = /*new CursorLoader*/  this.activity.managedQuery(  uri, null /*projection*/, null /*selection*/, null /*selectionArgs*/, null /*sortOrder */ );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }


        return cursor ;
    }

    private String  /*Loader<Cursor>*/ getPhonesFromDevice ( String id ) {
        String phone = "" ;
        Cursor pCur = this.activity.managedQuery (
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                        new String[]{id}, null
                    );
        //String x = null, y [] = null ;
        if /*while*/ ( pCur.moveToNext( ) ) {
            //y = pCur.getColumnNames() ;
            phone = pCur.getString(pCur.getColumnIndex(ContactsContract.Data.DATA1)) ;
            //x = pCur.getString(pCur.getColumnIndex(ContactsContract.Data.DATA2)) ;
            //x = pCur.getString(pCur.getColumnIndex(ContactsContract.Data.DATA3)) ;
            //x = pCur.getString(pCur.getColumnIndex(ContactsContract.Data.DATA4)) ;
            //x = pCur.getString ( pCur.getColumnIndex( ContactsContract.Data.CONTACT_ID)) ;
        }

        pCur.close();

        return phone ;
    }
}
