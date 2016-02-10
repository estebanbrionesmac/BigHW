package ejbm.mac.com.bighw;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.EditText;
import android.widget.Toast;

import ejbm.mac.com.bighw.beans.Contact;
import ejbm.mac.com.bighw.beans.bd.ContactBDH;

/**
 * Created by admin on 2/6/2016.
 */
public class Contact_Edit extends AppCompatActivity {

    private final AppCompatActivity act = this ;

    private EditText id ;
    private EditText name ;
    private EditText phone ;

    private ImageView edit ;
    private ImageView delete ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        this.id = (EditText) findViewById( R.id.contact_id ) ;
        this.name = (EditText) findViewById( R.id.contact_name ) ;
        this.phone = (EditText) findViewById( R.id.contact_phone ) ;

        this.edit = (ImageView) findViewById( R.id.contact_edit ) ;
        this.delete = (ImageView) findViewById( R.id.contact_delete ) ;


        this.edit.setImageDrawable(getResources().getDrawable(R.drawable.checked));
        //this.edit.setVisibility(View.INVISIBLE );
        this.delete.setVisibility(View.INVISIBLE);

        Bundle extras = getIntent().getExtras() ;

        if ( extras != null ) {
            this.id.setText( "" + extras.getInt("id") );
            this.name.setText(extras.getString("name"));
            this.phone.setText(extras.getString("phone"));
        }

        this.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactBDH cdbh = new ContactBDH( act  );
                Contact contact = new Contact( /*Integer.parseInt( ( (EditText) findViewById( R.id.contact_id ) ).getText().toString() )

                                ,*/ (( EditText) findViewById( R.id.contact_id ) ).getText().toString()
                                , ((EditText) findViewById( R.id.contact_id ) ).getText().toString()
                ) ;

                cdbh.addContact( contact );

                Toast t = Toast.makeText( act, "Contact added" , Toast.LENGTH_LONG) ;
                // t.setDuration( Gravity.);
                t.show();

                finish();

            }
        });

    }

}
