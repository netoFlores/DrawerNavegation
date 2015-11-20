package app.ernesto.geofrarmedrawer;


import android.content.res.Configuration;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] opciones;
    private DrawerLayout drawerLayout;
    private ListView menu;
    private ActionBarDrawerToggle  toggle;
    private CharSequence tituloSec;
    private CharSequence tituloApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        opciones = new String[]{"Menu1", "Menu2", "Menu3", "Menu4"};
        drawerLayout = (DrawerLayout) findViewById(R.id.contenedor);
        menu = (ListView) findViewById(R.id.menu);
        menu.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1, opciones));
        menu.setOnItemClickListener(this);
        tituloSec = getTitle();
        tituloApp = getTitle();

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_action_editor_format_align_justify, R.string.abierto, R.string.cerrado)
        {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ActivityCompat.invalidateOptionsMenu(MainActivity.this);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ActivityCompat.invalidateOptionsMenu(MainActivity.this);
            }
        };

        drawerLayout.setDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActivityCompat.invalidateOptionsMenu(MainActivity.this);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        int id = item.getItemId();
        if(id == R.id.action_settings)
            return true;

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new framento1();
                break;
            case 1:
                fragment = new framento2();
                break;
            case 2:
                fragment = new framento3();
                break;
            case 3:
                fragment = new framento4();
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedorFrame, fragment).commit();
        menu.setItemChecked(position, true);

        tituloSec = opciones[position];
        getSupportActionBar().setTitle(tituloSec);
        drawerLayout.closeDrawer(menu);

    }
}
