package techhash.sepfinalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText fname, lname, email, hotel, username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        fname = (EditText) findViewById(R.id.editFname);
        lname = (EditText) findViewById(R.id.editLname);
        email = (EditText) findViewById(R.id.editEmail);
        hotel = (EditText) findViewById(R.id.editHname);
        username = (EditText) findViewById(R.id.editUname);
        password = (EditText) findViewById(R.id.editPassword);

    }

    public void OnRegister(View view)
    {
        String Fname = fname.getText().toString();
        String Lname = lname.getText().toString();
        String Email = email.getText().toString();
        String Hotel = hotel.getText().toString();
        String Username = username.getText().toString();
        String Password = password.getText().toString();

        String type = "register";

        BackWork backWork = new BackWork(this);
        backWork.execute(type,Fname,Lname,Email,Hotel,Username,Password);

    }

    public void BackToLogin(View view)
    {
        //startActivity(new Intent(this,Login.class));
    }
}
