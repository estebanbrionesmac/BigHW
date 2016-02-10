package ejbm.mac.com.bighw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ejbm.mac.com.bighw.beans.bd.ContactBDH;
import ejbm.mac.com.bighw.fragments.ContactDB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void clickButtonLoadContacts ( View v ) {

        ContactDB cdb = new ContactDB ( ) ;
        cdb.setAct(this);

        cdb.doContactsJob();

        Toast t = Toast.makeText(this, "Contacts copied from Phone to local Data Base", Toast.LENGTH_LONG) ;
        t.show();
    }

    public void clickButtonEmptyDB ( View v ) {

        ContactBDH c = new ContactBDH ( this ) ;

        c.deleteAllContacts();

        Toast t = Toast.makeText(this, "Data Base has been emptied", Toast.LENGTH_LONG) ;
        // t.setDuration( Gravity.);
        t.show();

    }

    public void clickButtonViewData ( View v ) {

        Intent i = new Intent( this, RV_Contacts.class );
        startActivity(i);
    }

    public void syncData ( View v ) {

        ContactBDH c = new ContactBDH ( this ) ;

        Toast t = Toast.makeText(this, c.sendContacts2Cloud() , Toast.LENGTH_LONG) ;
        // t.setDuration( Gravity.);
        t.show();

    }

    public void clickButtonAddContact ( View v ) {
        Intent i = new Intent( this, Contact_Edit.class );
        startActivity(i) ;
    }
}
