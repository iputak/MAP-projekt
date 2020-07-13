package hr.vsite.map.taxivodstvo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public String A = "11";
    public String korisnickoIme = "test";
    public String autentifikator = "0";
    public String vozac = "2";
    // Varijable
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*---------- Hooks ----------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        /*---------- Toolbar ----------*/
        setSupportActionBar(toolbar);

        /*---------- Navigacija -> Navigation Drawer Menu ----------*/

        // Prikaz odabira autentificiranim osobama
        menu = navigationView.getMenu();




        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        // ------ Fragmenti ------
        // Load default fragment
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, new HomeFragment());
        fragmentTransaction.commit();

        Intent intent = getIntent();

        korisnickoIme= intent.getStringExtra("korime");
        autentifikator = intent.getStringExtra("autentifikator");

        menu();



    }

    public void menu() {
        // Da li je korisnik vlasnik/voditelj?
        if (autentifikator.equals(vozac)) {
            menu.findItem(R.id.nav_vozaci).setVisible(false);
            menu.findItem(R.id.nav_novi_vozac).setVisible(false);
        }
    }


    // Ako pritisnemo backspace, da ne izađemo odmah iz aplikacije
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new HomeFragment());
                fragmentTransaction.commit();
                break;
            case R.id.nav_novi_dan:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new SmjenaFragment());
                fragmentTransaction.commit();
                break;
            /*

            case R.id.nav_voznje:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new DogovoreneVoznjeFragment());
                fragmentTransaction.commit();
                break;

            */
            case R.id.nav_izvjesca:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new IzvjescaFragment());
                fragmentTransaction.commit();
                break;
            case R.id.nav_vozaci:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_fragment, new VozaciFragment());
                fragmentTransaction.commit();
                break;
            case R.id.nav_novi_vozac:
                startActivity(new Intent(this ,NoviVozacActivity.class));
                break;
        }
        // Zatvaranje navigacije nakon odabira
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
