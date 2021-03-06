package com.rodrigoamora.testgreendao.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.rodrigoamora.testgreendao.R;
import com.rodrigoamora.testgreendao.dao.DaoFactory;
import com.rodrigoamora.testgreendao.entity.DaoSession;
import com.rodrigoamora.testgreendao.fragment.ListPeopleFragment;
import com.rodrigoamora.testgreendao.fragment.SavePersonFragment;
import com.rodrigoamora.testgreendao.util.FragmentUtil;
import com.rodrigoamora.testgreendao.util.ShareUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creteNavigationView();

        DaoSession session = DaoFactory.createSession(this);
        if (session.getPersonDao().loadAll().isEmpty()) {
            FragmentUtil.changeFragment(R.id.conatiner, SavePersonFragment.class, getFragmentManager(), false, null);
        } else {
            FragmentUtil.changeFragment(R.id.conatiner, ListPeopleFragment.class, getFragmentManager(), false, null);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_save_person) {
            FragmentUtil.changeFragment(R.id.conatiner, SavePersonFragment.class, getFragmentManager(), false, null);
        } else if (id == R.id.nav_list_people) {
            FragmentUtil.changeFragment(R.id.conatiner, ListPeopleFragment.class, getFragmentManager(), false, null);
        } else if (id == R.id.nav_share) {
            String textShare = "APP Test-GreenDao\n"+
                    "URL: https://github.com/RodrigoAmora/test-greendao";
            ShareUtil.directShare(this, getString(R.string.share), textShare);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void creteNavigationView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

}
