package ejbm.mac.com.bighw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import ejbm.mac.com.bighw.adapters.ContactAdapter;
import ejbm.mac.com.bighw.beans.bd.ContactBDH;

/**
 * Created by admin on 2/6/2016.
 */
public class RV_Contacts extends AppCompatActivity {

    RecyclerView mRecyclerView ;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private final AppCompatActivity act = this ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rvcontacts );

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_contacts);
        mLayoutManager = new LinearLayoutManager(this);
        //mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ContactBDH cdbh = new ContactBDH( this ) ;
        mAdapter = new ContactAdapter(cdbh.getAllContacts(), this );
        mRecyclerView.setAdapter(mAdapter);




        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( act , Contact_Edit.class ) ;
                act.startActivity(i) ;
            }
        });
    }
}
