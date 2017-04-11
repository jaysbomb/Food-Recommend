package com.example.zc_ch.login;
/**
 * Created by ZOU Zijie on 2017/4/9.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.zc_ch.login.LoginActivity.state;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Button button;
    static FragmentManager fragmentManager;
    private ImageView mImageView;
    private ImageView mImageView2;
    private ImageView mImageView3;
    private ImageView mImageView4;
    private ImageView mImageView5;
    private TextView mTextView;
    private TextView tvUser;
    //for foodXX
    private static FoodIconSettings settingsFood;
    private boolean showHelp = true;

    private FoodIconList foodIconList;

    public static FoodIconSettings getFoodSettings() { return settingsFood; }
    public  String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle = getIntent().getExtras();
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

//        mImageView =(ImageView)findViewById(R.id.imageView4);
//        mImageView2 =(ImageView)findViewById(R.id.imageView5);
//        mImageView3 =(ImageView)findViewById(R.id.imageView6);
//        mImageView4 =(ImageView)findViewById(R.id.imageView8);
//        mImageView5 =(ImageView)findViewById(R.id.imageView9);
//        mTextView = (TextView)findViewById(R.id.textView);
//        tvUser =(TextView) findViewById(R.id.tvUser);
//        tvUser.setText(userName);
//        mImageView.setAlpha(0.0f);
//        mImageView2.setAlpha(0.0f);
//        mImageView3.setAlpha(0.0f);
//        mImageView4.setAlpha(0.0f);
//        mImageView5.setAlpha(0.0f);
//        mTextView.setAlpha(0.0f);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        TextView name = (TextView)header.findViewById(R.id.tvHeaderName);
        name.setText(userName);

        //food
        settingsFood = FoodIconSettings.getFoodIconSettings(this);
        foodIconList = new FoodIconList();

        if (showHelp) {

            showHelp = false;
        }
        Fragment fragment = null;
        try {
            fragment = Mainpage.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

    }
    public void onClick(View v) {
        Intent con = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(con);
    }
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus){
//        super.onWindowFocusChanged(hasFocus);
//
//        if (hasFocus){
//            fadeIn.run();
//        }
//    }
//    Runnable fadeIn = new Runnable() {
//        @Override
//        public void run() {
//            mImageView.animate().setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
//            mImageView2.animate().setStartDelay(1000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
//            mImageView3.animate().setStartDelay(3000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
//            mImageView4.animate().setStartDelay(5000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
//            mTextView.animate().setStartDelay(5000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f);
//            mImageView5.animate().setStartDelay(5000).setDuration(2000).setInterpolator(new LinearInterpolator()).alpha(1.0f).withEndAction(fadeOut);
//        }
//    };
//    Runnable fadeOut = new Runnable() {
//        @Override
//        public void run() {
//            mImageView.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
//            mImageView2.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
//            mImageView3.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
//            mImageView4.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
//            mTextView.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f);
//            mImageView5.animate().setDuration(2000).setInterpolator(new DecelerateInterpolator()).alpha(0.0f).withEndAction(fadeIn);
//        }
//    };
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private boolean checkConfig() {
        if (foodIconList.getFoodRestrictionList(true).size() <= 0) {
            Toast.makeText(
                    getApplicationContext(),
                    getResources()
                            .getString(R.string.no_config_msg),
                    Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
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
            state=1;
            finish();
            return true;
        }
        if (id == R.id.action_about){

            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;

        switch (id){
            case R.id.nav_camera:
                fragmentClass = Donghua.class;

                break;
            case R.id.nav_gallery:
                if (checkConfig()) {
                    fragmentClass = FoodIconGrid.class;
                } else {
                    fragmentClass = FoodIconConfig.class;
                }
                break;
            case R.id.nav_manage:
                fragmentClass = FoodIconConfig.class;
                break;
            case R.id.nav_fitness:
                fragmentClass = Stepcourt.class;
//                Bundle bundle = new Bundle();
//                bundle.putString("userName", userName);
//                fragmentClass.putExtras(bundle);
//                Bundle bundle = new Bundle();
//                bundle.putString("userName", userName);
//                Stepcourt myFrag = new Stepcourt();
//                myFrag.setArguments(bundle);
                break;
            case R.id.nav_share:
//                Intent location = new Intent(Main2Activity.this,Sharelocation.class);
//                startActivity(location);
                    fragmentClass = Sharelocation.class;
                break;
            case R.id.nav_send:
                Intent history = new Intent(this,HistoryActivity.class);
                startActivity(history);
                break;

        }
//        if (id == R.id.nav_camera) {
//            Intent prifile = new Intent(Main2Activity.this,ProfileActivity.class);
//            startActivity(prifile);
//        } else if (id == R.id.nav_gallery) {
//
//        }  else if (id == R.id.nav_manage) {
//            Intent setting = new Intent(Main2Activity.this,SettingsActivity.class);
//            startActivity(setting);
//        }else if (id == R.id.nav_fitness) {
//            Intent fitness = new Intent(Main2Activity.this,Stepcourt.class);
//            Bundle bundle = new Bundle();
//            bundle.putString("userName", userName);
//            fitness.putExtras(bundle);
//            startActivity(fitness);
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
        if (fragmentClass != null) {
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
