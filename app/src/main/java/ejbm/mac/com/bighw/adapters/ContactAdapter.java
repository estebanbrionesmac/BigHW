package ejbm.mac.com.bighw.adapters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ejbm.mac.com.bighw.Contact_Edit;
import ejbm.mac.com.bighw.R;
import ejbm.mac.com.bighw.beans.Contact;

/**
 * Created by admin on 2/6/2016.
 */
public class ContactAdapter   extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> mDataset;
    private AppCompatActivity ref ;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ContactAdapter(List<Contact> myDataset) {
        mDataset = myDataset;
    }

    public ContactAdapter(List<Contact> myDataset, AppCompatActivity refer ) {
        mDataset = myDataset;
        this.ref = refer ;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView id;
        public TextView name;
        public TextView phone ;

        public ImageView edit ;
        public ImageView delete ;

        public ViewHolder(View v) {
            super(v);
            id = (TextView) v.findViewById(R.id.contact_id);
            //coid = (TextView) v.findViewById( R.id.Contact_coid);
            name = (TextView) v.findViewById(R.id.contact_name);
            phone = (TextView) v.findViewById( R.id.contact_phone);
            //mail = (TextView) v.findViewById( R.id.Contact_email);

            edit = (ImageView) v.findViewById( R.id.contact_edit) ;
            delete = (ImageView) v.findViewById( R.id.contact_delete ) ;
        }
    }

    public void add(int position, Contact c /* String item*/) {
        mDataset.add(position, c /* item*/);
        notifyItemInserted(position);
    }

    public void remove(Contact c /*String item*/) {
        int position = mDataset.indexOf(c /* item*/);
        if (position < 0)
            return;

        mDataset.remove(position);
        notifyItemRemoved(position);
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Contact /* String */ contact = mDataset.get(position);

        //Log.d( "EJBM", contact.toString() ) ;

        //holder.id.setText("" + contact.getId());
//        holder.coid.setText( Contact.getCompanyID () );
        holder.name.setText( /*Contact.getName() */ contact.getName()  );
        holder.phone.setText( contact.getPhone () );
        //holder.mail.setText( Contact.getEmail() );

        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                remove(contact);
            }

        });

        holder.edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // remove(contact);
                Intent i = new Intent( ref , Contact_Edit.class ) ;
                i.putExtra( "id" , contact.getId() ) ;
                i.putExtra( "name",contact.getName() ) ;
                i.putExtra( "phone", contact.getPhone() ) ;

                ref.startActivity( i ) ;
            }

        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}