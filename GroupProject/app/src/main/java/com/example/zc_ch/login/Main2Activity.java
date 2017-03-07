package com.example.zc_ch.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Button button;
    private ImageView mImageView;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;
    private ImageView mImageView5;
    private TextView mTextView;

    public  String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = new Bundle();
        userName = bundle.getString("userName");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        mImageView =(ImageView)findViewById(R.id.imageView4);
        mImageView2 =(ImageView)findViewById(R.id.imageView5);
        mImageView3 =(ImageView)findViewById(R.id.imageView6);
        mImageView4 =(ImageView)findViewById(R.id.imageView8);
        mImageView5 =(ImageView)findViewById(R.id.imageView9);
        mTextView = (TextView)findViewById(R.id.textView);
        mImageView.setAlpha(0.0f);
        mImageView2.setAlpha(0.0f);
        mImageView3.setAlpha(0.0f);
        mImageView4.setAlpha(0.0f);
        mImageView5.setAlpha(0.0f);
        mTextView.setAlpha(0.0f);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);



        navigationView.setNavigationItemSelectedListener(this);


    }
    public void onClick(View v) {
        Intent con = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(con);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus){
            fadeIn.run();
        }
    }
    Runnable fadeIn = new Runnable() {
        @Override
        public void run() {
            mImageView.animate().setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
            mImageView2.animate().setStartDelay(1000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
            mImageView3.animate().setStartDelay(3000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
            mImageView4.animate().setStartDelay(5000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
            mTextView.animate().setStartDelay(5000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
            mImageView5.animate().setStartDelay(5000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f).withEndAction(fadeOut);
        }
    };
    Runnable fadeOut = new Runnable() {
        @Override
        public void run() {
            mImageView.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
            mImageView2.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
            mImageView3.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
            mImageView4.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
            mTextView.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
            mImageView5.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f).withEndAction(fadeIn);
        }
    };
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(Main2Activity.this,LoginActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent prifile = new Intent(Main2Activity.this,ProfileActivity.class);
            startActivity(prifile);
        } else if (id == R.id.nav_gallery) {

        }  else if (id == R.id.nav_manage) {
            Intent setting = new Intent(Main2Activity.this,SettingsActivity.class);
            startActivity(setting);
        }else if (id == R.id.nav_fitness) {
            Intent fitness = new Intent(Main2Activity.this,Stepcourt.class);
            startActivity(fitness);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
