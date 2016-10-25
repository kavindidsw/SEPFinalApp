package techhash.sepfinalapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuAdapter extends ArrayAdapter<String> implements View.OnClickListener {

    private String[] names;

    private Activity context;

    public MenuAdapter(Activity context, String[] names) {
        super(context, R.layout.menu_list, names);
        this.context = context;
        this.names = names;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.menu_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);

        textViewName.setText(names[position]);


        listViewItem.setOnClickListener(this);
        return listViewItem;


    }

    @Override
    public void onClick(View view) {
        Toast.makeText(context, "You clicked an item ", Toast.LENGTH_SHORT).show();
    }
}

 class ParseJSON {

    public static String[] names;

    public static final String JSON_ARRAY = "menu";


    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);


            names = new String[users.length()];


            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                names[i] = jo.getString("ItemType");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}