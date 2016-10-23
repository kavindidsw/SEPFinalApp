package techhash.sepfinalapp;

import android.app.SearchManager;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar=(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout layout=(DrawerLayout)findViewById(R.id.drawerLayout);
         toggle=new ActionBarDrawerToggle(this,layout,R.string.open,R.string.close);
        layout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView=(NavigationView)findViewById(R.id.NavigationView);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setTitle(R.string.mytoolbar_title);
        //getSupportActionBar().setIcon(R.drawable.ic_toolbar);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        SearchView searchView=(SearchView) menu.findItem(R.id.menu_search).getActionView();
        SearchManager searchManager=(SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item))
            return true;





        switch (item.getItemId()){

            case R.id.menu_1:
                Toast.makeText(MainActivity.this,"Option 1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_2:
                Toast.makeText(MainActivity.this,"Option 2",Toast.LENGTH_SHORT).show();
                break;


        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        switch (item.getItemId()){

            case R.id.nav_menu_1:
                Toast.makeText(MainActivity.this,"Option one",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_menu_2:
                Toast.makeText(MainActivity.this,"Option two",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);


                break;
            case R.id.nav_menu_4:
                Toast.makeText(MainActivity.this,"Option three",Toast.LENGTH_SHORT).show();
                break;
        }

        DrawerLayout layout= (DrawerLayout) findViewById(R.id.drawerLayout);
        if(layout.isDrawerOpen(GravityCompat.START))
            layout.closeDrawer(GravityCompat.START);



        return false;
    }
}
