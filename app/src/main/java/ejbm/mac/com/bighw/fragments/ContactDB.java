package ejbm.mac.com.bighw.fragments;

import android.app.Activity;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.util.Log;


import java.util.ArrayList;

import ejbm.mac.com.bighw.async.ContactDBTasks;
import ejbm.mac.com.bighw.beans.Contact;

/**
 * Created by admin on 2/5/2016.
 */
public class ContactDB extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private final static String CLASS_TAG =  "EJBM-FrAc-Cont" ;

    private ContactDBTasks cdbt ;
    private Activity act ;

    ArrayList<Contact> contacts ;

    private final static int URL_CONTACTOS = 100 ;

    public ContactDB () {


    }

/*    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout
        return inflater.inflate(R.layout.contact_list_fragment, container, false);
    }
*/

    public void doContactsJob () {
        /*if ( this.act == null )
            return "Null Acvitity!!!" ; */

        //getSupportLoaderManager().initLoader( URL_CONTACTOS, new Bundle(), this );



        cdbt = new ContactDBTasks ( act ) ;
        cdbt.execute();
    }


    @Override
    public void onLoaderReset(android.support.v4.content.Loader<Cursor> loader) {

    }

    @Override
    public android.support.v4.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri CONTENT_URI = ContactsContract.RawContacts.CONTENT_URI;
        return new CursorLoader( getApplicationContext(), CONTENT_URI, null, null, null, null);

/*        switch ( id ) {
            case URL_CONTACTOS :
                // Returns a new CursorLoader
                return new CursorLoader(
                        getActivity(),                                  // Parent activity context
                        ContactsContract.RawContacts.CONTENT_URI,       // Table to query
                        mProjection,     // Projection to return
                        null,            // No selection clause
                        null,            // No selection arguments
                        null             // Default sort order
                );
            default:
                // An invalid id was passed in
                return null;
        }*/


    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        contacts = new  ArrayList () ;
        Contact contact = null ;

        while (!cursor.isAfterLast()) {

            contact = new Contact ( cursor.getString(21), cursor.getString(22) ) ;
            Log.d(CLASS_TAG, "\t> " + contact) ;

            contacts.add( contact ) ;
            cursor.moveToNext();
        }
    }

    /*
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        Uri CONTENT_URI = ContactsContract.RawContacts.CONTENT_URI;
        return new CursorLoader( getApplicationContext(), CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        contacts = new  ArrayList () ;
        Contact contact = null ;

        while (!cursor.isAfterLast()) {

            contact = new Contact ( cursor.getString(21), cursor.getString(22) ) ;
            Log.d(CLASS_TAG, "\t> " + contact) ;

            contacts.add( contact ) ;
            cursor.moveToNext();
        }
    }
    */



    public Activity getAct() {
        return act;
    }

    public void setAct(Activity act) {
        this.act = act;
    }


}
