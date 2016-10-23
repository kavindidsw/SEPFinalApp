package techhash.sepfinalapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by techhash on 10/23/2016.
 */
public class RestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Restaurant> data= Collections.emptyList();
    Restaurant current;

    public RestAdapter(Context context,List<Restaurant> data){
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.data=data;
    }
    // @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_restaurant, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        Restaurant current=data.get(position);
        myHolder.textRestName.setText(current.HotelName);
        myHolder.textAddress.setText("Address " + current.Address);
        myHolder.textPostalcode.setText("Postalcode: " + current.Postalcode);
        myHolder.intRestId.setText(current.ID);

        //myHolder.textPrice.setText("Rs. " + current.price + "\\Kg");
        //myHolder.textPrice.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }





    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textRestName;
        TextView textPostalcode;
        TextView textAddress;
        TextView intRestId;
        TextView textEmail;

        public MyHolder(View itemview){
            super(itemview);
            textRestName=(TextView)itemview.findViewById(R.id.textResName);
            textPostalcode=(TextView)itemview.findViewById(R.id.textPostalcode);
            textAddress=(TextView)itemview.findViewById(R.id.textaddress);
            intRestId=(TextView)itemview.findViewById(R.id.textResid);


            itemview.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {


            Toast.makeText(context, "You clicked an item "+intRestId.getText() , Toast.LENGTH_SHORT).show();


            //int Rid=intRestId.getText();


           // Intent intent = new Intent(context,ViewRestaurant.class);
            //intent.putExtra("RId",intRestId.getText() );
            //context.startActivity(intent);

        }
    }

}
