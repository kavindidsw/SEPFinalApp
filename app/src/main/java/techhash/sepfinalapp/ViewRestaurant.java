package techhash.sepfinalapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ViewRestaurant extends AppCompatActivity {

    private String stringData;
    private TextView textviewresult;
    private ProgressDialog loading;
    Config config;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_restaurant);


        textviewresult=(TextView)findViewById(R.id.textViewresult);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_view_restaurant);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (null != intent) {
            stringData= intent.getStringExtra("RId");
            //int numberData = intent.getIntExtra(KEY, defaultValue);
            //boolean booleanData = intent.getBooleanExtra(KEY, defaultValue);
            //char charData = intent.getCharExtra(KEY, defaultValue);

        }
        getData();





    }

    private void getData(){

        if(stringData.equals("")){

            Toast.makeText(this,"Not Available",Toast.LENGTH_LONG).show();
            return;
        }
        loading=ProgressDialog.show(this,"Please wait...","Fetching..",false,false);
        String DATA_URL = "http://192.168.8.100/SEP/getRestData.php?id=";
        String url=DATA_URL+stringData;
        StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                ShowJSON(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ViewRestaurant.this,error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        }
        );

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void ShowJSON(String response){

        //Restaurant restaurant=new Restaurant();
        String Hotelname="";
        String Address="";
        String RegNo="";

        try{
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("result");
            JSONObject object=jsonArray.getJSONObject(0);
            Hotelname=object.getString("HotelName");
            Address=object.getString("Address");
            RegNo=object.getString("RegistrationNo");


            getSupportActionBar().setTitle(Hotelname);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        textviewresult.setText(Hotelname+" :"+Address+":"+RegNo);


    }
}
