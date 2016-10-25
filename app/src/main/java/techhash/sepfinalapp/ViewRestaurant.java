package techhash.sepfinalapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
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
    Config config=new Config();
    private String DATA_URL = "http://"+config.getUrl()+"/SEP/getRestData.php?id=";
   // private String DATA_URL_MENU="http://"+config.getUrl()+"/SEP/getData.php?id=";
   private  final String DATA_URL_MENU="http://"+config.getUrl()+"/SEP/getData.php?id=";

    ListView list;




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
        getRestaurantData();

        list = (ListView) findViewById(R.id.listView);

        sendRequest();



    }


    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(DATA_URL_MENU+stringData,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewRestaurant.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        MenuAdapter cl = new MenuAdapter(this,ParseJSON.names);
        list.setAdapter(cl);
    }



    private void getRestaurantData(){

        if(stringData.equals("")){

            Toast.makeText(this,"Not Available",Toast.LENGTH_LONG).show();
            return;
        }
        loading=ProgressDialog.show(this,"Please wait...","Fetching..",false,false);

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

        Restaurant restaurant=new Restaurant();

        TextView txtHotelName;
        TextView txtAddress;
        TextView txtLandphone;
        TextView txtEmail;
        TextView txtOpeningHours;
        TextView txtDescription;

        txtHotelName= (TextView) findViewById(R.id.textviewHotelName);
        txtDescription=(TextView)findViewById(R.id.textviewHotelDescription);


        try{
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("result");
            JSONObject object=jsonArray.getJSONObject(0);
            restaurant.HotelName=object.getString("HotelName");
            restaurant.Address=object.getString("Address");
            restaurant.Description=object.getString("Description");
            restaurant.LandPhone=object.getString("LandLine");
            restaurant.Openinghours=object.getString("OpeningHours");


            getSupportActionBar().setTitle(restaurant.HotelName);
        }
        catch (JSONException e){
            e.printStackTrace();
        }

        textviewresult.setText(restaurant.HotelName+" :"+restaurant.Address+":"+restaurant.Openinghours);
        txtHotelName.setText(restaurant.HotelName);
        txtDescription.setText(restaurant.Description);


    }




}
